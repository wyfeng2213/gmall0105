package com.atguigu.gmall.passport.controller;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.MD5;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.atguigu.gmall.bean.UmsMember;
import com.atguigu.gmall.service.UserService;
import com.atguigu.gmall.util.HttpclientUtil;
import com.atguigu.gmall.util.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : 熊亚东
 * @description :
 * @date : 2019/7/17 | 18:55
 **/
@Controller
@CrossOrigin
public class PassportController {

    @Reference
    UserService userService;

    // 点击登录的时候 会携带returnurl 比如首页的url returnurl:http://localhost:8083/index
    //如果登录成功访问首页的url http://localhost:8083/index?token=
    // 对于京东或者天猫 818或者双12 可以预先把活跃用户先放到redis中 ,减少服务器的压力
    @RequestMapping("login")/*本站登录*/
    @ResponseBody
    public String login(UmsMember umsMember, HttpServletRequest request) {

        String token = "";
        /*调用用户服务验证用户名，密码*/
        UmsMember umsMemberLogin = userService.login(umsMember);
        if (umsMemberLogin != null) {
            /*登录成功 , 用jwt制作token ， 将token存入redis*/
            /*在web-util中有一个jwt加密算法，定义了一个map<String , Object>*/
            String memberId = umsMemberLogin.getId();
            String nickname = umsMemberLogin.getNickname();
            Map<String, Object> userMap = new HashMap<>();
            userMap.put("memberId", memberId);
            userMap.put("nickname", nickname);

            // 通过nginx转发的客户端ip  nginx需要设置 x-forwarded-for
            String ip = request.getHeader("x-forwarded-for");
            if (StringUtils.isBlank(ip)) {
                //从request中获取
                ip = request.getRemoteAddr();
                if (StringUtils.isBlank(ip)) {
                    // 要么给个默认的ip ,要么就直接return false 异常处理;
                    ip = "127.0.0.1";
                }
            }

            /*在以后实际项目中需要按照预先设计的算法对其进行加密(JwtUtil.encode)，再生成token*/
            // key和ip最好最好进行md5加密 SecureUtil.md5("2019")
            token = JwtUtil.encode("2019", userMap, ip);
            /*将token存入redis , cookie可能被篡改 , 需要redis也要设置*/
            userService.addUserToken(token, memberId);
        } else {
            /*登录失败 , 前端判断token*/
            token = "false";
        }
        return token;
    }

    /*验证token*/
    @RequestMapping("verify")
    @ResponseBody
    public String verify(String token, String currentIp, HttpServletRequest request) {
        /*通过jwt校验token真假 ,解析*/
        Map<String, String> map = new HashMap<>();
        /*对token进行解析(JwtUtil.decode)*/
        /*=========================================================================================================================================*/
                     /*这里有一个大的bug不能从这里的request域中获取原始ip，currentIp（原始ip） ，request.getRemoteAddr(当前获取的ip是
                                     HttpclientUtil.doGet("http://localhost:8085/verify?token="发送的)*/
        /*=========================================================================================================================================*/
        System.out.println(request.getRemoteAddr());

        Map<String, Object> decode = JwtUtil.decode(token, "2019", currentIp);
        if (decode != null) {
            map.put("status", "success");
            map.put("memberId", (String) decode.get("memberId"));
            map.put("nickname", (String) decode.get("nickname"));
            return JSON.toJSONString(map);
        } else {
            map.put("status", "fail");
            return JSON.toJSONString(map);
        }
    }

