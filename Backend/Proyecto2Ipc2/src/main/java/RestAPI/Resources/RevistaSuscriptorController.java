/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RestAPI.Resources;

import Backend.DB.RevistaSuscriptorDB;
import Modelos.Revista;
import Modelos.Suscripcion;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 *
 * @author herson
 */

@Path("/revistas-suscriptor")
public class RevistaSuscriptorController {

    private RevistaSuscriptorDB revistaSuscriptorDB = new RevistaSuscriptorDB();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTodasLasRevistas() {
        List<Revista> revistas = revistaSuscriptorDB.getTodasLasRevistas();
        if (revistas != null && !revistas.isEmpty()) {
            return Response.ok(revistas).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("{\"message\":\"No se encontraron revistas.\"}").build();
        }
    }

    @GET
    @Path("/categoria/{categoria}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRevistasPorCategoria(@PathParam("categoria") String categoria) {
        List<Revista> revistas = revistaSuscriptorDB.getRevistasPorCategoria(categoria);
        if (revistas != null && !revistas.isEmpty()) {
            return Response.ok(revistas).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("{\"message\":\"No se encontraron revistas para la categoría especificada.\"}").build();
        }
    }

    @GET
    @Path("/tag/{tag}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRevistasPorTag(@PathParam("tag") String tag) {
        List<Revista> revistas = revistaSuscriptorDB.getRevistasPorTag(tag);
        if (revistas != null && !revistas.isEmpty()) {
            return Response.ok(revistas).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("{\"message\":\"No se encontraron revistas para el tag especificado.\"}").build();
        }
    }

    
    @POST
    @Path("/{idRevista}/suscribirse")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response suscribirseRevista(@PathParam("idRevista") int idRevista, Suscripcion request) {
        // Log para verificar parámetros de entrada
        System.out.println("Llamada a suscribirseRevista con idRevista: " + idRevista + " y idUsuario: " + request.getIdUsuario());

       
        RevistaSuscriptorDB revistaSuscriptorDB = new RevistaSuscriptorDB();

        // Llamada a la base de datos para verificar si el usuario ya está suscrito
        boolean isSubscribed = revistaSuscriptorDB.suscribirseRevista(request.getIdUsuario(), idRevista, request.getFechaSuscripcion());

        if (isSubscribed) {
            // Respuesta exitosa
            return Response.status(Response.Status.OK)
                           .entity("{\"message\":\"Suscripción realizada exitosamente.\"}")
                           .build();
        } else {
            // Respuesta en caso de conflicto: ya suscrito
            return Response.status(Response.Status.CONFLICT)
                           .entity("{\"message\":\"El usuario ya está suscrito a esta revista.\"}")
                           .build();
        }
    }

    //metodo para exponer las revistas por suscripcion
    @GET
    @Path("/suscritas/{idUsuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRevistasSuscritasPorUsuario(@PathParam("idUsuario") int idUsuario) {
        List<Revista> revistas = revistaSuscriptorDB.getRevistasSuscritasPorUsuario(idUsuario);
        if (revistas != null && !revistas.isEmpty()) {
            return Response.ok(revistas).build();
        } else {
            return Response.status(Response.Status.OK).entity("{\"message\":\"No se encontraron revistas suscritas para este usuario.\"}").build();
        }
    }


}
