/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RestAPI.Resources;

import Modelos.TokenBlackList;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author herson
 */
@Path("/logout")
public class LogoutController {
    private static final String SECRET_KEY = "secretKey";

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON) // Asegúrate de producir JSON
    public Response logout(@Context HttpHeaders headers) {
        String token = headers.getRequestHeader("Authorization").get(0).replace("Bearer ", "");

        // Agrega el token a la lista negra
        TokenBlackList.add(token);

        return Response.ok().entity("{\"message\":\"Logout exitoso\"}").build(); // Devuelve un objeto JSON
    }
}