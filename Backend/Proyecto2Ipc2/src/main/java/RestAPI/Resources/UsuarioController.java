/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RestAPI.Resources;

import Backend.DB.FileUtils;
import Backend.DB.UsuarioDB;
import Modelos.Codificador;
import Modelos.Usuario;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

/**
 *
 * @author herson
 */

@Path("usuarios")
public class UsuarioController {
    private UsuarioDB usuarioDB = new UsuarioDB();

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response crearUsuario(
        @FormDataParam("nombre") String nombre,
        @FormDataParam("email") String email,
        @FormDataParam("password") String password,
        @FormDataParam("descripcion") String descripcion,
        @FormDataParam("hobbies") String hobbies,
        @FormDataParam("temasInteres") String temasInteres,
        @FormDataParam("tipoUsuario") String tipoUsuario,
        @FormDataParam("fotoPerfil") InputStream uploadedInputStream,
        @FormDataParam("fotoPerfil") FormDataContentDisposition fileDetails) {
        
        try {


            // Validar campos obligatorios
            if (nombre == null || email == null || password == null) {
                System.out.println("Error: Campos obligatorios no recibidos");
                return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Los campos nombre, email y contraseña son obligatorios").build();
            }

            // Verificar si el correo electrónico ya existe
            if (usuarioDB.correoExiste(email)) {
                System.out.println("Error: Correo electrónico ya registrado");
                return Response.status(Response.Status.CONFLICT)
                    .entity("El correo electrónico ya está registrado").build();
            }

            // Codificar la contraseña
            String contraseñaCodificada = Codificador.codificar(password);

            // Convertir InputStream a byte[]
            byte[] fotoPerfilBytes = null;
            if (uploadedInputStream != null) {
                fotoPerfilBytes = FileUtils.inputStreamToByteArray(uploadedInputStream);
            }

            // Crear objeto Usuario
            Usuario usuario = new Usuario();
            usuario.setNombre(nombre);
            usuario.setEmail(email);
            usuario.setContraseña(contraseñaCodificada);
            usuario.setFotoPerfil(fotoPerfilBytes);
            usuario.setDescripcion(descripcion);
            usuario.setHobbies(List.of(hobbies.split(",")));
            usuario.setTemasInteres(List.of(temasInteres.split(",")));
            usuario.setTipoUsuario(tipoUsuario);

            // Insertar usuario en la base de datos
            usuarioDB.insertarUsuario(usuario);

            System.out.println("Usuario creado con éxito");
            return Response.status(Response.Status.CREATED).build();
        } catch (SQLException e) {
            System.out.println("Error al crear el usuario: " + e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("Error al crear el usuario").build();
        } catch (IOException e) {
            System.out.println("Error al procesar la imagen: " + e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("Error al procesar la imagen").build();
        }
    }
}