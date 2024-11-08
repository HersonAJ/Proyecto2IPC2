/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RestAPI.Resources;

import Backend.DB.ComentarioDB;
import Modelos.Comentario;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 *
 * @author herson
 */

@Path("/comentarios")
public class ComentarioController {

    private ComentarioDB comentarioDB = new ComentarioDB();

    @GET
    @Path("/{idRevista}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerComentariosPorRevista(@PathParam("idRevista") int idRevista) {
        List<Comentario> comentarios = comentarioDB.obtenerComentariosPorRevista(idRevista);
        return Response.ok(comentarios).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response agregarComentario(Comentario comentario) {
        System.out.println("Se agrego un comentario");
        boolean creado = comentarioDB.agregarComentario(comentario);
        if (creado) {
            return Response.status(Response.Status.CREATED).entity(comentario).build();
        } else {
            // Verificar si el problema es que la revista no permite comentarios
            if (!comentarioDB.permiteComentarios(comentario.getIdRevista())) {
                return Response.status(Response.Status.FORBIDDEN).entity("La revista no permite comentarios.").build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).entity("Error al crear el comentario").build();
            }
        }
    }
}


