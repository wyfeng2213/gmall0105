package com.atguigu.gmall.util;

import io.jsonwebtoken.*;

import java.util.Map;

/**
 * @author : 熊亚东
 * @description :jwt单点登录加密算法
 * @date : 2019/7/18 | 9:04
 **/
public class JwtUtil {
    // 公共部分 : key 秘钥 ,param:私有部分 用户内容 salt一般是ip+时间戳后几位 通过MD5加密之后
    public static String encode(String key, Map<String, Object> param, String salt) {
        if (salt != null) {
            key += salt;
        }
        JwtBuilder jwtBuilder = Jwts.builder().signWith(SignatureAlgorithm.HS256, key);
        jwtBuilder = jwtBuilder.setClaims(param);
        String token = jwtBuilder.compact();
        return token;
    }

    public static Map<String, Object> decode(String token, String key, String salt) {
        Claims claims = null;
        if (salt != null) {
            key += salt;
        }
        try {
            claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        } catch (JwtException e) {
            return null;
        }
        return claims;
    }
}

