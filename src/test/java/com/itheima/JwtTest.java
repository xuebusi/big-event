package com.itheima;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {
    /**
     * 测试生成Token令牌
     */
    @Test
    public void testGen() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 1);
        claims.put("username", "张三");
        String token = JWT.create()
                // 添加载荷
                .withClaim("user", claims)
                // 设置过期时间
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 12))
                // 指定算法，配置秘钥
                .sign(Algorithm.HMAC256("itheima"));

        System.out.println(token);
    }

    /**
     * 测试验证Token令牌, 验证失败的3种情况:
     * <p>
     * (1)头部和载荷被篡改;
     * (2)秘钥被篡改;
     * (3)token过期;
     */
    @Test
    public void testParse() {
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7ImlkIjoxLCJ1c2VybmFtZSI6IuW8oOS4iSJ9LCJleHAiOjE3MDkxMjk2Nzl9.M_-5vYnIoiPjMvMJgfU8CKAm33Usr3cqoiKh3SMTqMg";

        // 验证JWT令牌使用的秘钥必须和生成JWT令牌时使用的秘钥相同
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("itheima")).build();

        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        Map<String, Claim> claims = decodedJWT.getClaims();
        System.out.println(claims.get("user"));
    }
}
