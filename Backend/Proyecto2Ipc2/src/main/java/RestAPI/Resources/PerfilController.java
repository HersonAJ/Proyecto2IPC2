/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RestAPI.Resources;

import Backend.DB.PerfilDB;
import Modelos.JWTHelper;
import Modelos.TokenBlackList;
import Modelos.Usuario;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 *
 * @author herson
 */

@Path("/perfil")
public class PerfilController {
    private PerfilDB perfilDB = new PerfilDB();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPerfil(@Context HttpHeaders headers) throws UnsupportedEncodingException {
        String token = getTokenFromHeaders(headers);
        
        // Verificar si el token está en la lista negra
        if (TokenBlackList.isBlacklisted(token)) {
            System.out.println("Token está en la lista negra: " + token);
            return Response.status(Response.Status.UNAUTHORIZED).entity("Token inválido o expirado").build();
        }

        try {
            // Decodificar el token usando JWTHelper
            DecodedJWT jwt = JWTHelper.verifyToken(token);
            String email = jwt.getClaim("email").asString(); // Obtener el claim "email" del token
            
            System.out.println("Decoded email: " + email);

            Usuario usuario = perfilDB.obtenerPerfilPorCorreo(email);
            if (usuario == null) {
                System.out.println("Usuario no encontrado para el correo proporcionado");
                return Response.status(Response.Status.UNAUTHORIZED).entity("Usuario no encontrado").build();
            }

            return Response.ok(usuario).build();
        } catch (JWTVerificationException e) {
            System.out.println("Token inválido o expirado: " + e.getMessage());
            return Response.status(Response.Status.UNAUTHORIZED).entity("Token inválido o expirado").build();
        }
    }

    private String getTokenFromHeaders(HttpHeaders headers) {
        List<String> authorizationHeaders = headers.getRequestHeader("Authorization");
        if (authorizationHeaders == null || authorizationHeaders.isEmpty()) {
            throw new IllegalArgumentException("Encabezado Authorization no proporcionado");
        }
        return authorizationHeaders.get(0).replace("Bearer ", "");
    }
}

