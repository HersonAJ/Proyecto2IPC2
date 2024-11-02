/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RestAPI.Resources;

import Backend.DB.SaldoCarteraDB;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author herson
 */

@Path("/cartera")
public class CarteraController {

    private SaldoCarteraDB saldoCarteraDB = new SaldoCarteraDB();

    @POST
    @Path("/agregar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response agregarSaldo(AgregarSaldoRequest request) {
        try {
            boolean success = saldoCarteraDB.agregarSaldo(request.getIdUsuario(), request.getMonto());
            if (success) {
                return Response.status(Response.Status.OK).entity("{\"message\":\"Saldo agregado exitosamente.\"}").build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).entity("{\"message\":\"No se pudo agregar el saldo.\"}").build();
            }
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("{\"message\":\"" + e.getMessage() + "\"}").build();
        }
    }
}

