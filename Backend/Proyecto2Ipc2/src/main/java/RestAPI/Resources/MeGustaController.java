/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RestAPI.Resources;

import Backend.DB.MeGustaDB;
import Modelos.MeGusta;
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

@Path("/megustas")
public class MeGustaController {

    private MeGustaDB meGustaDB = new MeGustaDB();

    @GET
    @Path("/{idRevista}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerMeGustaPorRevista(@PathParam("idRevista") int idRevista) {
        List<MeGusta> meGustas = meGustaDB.obtenerMeGustaPorRevista(idRevista);
        return Response.ok(meGustas).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response agregarMeGusta(MeGusta meGusta) {
        boolean creado = meGustaDB.agregarMeGusta(meGusta);
        if (creado) {
            return Response.status(Response.Status.CREATED).entity(meGusta).build();
        } else {
            // Verificar si el problema es que la revista no permite "Me Gusta"
            if (!meGustaDB.permiteMeGusta(meGusta.getIdRevista())) {
                return Response.status(Response.Status.FORBIDDEN).entity("La revista no permite 'Me Gusta'.").build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).entity("Error al agregar Me Gusta").build();
            }
        }
    }
}