    @RequestMapping("vlogin")/*社交登录*/
    public String vlogin(String code, HttpServletRequest request) {
        /*1.获得授权码，换取access_token*/
        String URL1 = "https://api.weibo.com/oauth2/access_token";
        String URL2 = "https://api.weibo.com/2/users/show.json";
        //参数
        String CLIENT_ID = "3702255538";
        String CLIENT_SECRET = "203c233ca28be441bdaf3fabfb82eedb";
        String GRANT_TYPE = "authorization_code";
        String REDIRECT_URI = "http://passport.gmall.com:8085/vlogin";
        String CODE = code;
        //accesstoken  url
        String s3 = URL1 + "?client_id=" + CLIENT_ID + "&client_secret=" + CLIENT_SECRET + "&grant_type=" + GRANT_TYPE + "&redirect_uri=" + REDIRECT_URI + "&code=" + CODE;
        String access_token_json = HttpclientUtil.doPost(s3, null);
        Map<String, Object> access_map = JSON.parseObject(access_token_json, Map.class);
        String ACCESS_TOKEN = (String) access_map.get("access_token");
        String UID = (String) access_map.get("uid");
        /*2.access_token换取用户信息*/
        String s4 = URL2 + "?access_token=" + ACCESS_TOKEN + "&uid=" + UID;
        String user_json = HttpclientUtil.doGet(s4);
        Map<String, Object> user_map = JSON.parseObject(user_json, Map.class);
        System.out.println(user_map);
        /*3.将用户信息保存到数据库，用户类型设置为微博用户*/
        UmsMember umsMember = new UmsMember();
        umsMember.setSourceType("2");
        umsMember.setSourceUid(Long.parseLong(UID));
        umsMember.setAccessCode(code);
        umsMember.setAccessToken(ACCESS_TOKEN);
        if (user_map != null) {
            umsMember.setNickname((String) user_map.get("screen_name"));
            umsMember.setCity((String) user_map.get("location"));
            String gender = (String) user_map.get("gender");
            if (gender != null) {
                if (gender.equals("m")) {
                    umsMember.setGender("1");
                } else {
                    umsMember.setGender("0");
                }
            }
        }
        UmsMember umsCheck = new UmsMember();
        umsCheck.setSourceUid(umsMember.getSourceUid());
        //检查   查询是否已经来源的uid , 如果没有说明之前没有登录过
        UmsMember checkOauthUser = userService.checkOauthUser(umsCheck);
        if (checkOauthUser == null) {/*之前没有登陆过*/
        /* 主键返回不支持远程RPC协议 ，保存用户信息 , mybatis的主键返回策略不能跨RPC协议使用，当我们远程调用
                        userService.addOauthUser(umsMember) 用该把新增的用户返回到这里*/
            //UmsMember  需要设置用户的主键生成策略 @GeneratedValue(strategy = GenerationType.IDENTITY) 才能获取到id
            //解决mybatis的主键返回策略不能跨RPC协议使用 可以返回一个对象包含id
            umsMember = userService.addOauthUser(umsMember);
        } else {
            // 之前就用第三方登录过 直接获取信息
            umsMember = checkOauthUser;
        }
        /*4.生成jwt的token，并且从定向到首页，携带token*/
        String token = "";
//        /*调用用户服务验证用户名*/
//        UmsMember umsMemberLogin = userService.login(umsMember);
//        if (umsMemberLogin != null) {
        /*登录成功 , 用jwt制作token ， 将token存入redis*/
        /*在web-util中有一个jwt加密算法，定义了一个map<String , Object>*/
//        String memberId = umsMemberLogin.getId();
//        String nickname = umsMemberLogin.getNickname();
        String memberId = umsMember.getId();
        String nickname = umsMember.getNickname();
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("memberId", memberId);
        userMap.put("nickname", nickname);

        // 通过nginx转发的客户端ip  nginx需要设置 x-forwarded-for
        String ip = request.getHeader("x-forwarded-for");
        if (StringUtils.isBlank(ip)) {
            //从request中获取
            ip = request.getRemoteAddr();
            if (StringUtils.isBlank(ip)) {
                // 要么给个默认的ip ,要么就直接return false 异常处理;
                ip = "127.0.0.1";
            }
        }
        /*在以后实际项目中需要按照预先设计的算法对其进行加密(JwtUtil.encode)，再生成token*/
        token = JwtUtil.encode("2019", userMap, ip);
        /*将token存入redis*/
        userService.addUserToken(token, memberId);
        return "redirect:http://localhost:8083/index?token=" + token + "&currentIp=" + ip;
//        } else {
//            /*登录失败 , 前端判断token*/
//            return "fail";
//        }
    }

    @RequestMapping("index")
    public String index(String ReturnUrl, ModelMap map) {
        map.put("ReturnUrl", ReturnUrl);
        return "index";
    }
}
