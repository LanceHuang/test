package com.lance.test.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Lance
 * @since 2021/12/31
 */
public class JwtTest {

    @Test
    void test() {
        String secret = "10086";
        Algorithm algorithm = Algorithm.HMAC256(secret);
        String token = JWT.create()
                .withClaim("username", "lance")
                .withClaim("password", "12346")
                .sign(algorithm);

        DecodedJWT decodedJWT = JWT.decode(token + "d");
        System.out.println(decodedJWT.getToken());
        System.out.println(decodedJWT.getHeader());
        System.out.println(decodedJWT.getPayload());
        System.out.println(decodedJWT.getSignature());
        System.out.println();
        System.out.println(decodedJWT.getAlgorithm());
        System.out.println(decodedJWT.getType());
        System.out.println(decodedJWT.getClaims());

        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT verify = verifier.verify(token);
        System.out.println(verify);
    }

    @Test
    void testUtil() {
        String username = "lance";
        String secret = "123456";
        String token = JwtUtils.sign(username, secret);
        System.out.println(token);

        Assertions.assertTrue(JwtUtils.verify(token, secret));
        Assertions.assertFalse(JwtUtils.verify(token + "d", secret));
        Assertions.assertFalse(JwtUtils.verify(token, secret + "a"));
    }
}
