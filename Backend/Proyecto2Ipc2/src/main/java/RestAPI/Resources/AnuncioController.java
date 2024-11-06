/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RestAPI.Resources;

import Backend.DB.AnuncioDB;
import Modelos.Anuncio;
import Modelos.JWTHelper;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.exceptions.JWTVerificationException;
import java.io.UnsupportedEncodingException;
/**
 *
 * @author herson
 */

@Path("/anuncio")
public class AnuncioController {

    private AnuncioDB anuncioDB = new AnuncioDB();

    private boolean validarAnunciante(String token) throws JWTVerificationException, UnsupportedEncodingException {
        DecodedJWT jwt = JWTHelper.verifyToken(token);
        String tipoUsuario = jwt.getClaim("tipoUsuario").asString();
        return "Comprador_Anuncios".equals(tipoUsuario);
    }

    @POST
    @Path("/crearTexto")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response crearAnuncioTexto(@HeaderParam("Authorization") String token, Anuncio anuncio) {
        try {
            // Eliminar el prefijo "Bearer "
            if (token.startsWith("Bearer ")) {
                token = token.substring(7);
            }

            if (!validarAnunciante(token)) {
                return Response.status(Response.Status.FORBIDDEN)
                        .entity("{\"message\":\"No tienes permisos para realizar esta acción.\"}")
                        .build();
            }

            anuncioDB.insertarAnuncioTexto(anuncio);
            return Response.status(Response.Status.CREATED)
                    .entity("{\"message\":\"Anuncio de texto creado correctamente.\"}")
                    .build();

        } catch (JWTVerificationException | IllegalArgumentException | UnsupportedEncodingException e) {
            System.out.println("Error al verificar el token: " + e.getMessage());
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("{\"message\":\"Token inválido.\"}")
                    .build();
        } catch (Exception e) {
            System.out.println("Error al crear anuncio de texto: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"message\":\"Error al crear anuncio de texto.\"}")
                    .build();
        }
    }

    @POST
    @Path("/crearTextoImagen")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response crearAnuncioTextoImagen(@HeaderParam("Authorization") String token, Anuncio anuncio) {
        try {
            // Eliminar el prefijo "Bearer "
            if (token.startsWith("Bearer ")) {
                token = token.substring(7);
            }

            if (!validarAnunciante(token)) {
                return Response.status(Response.Status.FORBIDDEN)
                        .entity("{\"message\":\"No tienes permisos para realizar esta acción.\"}")
                        .build();
            }

            anuncioDB.insertarAnuncioTextoImagen(anuncio);
            return Response.status(Response.Status.CREATED)
                    .entity("{\"message\":\"Anuncio de texto e imagen creado correctamente.\"}")
                    .build();

        } catch (JWTVerificationException | IllegalArgumentException | UnsupportedEncodingException e) {
            System.out.println("Error al verificar el token: " + e.getMessage());
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("{\"message\":\"Token inválido.\"}")
                    .build();
        } catch (Exception e) {
            System.out.println("Error al crear anuncio de texto e imagen: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"message\":\"Error al crear anuncio de texto e imagen.\"}")
                    .build();
        }
    }

    @POST
    @Path("/crearVideo")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response crearAnuncioVideo(@HeaderParam("Authorization") String token, Anuncio anuncio) {
        try {
            // Eliminar el prefijo "Bearer "
            if (token.startsWith("Bearer ")) {
                token = token.substring(7);
            }

            if (!validarAnunciante(token)) {
                return Response.status(Response.Status.FORBIDDEN)
                        .entity("{\"message\":\"No tienes permisos para realizar esta acción.\"}")
                        .build();
            }

            anuncioDB.insertarAnuncioVideo(anuncio);
            return Response.status(Response.Status.CREATED)
                    .entity("{\"message\":\"Anuncio de video creado correctamente.\"}")
                    .build();

        } catch (JWTVerificationException | IllegalArgumentException | UnsupportedEncodingException e) {
            System.out.println("Error al verificar el token: " + e.getMessage());
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("{\"message\":\"Token inválido.\"}")
                    .build();
        } catch (Exception e) {
            System.out.println("Error al crear anuncio de video: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"message\":\"Error al crear anuncio de video.\"}")
                    .build();
        }
    }
}

