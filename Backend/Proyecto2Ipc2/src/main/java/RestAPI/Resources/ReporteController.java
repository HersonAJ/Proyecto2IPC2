/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RestAPI.Resources;


import Backend.DB.ReportesDB;
import Modelos.AuthService;
import Modelos.RevistaPopularAdmin;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
/**
 *
 * @author herson
 */


@Path("/reporte")
public class ReporteController {

    private ReportesDB reportesDB = new ReportesDB();
    private AuthService authService = new AuthService();

    @GET
    @Path("/revistasMasPopulares")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerRevistasMasPopulares(
            @HeaderParam("Authorization") String token,
            @QueryParam("fechaInicio") String fechaInicio,
            @QueryParam("fechaFin") String fechaFin) {

        System.out.println("Request recibida para obtener las revistas más populares");
        System.out.println("Token recibido: " + token);

        Response authResponse = authService.validateToken(token);
        if (authResponse != null) {
            return authResponse;
        }

        try {
            // Obtener las revistas más populares en el intervalo de tiempo
            List<RevistaPopularAdmin> revistasPopulares = reportesDB.obtenerRevistasMasPopulares(fechaInicio, fechaFin);
            return Response.status(Response.Status.OK)
                    .entity(revistasPopulares)
                    .build();
        } catch (Exception e) {
            System.out.println("Error al obtener las revistas más populares: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"message\":\"Error al obtener las revistas más populares.\"}")
                    .build();
        }
    }

}
