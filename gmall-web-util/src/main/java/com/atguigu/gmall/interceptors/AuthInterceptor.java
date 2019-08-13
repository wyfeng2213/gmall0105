package com.atguigu.gmall.interceptors;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.atguigu.gmall.annotations.LoginRequired;
import com.atguigu.gmall.util.CookieUtil;
import com.atguigu.gmall.util.HttpclientUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //拦截代码
        //判断请求是否有@LoginRequired注解，有代表该方法必须拦截
        /*handler获取请求中的方法*/
        HandlerMethod hm = (HandlerMethod) handler;
        /*通过反射获取该方法上面的LoginRequired注解*/
        LoginRequired methodAnnotation = hm.getMethodAnnotation(LoginRequired.class);

        // 打印请求的url
        StringBuffer requestURL1 = request.getRequestURL();
        System.out.println(requestURL1);

        if (methodAnnotation == null) {
            return true;
        }

        String token = "";
        /*从Cookie中获取之前该用户的token ， 可能为null
         * oldtoken是cookie中本地保存的 , newtoken是浏览器传过来的
         * 判断oldtoken是否为空  使用token=oldtoken
         * 只要newtoken不为空 , 则使用newtoken   使用token=newtoken
         * 如果最后token为空 则从来没有登录过
         * */
        String oldToken = CookieUtil.getCookieValue(request, "oldToken", true);
        if (StringUtils.isNotBlank(oldToken)) {
            token = oldToken;
        }
        /*从当前请求域中获取该用户token ， 可能为null*/
        String newToken = request.getParameter("token");
        if (StringUtils.isNotBlank(newToken)) {
            token = newToken;
        }
        /*判断该请求是否必须要有登录权限（就是登陆过才放该请求）*/
        boolean loginSuccess = methodAnnotation.loginSuccess();
        /*验证token，调用认证中心verify
         * passport.gmall.com*/
        String success = "fail";
        Map<String, String> successMap = new HashMap<>();
        // token不为空 进行token验证
        if (StringUtils.isNotBlank(token)) {

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
            // 认证结果  通过jwt解析出来的用户私有数据
            String successJson = HttpclientUtil.doGet("http://localhost:8085/verify?token=" + token + "&currentIp=" + ip);
            successMap = JSON.parseObject(successJson, Map.class);
            success = successMap.get("status");
        }
        // 需要认证成功之后才能放行的
        if (loginSuccess) {
            /*======用户必须登录成功才能使用该业务  认证结果是否成功*/
            // ======失败的情况
            if (!success.equals("success")) {
                /*重定向到登录界面*/
                /* passport.gmall.com*/
                //用户从哪个页面被拦截，获取该页面的全地址 ：StringBuffer requestURL = request.getRequestURL();
                // 用户登录成功后可以返回该页面
                StringBuffer requestURL = request.getRequestURL(); //获取当前请求url
                //http://localhost:8085/index 对应的是passport的登录页面 携带首页的url 跳转到登录页面
                // 登录成功之后 就可以跳转到项目的首页
                response.sendRedirect("http://localhost:8085/index?ReturnUrl=" + requestURL);
                return false;
            } else {
                //=======以下是成功success的处理 也就是认证成功之后的处理
                /*需要将token中携带的用户信息写入request，方便其他服务使用*/
                request.setAttribute("memberId", successMap.get("memberId"));
                request.setAttribute("nickname", successMap.get("nickname"));

                /*验证通过，覆盖Cookie中的token，防止token在Cookie中过期*/
                if (StringUtils.isNotBlank(token)) {
                    CookieUtil.setCookie(request, response, "oldToken", token, 60 * 60 * 1, true);
                }
            }
        } else {
            /*用户不必登录也可以访问该方法 ， 但是必须验证*/
            if (success.equals("success")) {
                /*需要将token中携带的用户信息写入request，方便其他服务使用*/
                request.setAttribute("memberId", successMap.get("memberId"));
                request.setAttribute("nickname", successMap.get("nickname"));
                /*验证通过，覆盖Cookie中的token，防止token在Cookie中过期*/
                if (StringUtils.isNotBlank(token)) {
                    CookieUtil.setCookie(request, response, "oldToken", token, 60 * 60 * 1, true);
                }
            }
        }
        return true;
    }
}