/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;


import Backend.DB.ReporteComentarioDB;
import Backend.DB.ReporteMeGustaDB;
import Backend.DB.ReporteSuscripcionDB;
import Modelos.Comentario;
import Modelos.MeGusta;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author herson
 */


@Path("/reportesEditor")
public class ReportesEditorController {

    private ReporteComentarioDB reporteComentarioDB = new ReporteComentarioDB();
    private ReporteSuscripcionDB reporteSuscripcionDB = new ReporteSuscripcionDB();
    private ReporteMeGustaDB reporteMeGustaDB = new ReporteMeGustaDB();

    // Endpoint para obtener todos los comentarios de todas las revistas de un usuario en un intervalo de tiempo
    @GET
    @Path("/comentariosPorUsuario")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerComentariosPorUsuario(@QueryParam("idUsuario") int idUsuario,
                                                 @QueryParam("fechaInicio") Date fechaInicio,
                                                 @QueryParam("fechaFin") Date fechaFin) {
        try {
            List<Comentario> comentarios = reporteComentarioDB.obtenerComentariosPorUsuarioYFecha(idUsuario, fechaInicio, fechaFin);
            if (comentarios.isEmpty()) {
                return Response.status(Response.Status.NO_CONTENT).entity("{\"message\":\"No hay comentarios en este intervalo de tiempo.\"}").build();
            }
            return Response.status(Response.Status.OK).entity(comentarios).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"message\":\"Error al obtener los comentarios.\"}").build();
        }
    }

    // Endpoint para obtener todos los comentarios de una revista específica en un intervalo de tiempo
    @GET
    @Path("/comentariosPorRevista")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerComentariosPorRevista(@QueryParam("idRevista") int idRevista,
                                                 @QueryParam("fechaInicio") Date fechaInicio,
                                                 @QueryParam("fechaFin") Date fechaFin) {
        try {
            List<Comentario> comentarios = reporteComentarioDB.obtenerComentariosPorRevistaYFecha(idRevista, fechaInicio, fechaFin);
            if (comentarios.isEmpty()) {
                return Response.status(Response.Status.NO_CONTENT).entity("{\"message\":\"No hay comentarios en este intervalo de tiempo.\"}").build();
            }
            return Response.status(Response.Status.OK).entity(comentarios).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"message\":\"Error al obtener los comentarios.\"}").build();
        }
    }

    // Endpoint para obtener todas las suscripciones de todas las revistas de un usuario en un intervalo de tiempo
    @GET
    @Path("/suscripcionesPorUsuario")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerSuscripcionesPorUsuario(@QueryParam("idUsuario") int idUsuario,
                                                   @QueryParam("fechaInicio") Date fechaInicio,
                                                   @QueryParam("fechaFin") Date fechaFin) {
        try {
            List<Suscripcion> suscripciones = reporteSuscripcionDB.obtenerSuscripcionesPorUsuarioYFecha(idUsuario, fechaInicio, fechaFin);
            if (suscripciones.isEmpty()) {
                return Response.status(Response.Status.NO_CONTENT).entity("{\"message\":\"No hay suscripciones en este intervalo de tiempo.\"}").build();
            }
            return Response.status(Response.Status.OK).entity(suscripciones).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"message\":\"Error al obtener las suscripciones.\"}").build();
        }
    }

    // Endpoint para obtener todas las suscripciones de una revista específica en un intervalo de tiempo
    @GET
    @Path("/suscripcionesPorRevista")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerSuscripcionesPorRevista(@QueryParam("idRevista") int idRevista,
                                                   @QueryParam("fechaInicio") Date fechaInicio,
                                                   @QueryParam("fechaFin") Date fechaFin) {
        try {
            List<Suscripcion> suscripciones = reporteSuscripcionDB.obtenerSuscripcionesPorRevistaYFecha(idRevista, fechaInicio, fechaFin);
            if (suscripciones.isEmpty()) {
                return Response.status(Response.Status.NO_CONTENT).entity("{\"message\":\"No hay suscripciones en este intervalo de tiempo.\"}").build();
            }
            return Response.status(Response.Status.OK).entity(suscripciones).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"message\":\"Error al obtener las suscripciones.\"}").build();
        }
    }

    // Endpoint para obtener las 5 revistas más gustadas en un intervalo de tiempo
    @GET
    @Path("/meGustas")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerMeGustasPorFecha(@QueryParam("fechaInicio") Date fechaInicio,
                                            @QueryParam("fechaFin") Date fechaFin) {
        try {
            List<MeGusta> meGustas = reporteMeGustaDB.obtenerMeGustasPorFecha(fechaInicio, fechaFin);
            if (meGustas.isEmpty()) {
                return Response.status(Response.Status.NO_CONTENT).entity("{\"message\":\"No hay me gusta en este intervalo de tiempo.\"}").build();
            }
            return Response.status(Response.Status.OK).entity(meGustas).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"message\":\"Error al obtener los me gusta.\"}").build();
        }
    }

    // Endpoint para obtener todas las veces que se ha dado Me Gusta a una revista específica en un intervalo de tiempo
    @GET
    @Path("/meGustasPorRevista")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerMeGustasPorRevista(@QueryParam("idRevista") int idRevista,
                                              @QueryParam("fechaInicio") Date fechaInicio,
                                              @QueryParam("fechaFin") Date fechaFin) {
        try {
            List<MeGusta> meGustas = reporteMeGustaDB.obtenerMeGustasPorRevistaYFecha(idRevista, fechaInicio, fechaFin);
            if (meGustas.isEmpty()) {
                return Response.status(Response.Status.NO_CONTENT).entity("{\"message\":\"No hay me gusta en este intervalo de tiempo.\"}").build();
            }
            return Response.status(Response.Status.OK).entity(meGustas).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"message\":\"Error al obtener los me gusta.\"}").build();
        }
    }
}


