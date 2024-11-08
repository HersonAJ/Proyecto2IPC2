/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RestAPI.Resources;

/**
 *
 * @author herson
 */

import Backend.DB.CategoriaDB;
import Modelos.Revista;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

@Path("/categorias")
public class CategoriaController {

    private CategoriaDB categoriasDB = new CategoriaDB();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerRevistasPorCategoria() {
        try {
            Map<String, List<Revista>> revistasPorCategoria = categoriasDB.obtenerRevistasPorCategoria();
            if (revistasPorCategoria.isEmpty()) {
                return Response.status(Response.Status.NO_CONTENT).entity("{\"message\":\"No hay revistas disponibles.\"}").build();
            }
            return Response.status(Response.Status.OK).entity(revistasPorCategoria).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"message\":\"Error al obtener las revistas por categorías.\"}").build();
        }
    }
}

