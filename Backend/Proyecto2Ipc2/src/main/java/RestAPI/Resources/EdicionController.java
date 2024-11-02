/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RestAPI.Resources;

import Backend.DB.EdicionDB;
import Backend.DB.FileUtils;
import Modelos.Edicion;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.OPTIONS;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Date;
import org.glassfish.jersey.media.multipart.FormDataParam;


/**
 *
 * @author herson
 */

@Path("ediciones")
public class EdicionController {
    private EdicionDB edicionDB = new EdicionDB();
    
    @OPTIONS 
    @Path("{idRevista}")
    public Response handlePreflight() { 
        return Response.ok() .header("Access-Control-Allow-Origin", "http://localhost:4200") 
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS") 
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization") 
                .build(); }
    

    
   @POST
   @Consumes(MediaType.MULTIPART_FORM_DATA)
   @Produces(MediaType.APPLICATION_JSON)
   public Response insertarEdicion(@FormDataParam("idRevista") int idRevista,
                                   @FormDataParam("tituloEdicion") String tituloEdicion,
                                   @FormDataParam("fechaCreacion") Date fechaCreacion,
                                   @FormDataParam("archivoPdf") InputStream uploadedInputStream,
                                   @FormDataParam("permiteComentarios") boolean permiteComentarios,
                                   @FormDataParam("permiteMegusta") boolean permiteMegusta) {

       try {
           byte[] archivoPdf = FileUtils.inputStreamToByteArray(uploadedInputStream);
           Edicion edicion = new Edicion(0, idRevista, tituloEdicion, fechaCreacion, "Activo", permiteComentarios, permiteMegusta, archivoPdf); // Estado fijado a 'Activo'
           boolean isInserted = edicionDB.insertarEdicion(edicion);

           if (isInserted) {
               return Response.status(Response.Status.CREATED).entity(edicion).build();
           } else {
               return Response.status(Response.Status.BAD_REQUEST).entity("Error al insertar la edición").build();
           }

       } catch (SQLException e) {
           e.printStackTrace();
           return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al procesar la solicitud").build();
       } catch (IOException e) {
           e.printStackTrace();
           return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al procesar la imagen").build();
       }
   }




//agregue este metodo para tratar de enviar el pdf correctamente
    @GET
    @Path("pdf/{idEdicion}")
    @Produces("application/pdf")
    public Response obtenerArchivoPdf(@PathParam("idEdicion") int idEdicion) {
        try {
            byte[] archivoPdf = edicionDB.obtenerArchivoPdfPorId(idEdicion);
            if (archivoPdf == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(archivoPdf, "application/pdf")
                    .header("Content-Disposition", "inline; filename=\"archivo.pdf\"")
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al obtener el archivo PDF").build();
        }
    }
}
