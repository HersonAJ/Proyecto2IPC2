/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RestAPI.Resources;

import Backend.DB.EdicionDB;
import Backend.DB.RevistaDB;
import Modelos.Edicion;
import Modelos.Revista;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
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

@Path("/revistas")
public class RevistaController {
    private EdicionDB edicionDB = new EdicionDB();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response crearRevista(Revista revista) {
        System.out.println("Llamada a crearRevista con revista: " + revista.getTitulo());
        RevistaDB revistaDB = new RevistaDB();
        
        boolean isInserted = revistaDB.insertRevista(revista);
        
        if (isInserted) {
            return Response.status(Response.Status.CREATED).entity("{\"message\":\"Revista creada exitosamente\"}").build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"message\":\"Error al crear la revista\"}").build();
        }
    }
    
    @GET
    @Path("/{idUsuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRevistaPorUsuario(@PathParam("idUsuario") int idUsuario) {
        System.out.println("Llamada a getRevistaPorUsuario con idUsuario: " + idUsuario);
        RevistaDB revistaDB = new RevistaDB();
        List<Revista> revistas = revistaDB.getRevistasPorUsuario(idUsuario);
        if(revistas != null) {
            return Response.ok(revistas).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("{\"message\":\"No se encontraron revistas para este usuario\"}").build();
        }
    }

    @GET
    @Path("{idRevista}/ediciones")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerEdicionesPorRevista(@PathParam("idRevista") int idRevista) {
        System.out.println("Llamada a obtenerEdicionesPorRevista con idRevista: " + idRevista);
        List<Edicion> ediciones = edicionDB.obtenerEdicionesPorRevista(idRevista);
        return Response.ok(ediciones).build();
    }
    //cambia el estado de la revista
    @PUT
    @Path("/{idRevista}/estado")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarRevista(@PathParam("idRevista") int idRevista) {
        System.out.println("Llamada a eliminarRevista con idRevista: " + idRevista);
        RevistaDB revistaDB = new RevistaDB();

        boolean isUpdated = revistaDB.eliminarRevista(idRevista, "Inactivo");
        System.out.println("Resultado de eliminarRevista: " + isUpdated);

        if (isUpdated) {
            return Response.status(Response.Status.OK).entity("{\"message\":\"Revista eliminada exitosamente.\"}").build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"message\":\"Error al eliminar la revista.\"}").build();
        }
    }


    @PUT
    @Path("/{idRevista}/comentarios")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarPermiteComentarios(@PathParam("idRevista") int idRevista, @QueryParam("permiteComentarios") boolean permiteComentarios) {
        System.out.println("Llamada a actualizarPermiteComentarios con idRevista: " + idRevista + " y permiteComentarios: " + permiteComentarios);
        RevistaDB revistaDB = new RevistaDB();
        
        boolean isUpdated = revistaDB.actualizarPermiteComentarios(idRevista, permiteComentarios);
        
        if (isUpdated) {
            return Response.status(Response.Status.OK).entity("{\"message\":\"Permite Comentarios actualizado exitosamente.\"}").build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"message\":\"Error al actualizar Permite Comentarios.\"}").build();
        }
    }

    @PUT
    @Path("/{idRevista}/megusta")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarPermiteMeGusta(@PathParam("idRevista") int idRevista, @QueryParam("permiteMeGusta") boolean permiteMeGusta) {
        System.out.println("Llamada a actualizarPermiteMeGusta con idRevista: " + idRevista + " y permiteMeGusta: " + permiteMeGusta);
        RevistaDB revistaDB = new RevistaDB();
        
        boolean isUpdated = revistaDB.actualizarPermiteMeGusta(idRevista, permiteMeGusta);
        
        if (isUpdated) {
            return Response.status(Response.Status.OK).entity("{\"message\":\"Permite Me Gusta actualizado exitosamente.\"}").build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"message\":\"Error al actualizar Permite Me Gusta.\"}").build();
        }
    }

    @PUT
    @Path("/{idRevista}/suscripcion")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarPermiteSuscripcion(@PathParam("idRevista") int idRevista, @QueryParam("permiteSuscripcion") boolean permiteSuscripcion) {
        System.out.println("Llamada a actualizarPermiteSuscripcion con idRevista: " + idRevista + " y permiteSuscripcion: " + permiteSuscripcion);
        RevistaDB revistaDB = new RevistaDB();

        boolean isUpdated = revistaDB.actualizarPermiteSuscripcion(idRevista, permiteSuscripcion);

        if (isUpdated) {
            return Response.status(Response.Status.OK).entity("{\"message\":\"Permite Suscripción actualizado exitosamente.\"}").build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"message\":\"Error al actualizar Permite Suscripción.\"}").build();
        }
    }


}