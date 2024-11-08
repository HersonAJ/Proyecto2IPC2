/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RestAPI.Resources;

/**
 *
 * @author herson
 */
import Backend.DB.PerfilesEditoresDB;
import Modelos.Usuario;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/perfilesEditores")
public class PerfilesEditoresController {

    private PerfilesEditoresDB perfilesEditoresDB = new PerfilesEditoresDB();

    // Endpoint para obtener todos los usuarios que son Editores
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerEditores() {
        try {
            List<Usuario> editores = perfilesEditoresDB.obtenerEditores();
            if (editores.isEmpty()) {
                return Response.status(Response.Status.NO_CONTENT).entity("{\"message\":\"No hay editores disponibles.\"}").build();
            }
            return Response.status(Response.Status.OK).entity(editores).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"message\":\"Error al obtener los editores.\"}").build();
        }
    }
}
