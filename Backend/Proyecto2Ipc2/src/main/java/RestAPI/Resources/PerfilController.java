/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RestAPI.Resources;

import Backend.DB.FileUtils;
import Backend.DB.PerfilDB;
import Modelos.Codificador;
import Modelos.JWTHelper;
import Modelos.TokenBlackList;
import Modelos.Usuario;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

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

    @PUT
    @Path("/actualizar")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarPerfil(
            @FormDataParam("nombre") String nombre,
            @FormDataParam("email") String email,
            @FormDataParam("descripcion") String descripcion,
            @FormDataParam("hobbies") String hobbies,
            @FormDataParam("temasInteres") String temasInteres,
            @FormDataParam("fotoPerfil") InputStream uploadedInputStream,
            @FormDataParam("fotoPerfil") FormDataContentDisposition fileDetails,
            @Context HttpHeaders headers) throws IllegalArgumentException, UnsupportedEncodingException {

        String token = getTokenFromHeaders(headers);

        if (TokenBlackList.isBlacklisted(token)) {
            System.out.println("Token está en la lista negra: " + token);
            return Response.status(Response.Status.UNAUTHORIZED).entity("Token inválido o expirado").build();
        }

        try {
            DecodedJWT jwt = JWTHelper.verifyToken(token);
            Integer idUsuario = jwt.getClaim("idUsuario").asInt();

            if (idUsuario == null) {
                return Response.status(Response.Status.UNAUTHORIZED).entity("ID de usuario no encontrado en el token").build();
            }

            byte[] fotoPerfilBytes = null;
            if (uploadedInputStream != null) {
                fotoPerfilBytes = FileUtils.inputStreamToByteArray(uploadedInputStream);
            }

            Usuario usuario = new Usuario();
            usuario.setIdUsuario(idUsuario);
            usuario.setNombre(nombre);
            usuario.setEmail(email);
            usuario.setFotoPerfil(fotoPerfilBytes);
            usuario.setDescripcion(descripcion);
            usuario.setHobbies(List.of(hobbies.split(",")));
            usuario.setTemasInteres(List.of(temasInteres.split(",")));

            boolean isUpdated = perfilDB.actualizarPerfilUsuario(usuario);
            if (isUpdated) {
                return Response.ok().entity("{\"message\":\"Perfil actualizado exitosamente\"}").type(MediaType.APPLICATION_JSON).build();
            } else {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"message\":\"Error al actualizar el perfil\"}").type(MediaType.APPLICATION_JSON).build();
            }
        } catch (IOException e) {
            System.out.println("Error al actualizar el perfil: " + e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"message\":\"Error al actualizar el perfil\"}").type(MediaType.APPLICATION_JSON).build();
        } catch (JWTVerificationException e) {
            System.out.println("Token inválido o expirado: " + e.getMessage());
            return Response.status(Response.Status.UNAUTHORIZED).entity("{\"message\":\"Token inválido o expirado\"}").type(MediaType.APPLICATION_JSON).build();
        }
    }

    @PUT
    @Path("/actualizarContraseña")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarContraseña(@Context HttpHeaders headers, Map<String, String> request) throws IllegalArgumentException, UnsupportedEncodingException {
        String token = getTokenFromHeaders(headers);

        // Verificar si el token está en la lista negra
        if (TokenBlackList.isBlacklisted(token)) {
            System.out.println("Token está en la lista negra: " + token);
            return Response.status(Response.Status.UNAUTHORIZED).entity("{\"message\":\"Token inválido o expirado\"}").type(MediaType.APPLICATION_JSON).build();
        }

        try {
            // Decodificar el token usando JWTHelper
            DecodedJWT jwt = JWTHelper.verifyToken(token);

            Integer idUsuario = jwt.getClaim("idUsuario").asInt();
            if (idUsuario == null) {
                return Response.status(Response.Status.UNAUTHORIZED).entity("{\"message\":\"ID de usuario no encontrado en el token\"}").type(MediaType.APPLICATION_JSON).build();
            }

            String nuevaContraseña = request.get("nuevaContraseña");

            // Codificar la nueva contraseña antes de actualizarla en la base de datos
            String contraseñaCodificada = Codificador.codificar(nuevaContraseña);

            boolean isUpdated = perfilDB.actualizarContraseñaUsuario(idUsuario, contraseñaCodificada);
            if (isUpdated) {
                return Response.ok().entity("{\"message\":\"Contraseña actualizada exitosamente\"}").type(MediaType.APPLICATION_JSON).build();
            } else {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"message\":\"Error al actualizar la contraseña\"}").type(MediaType.APPLICATION_JSON).build();
            }
        } catch (JWTVerificationException e) {
            System.out.println("Token inválido o expirado: " + e.getMessage());
            return Response.status(Response.Status.UNAUTHORIZED).entity("{\"message\":\"Token inválido o expirado\"}").type(MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            System.out.println("Error al actualizar la contraseña: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"message\":\"Error al actualizar la contraseña\"}").type(MediaType.APPLICATION_JSON).build();
        }
    }

}
