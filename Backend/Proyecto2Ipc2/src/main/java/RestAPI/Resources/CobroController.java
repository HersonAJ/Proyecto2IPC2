/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RestAPI.Resources;

import Backend.DB.CobroDB;
import Modelos.AuthService;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.MediaType;

/**
 *
 * @author herson
 */
@Path("/cobro")
public class CobroController {

    private CobroDB cobroDB = new CobroDB();
    private AuthService authService = new AuthService();

    @PUT
    @Path("/actualizarCostoDiario/{nuevoCostoPorDia}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarCostoDiario(@HeaderParam("Authorization") String token, @PathParam("nuevoCostoPorDia") double nuevoCostoPorDia) {
        System.out.println("Request recibida para actualizar costo diario");
        System.out.println("Token recibido: " + token);

        Response authResponse = authService.validateToken(token);
        if (authResponse != null) {
            return authResponse;
        }

        try {
            // Actualizar el costo diario de las revistas
            cobroDB.actualizarCostoDiario(nuevoCostoPorDia);
            return Response.status(Response.Status.OK)
                    .entity("{\"message\":\"Costo diario actualizado correctamente.\"}")
                    .build();
        } catch (Exception e) {
            System.out.println("Error al actualizar el costo diario: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"message\":\"Error al actualizar el costo diario.\"}")
                    .build();
        }
    }

    @PUT
    @Path("/procesarCobroDiario")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response procesarCobroDiario(@HeaderParam("Authorization") String token) {
        System.out.println("Request recibida para procesar cobro diario");
        System.out.println("Token recibido: " + token);

        Response authResponse = authService.validateToken(token);
        if (authResponse != null) {
            return authResponse;
        }

        try {
            // Procesar el cobro diario de las revistas
            cobroDB.procesarCobroDiario();
            return Response.status(Response.Status.OK)
                    .entity("{\"message\":\"Cobro diario procesado correctamente.\"}")
                    .build();
        } catch (Exception e) {
            System.out.println("Error al procesar el cobro diario: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"message\":\"Error al procesar el cobro diario.\"}")
                    .build();
        }
    }

    @GET
    @Path("/contarRevistasActivas")
    @Produces(MediaType.APPLICATION_JSON)
    public Response contarRevistasActivas(@HeaderParam("Authorization") String token) {
        System.out.println("Request recibida para contar revistas activas");
        System.out.println("Token recibido: " + token);

        Response authResponse = authService.validateToken(token);
        if (authResponse != null) {
            return authResponse;
        }

        try {
            // Contar las revistas activas
            int cantidadRevistas = cobroDB.contarRevistasActivas();
            return Response.status(Response.Status.OK)
                    .entity("{\"cantidadRevistas\": " + cantidadRevistas + "}")
                    .build();
        } catch (Exception e) {
            System.out.println("Error al contar las revistas activas: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"message\":\"Error al contar las revistas activas.\"}")
                    .build();
        }
    }

    @GET
    @Path("/obtenerCostoDiarioAsignado")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerCostoDiarioAsignado(@HeaderParam("Authorization") String token) {
        System.out.println("Request recibida para obtener costo diario asignado");
        System.out.println("Token recibido: " + token);

        Response authResponse = authService.validateToken(token);
        if (authResponse != null) {
            return authResponse;
        }

        try {
            // Obtener el costo diario asignado
            double costoDiario = cobroDB.obtenerCostoDiarioAsignado();
            return Response.status(Response.Status.OK)
                    .entity("{\"costoDiario\": " + costoDiario + "}")
                    .build();
        } catch (Exception e) {
            System.out.println("Error al obtener el costo diario asignado: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"message\":\"Error al obtener el costo diario asignado.\"}")
                    .build();
        }
    }

    @PUT
    @Path("/fijarPrecioAnuncio")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response fijarPrecioAnuncio(@HeaderParam("Authorization") String token, @QueryParam("tipo") String tipo, @QueryParam("duracion") String duracion, @QueryParam("precio") double precio) {
        System.out.println("Request recibida para fijar precio anuncio");
        System.out.println("Token recibido: " + token);

        Response authResponse = authService.validateToken(token);
        if (authResponse != null) {
            return authResponse;
        }

        try {
            // Fijar el precio del anuncio
            cobroDB.fijarPrecioAnuncio(tipo, duracion, precio);
            return Response.status(Response.Status.OK)
                    .entity("{\"message\":\"Precio del anuncio fijado correctamente.\"}")
                    .build();
        } catch (Exception e) {
            System.out.println("Error al fijar el precio del anuncio: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"message\":\"Error al fijar el precio del anuncio.\"}")
                    .build();
        }
    }

    @GET
    @Path("/obtenerPrecioAnuncio")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerPrecioAnuncio(@HeaderParam("Authorization") String token, @QueryParam("tipo") String tipo, @QueryParam("duracion") String duracion) {
        System.out.println("Request recibida para obtener precio del anuncio");
        System.out.println("Token recibido: " + token);

        Response authResponse = authService.validateToken(token);
        if (authResponse != null) {
            return authResponse;
        }

        try {
            // Obtener el precio del anuncio
            double precio = cobroDB.obtenerPrecioAnuncio(tipo, duracion);
            return Response.status(Response.Status.OK)
                    .entity("{\"precio\": " + precio + "}")
                    .build();
        } catch (Exception e) {
            System.out.println("Error al obtener el precio del anuncio: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"message\":\"Error al obtener el precio del anuncio.\"}")
                    .build();
        }
    }
}
