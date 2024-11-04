/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RestAPI.Resources;

import Backend.DB.SaldoCarteraGlobal;
import Modelos.JWTHelper;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author herson
 */


@Path("carteraGlobal")
public class CarteraGlobalController {

    private SaldoCarteraGlobal saldoCarteraGlobal = new SaldoCarteraGlobal();

    @POST
    @Path("/agregar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response agregarSaldo(@HeaderParam("Authorization") String token, AgregarSaldoRequest request) {
        System.out.println("Request recibida para agregar saldo");
        System.out.println("Token recibido: " + token);
        try {
            // Eliminar el prefijo "Bearer "
            if (token.startsWith("Bearer ")) {
                token = token.substring(7);
            }

            System.out.println("Verificando token...");
            DecodedJWT jwt = JWTHelper.verifyToken(token);
            System.out.println("Token verificado");

            // Obtener el tipo de usuario del token
            String tipoUsuario = jwt.getClaim("tipoUsuario").asString();
            System.out.println("Tipo de usuario: " + tipoUsuario);

            // Validar que el usuario es administrador
            if (!"Administrador".equals(tipoUsuario)) {
                return Response.status(Response.Status.FORBIDDEN)
                        .entity("{\"message\":\"No tienes permisos para realizar esta acción.\"}")
                        .build();
            }

            // Agregar el saldo a la cartera global
            boolean success = saldoCarteraGlobal.agregarSaldo(request.getMonto());
            if (success) {
                return Response.status(Response.Status.OK)
                        .entity("{\"message\":\"Saldo agregado exitosamente.\"}")
                        .build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("{\"message\":\"No se pudo agregar el saldo.\"}")
                        .build();
            }
        } catch (JWTVerificationException | IllegalArgumentException | UnsupportedEncodingException e) {
            System.out.println("Error al verificar el token: " + e.getMessage());
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("{\"message\":\"Token inválido.\"}")
                    .build();
        }
    }

    @GET
    @Path("/saldo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerSaldo(@HeaderParam("Authorization") String token) {
        System.out.println("Request recibida para obtener saldo");
        System.out.println("Token recibido: " + token);
        try {
            // Eliminar el prefijo "Bearer "
            if (token.startsWith("Bearer ")) {
                token = token.substring(7);
            }

            System.out.println("Verificando token...");
            DecodedJWT jwt = JWTHelper.verifyToken(token);
            System.out.println("Token verificado");

            // Obtener el saldo de la cartera global
            double saldo = saldoCarteraGlobal.obtenerSaldo();
            return Response.status(Response.Status.OK)
                    .entity("{\"saldo\":" + saldo + "}")
                    .build();

        } catch (JWTVerificationException | IllegalArgumentException | UnsupportedEncodingException e) {
            System.out.println("Error al verificar el token: " + e.getMessage());
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("{\"message\":\"Token inválido.\"}")
                    .build();
        }
    }
}



