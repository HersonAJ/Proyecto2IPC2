/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RestAPI.Resources;

import Backend.DB.LoginDB;
import Modelos.Codificador;
import Modelos.JWTHelper;
import Modelos.Usuario;
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


import java.io.UnsupportedEncodingException;

@Path("login")
public class LoginController {
    private LoginDB loginDB = new LoginDB();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginRequest loginRequest) throws IllegalArgumentException, UnsupportedEncodingException {
        String username = loginRequest.getUsername();
        String contraseñaCodificada = Codificador.codificar(loginRequest.getContraseña());
        Usuario usuario = loginDB.autenticarUsuario(username, contraseñaCodificada);

        if (usuario != null) {
            String jwt = JWTHelper.generateToken(
                username,
                usuario.getEmail(),
                usuario.getNombre(),
                usuario.getTipoUsuario(),
                usuario.getIdUsuario()
            );

            return Response.ok("{\"token\":\"" + jwt + "\"}").build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Credenciales inválidas").build();
        }
    }
}

