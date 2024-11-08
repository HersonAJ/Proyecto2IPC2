/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RestAPI.Resources;

import Backend.DB.AnuncioDB;
import Backend.DB.MisAnunciosDB;
import Backend.DB.TransaccionDB;
import Modelos.Anuncio;
import Modelos.AuthService;
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
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.PathParam;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.List;
import org.glassfish.jersey.media.multipart.FormDataParam;

/**
 *
 * @author herson
 */
@Path("/anuncio")
public class AnuncioController {

    private AnuncioDB anuncioDB = new AnuncioDB();
    private AuthService authService = new AuthService();
    private TransaccionDB transaccionDB = new TransaccionDB();
    private MisAnunciosDB misAnunciosDB = new MisAnunciosDB();

    @POST
    @Path("/crearTexto")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response crearAnuncioTexto(@HeaderParam("Authorization") String token, Anuncio anuncio) {
        Response authResponse = authService.validateTokenForCompradorAnuncios(token);
        if (authResponse != null) {
            return authResponse;
        }

        // Procesar compra del anuncio
        Response compraResponse = transaccionDB.procesarCompraAnuncio(anuncio.getIdUsuario(), "Texto", anuncio.getDuracion());
        if (compraResponse.getStatus() != Response.Status.OK.getStatusCode()) {
            return compraResponse;
        }

        try {
            anuncioDB.insertarAnuncioTexto(anuncio);
            return Response.status(Response.Status.CREATED)
                    .entity("{\"message\":\"Anuncio de texto creado correctamente.\"}")
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
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response crearAnuncioTextoImagen(@HeaderParam("Authorization") String token,
            @FormDataParam("tipo") String tipo,
            @FormDataParam("contenido") String contenido,
            @FormDataParam("imagen") InputStream imagenInputStream,
            @FormDataParam("video") String video,
            @FormDataParam("idUsuario") int idUsuario,
            @FormDataParam("fechaInicio") Date fechaInicio,
            @FormDataParam("fechaFin") Date fechaFin,
            @FormDataParam("estado") String estado) {

        Response authResponse = authService.validateTokenForCompradorAnuncios(token);
        if (authResponse != null) {
            return authResponse;
        }

        // Crear objeto Anuncio antes de procesar la compra
        Anuncio anuncio = new Anuncio();
        anuncio.setTipo(tipo);
        anuncio.setContenido(contenido);
        anuncio.setVideo(video);
        anuncio.setIdUsuario(idUsuario);
        anuncio.setFechaInicio(fechaInicio);
        anuncio.setFechaFin(fechaFin);
        anuncio.setEstado(estado);

        // Procesar compra del anuncio
        Response compraResponse = transaccionDB.procesarCompraAnuncio(idUsuario, "Texto e Imagen", anuncio.getDuracion());
        if (compraResponse.getStatus() != Response.Status.OK.getStatusCode()) {
            return compraResponse;
        }

        try {
            byte[] imagenBytes = imagenInputStream.readAllBytes();
            anuncio.setImagen(imagenBytes);

            anuncioDB.insertarAnuncioTextoImagen(anuncio);

            return Response.status(Response.Status.CREATED)
                    .entity("{\"message\":\"Anuncio de texto e imagen creado correctamente.\"}")
                    .build();
        } catch (IOException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"message\":\"Error al leer la imagen.\"}")
                    .build();
        }
    }

    @POST
    @Path("/crearVideo")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response crearAnuncioVideo(@HeaderParam("Authorization") String token, Anuncio anuncio) {
        Response authResponse = authService.validateTokenForCompradorAnuncios(token);
        if (authResponse != null) {
            return authResponse;
        }

        // Procesar compra del anuncio
        Response compraResponse = transaccionDB.procesarCompraAnuncio(anuncio.getIdUsuario(), "Video", anuncio.getDuracion());
        if (compraResponse.getStatus() != Response.Status.OK.getStatusCode()) {
            return compraResponse;
        }

        try {
            anuncioDB.insertarAnuncioVideo(anuncio);
            return Response.status(Response.Status.CREATED)
                    .entity("{\"message\":\"Anuncio de video creado correctamente.\"}")
                    .build();
        } catch (Exception e) {
            System.out.println("Error al crear anuncio de video: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"message\":\"Error al crear anuncio de video.\"}")
                    .build();
        }
    }

    @GET
    @Path("/misAnuncios/{idUsuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerAnunciosPorUsuario(@PathParam("idUsuario") int idUsuario) {
        try {
            List<Anuncio> anuncios = misAnunciosDB.obtenerAnunciosPorUsuario(idUsuario);
            if (anuncios.isEmpty()) {
                return Response.status(Response.Status.NO_CONTENT).entity("{\"message\":\"No hay anuncios para este usuario.\"}").build();
            }
            return Response.status(Response.Status.OK).entity(anuncios).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"message\":\"Error inesperado al obtener anuncios.\"}").build();
        }
    }

    //metodo para obtener anuncios desde la base de datos
    @GET
    @Path("/anunciosActivos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerAnunciosActivos() {
        try {
            List<Anuncio> anuncios = anuncioDB.obtenerAnunciosActivos();
            if (anuncios.isEmpty()) {
                return Response.status(Response.Status.NO_CONTENT).entity("{\"message\":\"No hay anuncios activos.\"}").build();
            }
            return Response.status(Response.Status.OK).entity(anuncios).build();
        } catch (Exception e) {
            System.out.println("Error al obtener anuncios activos: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"message\":\"Error al obtener anuncios activos.\"}").build();
        }
    }

    //metodo para actualizar estado de los anuncios
    @PUT
    @Path("/{idAnuncio}/estado/{nuevoEstado}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cambiarEstadoAnuncio(@PathParam("idAnuncio") int idAnuncio, @PathParam("nuevoEstado") String nuevoEstado) {
        try {
            // Verificar que el nuevo estado es válido
            if (!nuevoEstado.equals("Activo") && !nuevoEstado.equals("Inactivo")) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Estado inválido").build();
            }

            anuncioDB.cambiarEstadoAnuncio(idAnuncio, nuevoEstado);
            return Response.ok().entity("Estado del anuncio actualizado exitosamente").build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al actualizar el estado del anuncio").build();
        }
    }
}
