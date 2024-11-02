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
        List<Edicion> ediciones = edicionDB.obtenerEdicionesPorRevista(idRevista);
        return Response.ok(ediciones).build();
    }
    
    
    
    //metodos para cambiar el estado de las revistas
    @PUT
    @Path("/{idRevista}/estado")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cambiarEstadoRevista(@PathParam("idRevista") int idRevista, CambiarEstadoRequest request) {
        RevistaDB revistaDB = new RevistaDB();

        boolean isUpdated = revistaDB.cambiarEstadoRevista(idRevista, request.getEstado());

        if (isUpdated) {
            return Response.status(Response.Status.OK).entity("{\"message\":\"Estado de la revista actualizado exitosamente.\"}").build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"message\":\"Error al actualizar el estado de la revista.\"}").build();
        }
    }

    @PUT
    @Path("/ediciones/{idEdicion}/estado")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cambiarEstadoEdicion(@PathParam("idEdicion") int idEdicion, CambiarEstadoRequest request) {
        boolean isUpdated = edicionDB.cambiarEstadoEdicion(idEdicion, request.getEstado());

        if (isUpdated) {
            return Response.status(Response.Status.OK).entity("{\"message\":\"Estado de la edición actualizado exitosamente.\"}").build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"message\":\"Error al actualizar el estado de la edición.\"}").build();
        }
    }

}

    
    

