/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;



import com.auth0.jwt.algorithms.Algorithm;




import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JOSEObjectType;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;

import org.apache.commons.lang3.time.DateUtils;




/**
 *
 * @author herson
 */


import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import java.io.UnsupportedEncodingException;

import java.util.Date;

public class JWTHelper {

    private static final String SECRET = "super-secret-key-must-be-256-bits-long";
    private static final int EXPIRATION_TIME_IN_MS = 86400000; // 24 horas

    /**
     * Generates a signed JWT token.
     * @param username Username.
     * @param email User's email.
     * @param nombre User's name.
     * @param tipoUsuario User's role.
     * @return Signed JWT token.
     */
    public static String generateToken(String username, String email, String nombre, String tipoUsuario, int idUsuario) {
        return JWTHelper.getSignedToken(
                JWTHelper.getHeader(),
                JWTHelper.getClaims(username, email, nombre, tipoUsuario, idUsuario)
        ).serialize();
    }

    // Methods for Generating JWT

    private static JWSHeader getHeader() {
        return new JWSHeader.Builder(JWSAlgorithm.HS256)
                .type(JOSEObjectType.JWT)
                .build();
    }

    private static JWTClaimsSet getClaims(String username, String email, String nombre, String tipoUsuario, int idUsuario) {
        Date currentDate = new Date();
        return new JWTClaimsSet.Builder()
                .issueTime(currentDate)
                .notBeforeTime(currentDate)
                .expirationTime(DateUtils.addMilliseconds(currentDate, EXPIRATION_TIME_IN_MS))
                .claim("username", username)
                .claim("email", email)
                .claim("nombre", nombre)
                .claim("tipoUsuario", tipoUsuario)
                .claim("idUsuario", idUsuario)
                .build();
    }

    private static SignedJWT getSignedToken(JWSHeader header, JWTClaimsSet claims) {
        try {
            SignedJWT signedJWT = new SignedJWT(header, claims);
            signedJWT.sign(new MACSigner(SECRET.getBytes()));
            return signedJWT;
        } catch (JOSEException e) {
            System.out.println("Error creating JWT: " + e.getMessage());
            throw new RuntimeException("Error generating token.");
        }
    }
    
public static DecodedJWT verifyToken(String token) throws JWTVerificationException, IllegalArgumentException, UnsupportedEncodingException {
    Algorithm algorithm = Algorithm.HMAC256(SECRET);
    JWTVerifier verifier = JWT.require(algorithm).build();
    DecodedJWT jwt = verifier.verify(token);

    // Log claims for debugging purposes
    System.out.println("Decoded username: " + jwt.getClaim("username").asString());
    System.out.println("Decoded email: " + jwt.getClaim("email").asString());
    System.out.println("Decoded nombre: " + jwt.getClaim("nombre").asString());
    System.out.println("Decoded tipoUsuario: " + jwt.getClaim("tipoUsuario").asString());

    return jwt;
}

}


