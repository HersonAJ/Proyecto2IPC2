/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.ws.rs.core.Response;
import java.io.UnsupportedEncodingException;
/**
 *
 * @author herson
 */



public class AuthService {

    public Response validateToken(String token) {
        try {
            // Eliminar el prefijo "Bearer "
            if (token.startsWith("Bearer ")) {
                token = token.substring(7);
            }

            System.out.println("Verificando token...");
            DecodedJWT jwt = JWTHelper.verifyToken(token);
            System.out.println("Token verificado");

            // Obtener el tipo de usuario del token
            String tipoUsuario = jwt.getClaim("tipoUsuario").asString();
            System.out.println("Tipo de usuario: " + tipoUsuario);

            // Validar que el usuario es administrador
            if (!"Administrador".equals(tipoUsuario)) {
                return Response.status(Response.Status.FORBIDDEN)
                        .entity("{\"message\":\"No tienes permisos para realizar esta acción.\"}")
                        .build();
            }

            return null;  // Si todo es correcto, retorna null

        } catch (JWTVerificationException | IllegalArgumentException | UnsupportedEncodingException e) {
            System.out.println("Error al verificar el token: " + e.getMessage());
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("{\"message\":\"Token inválido.\"}")
                    .build();
        }
    }

    public Response validateTokenForCompradorAnuncios(String token) {
        try {
            // Eliminar el prefijo "Bearer "
            if (token.startsWith("Bearer ")) {
                token = token.substring(7);
            }

            System.out.println("Verificando token...");
            DecodedJWT jwt = JWTHelper.verifyToken(token);
            System.out.println("Token verificado");

            // Obtener el tipo de usuario del token
            String tipoUsuario = jwt.getClaim("tipoUsuario").asString();
            System.out.println("Tipo de usuario: " + tipoUsuario);

            // Validar que el usuario es comprador de anuncios
            if (!"Comprador_Anuncios".equals(tipoUsuario)) {
                return Response.status(Response.Status.FORBIDDEN)
                        .entity("{\"message\":\"No tienes permisos para realizar esta acción.\"}")
                        .build();
            }

            return null;  // Si todo es correcto, retorna null

        } catch (JWTVerificationException | IllegalArgumentException | UnsupportedEncodingException e) {
            System.out.println("Error al verificar el token: " + e.getMessage());
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("{\"message\":\"Token inválido.\"}")
                    .build();
        }
    }
}

