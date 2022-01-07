package com.lance.test.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;

/**
 * @author Lance
 * @since 2022/1/6
 */
public class JwtUtils {

    public static final String USERNAME = "username";

    /**
     * 签名
     *
     * @param username 用户名
     * @param secret   密钥
     * @return token
     */
    public static String sign(String username, String secret) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create()
                .withClaim(USERNAME, username)
                .sign(algorithm);
    }

    /**
     * 校验token
     *
     * @param token  token
     * @param secret 密钥
     * @return 校验结果
     */
    public static boolean verify(String token, String secret) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTVerifier verifier = JWT.require(algorithm).build();
        try {
            verifier.verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

}
