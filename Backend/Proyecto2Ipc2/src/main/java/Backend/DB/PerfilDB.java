/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.DB;

import Modelos.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

/**
 *
 * @author herson
 */
public class PerfilDB {

    public Usuario obtenerPerfilPorCorreo(String email) {
        Usuario usuario = null;
        try (Connection connection = DataSourceDB.getInstance().getConnection()) {
            String sql = "SELECT * FROM Usuario WHERE email = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, email);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        usuario = new Usuario();
                        usuario.setIdUsuario(resultSet.getInt("id_usuario"));
                        usuario.setNombre(resultSet.getString("nombre"));
                        usuario.setEmail(resultSet.getString("email"));
                        usuario.setDescripcion(resultSet.getString("descripcion"));
                        usuario.setHobbies(Arrays.asList(resultSet.getString("hobbies").split(",")));
                        usuario.setTemasInteres(Arrays.asList(resultSet.getString("temas_interes").split(",")));
                        usuario.setTipoUsuario(resultSet.getString("tipo_usuario"));
                        usuario.setSaldoCartera(resultSet.getDouble("saldo_cartera"));
                        usuario.setFotoPerfil(resultSet.getBytes("foto_perfil"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    // Método para actualizar el perfil del usuario (sin contraseña)
    public boolean actualizarPerfilUsuario(Usuario usuario) {
        String sql = "UPDATE Usuario SET nombre = ?, foto_perfil = ?, descripcion = ?, hobbies = ?, temas_interes = ? WHERE id_usuario = ?";
        try (Connection connection = DataSourceDB.getInstance().getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, usuario.getNombre());
            statement.setBytes(2, usuario.getFotoPerfil());
            statement.setString(3, usuario.getDescripcion());
            statement.setString(4, String.join(",", usuario.getHobbies()));
            statement.setString(5, String.join(",", usuario.getTemasInteres()));
            statement.setInt(6, usuario.getIdUsuario());

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para actualizar la contraseña del usuario
    public boolean actualizarContraseñaUsuario(int idUsuario, String nuevaContraseña) {
        String sql = "UPDATE Usuario SET contraseña = ? WHERE id_usuario = ?";
        try (Connection connection = DataSourceDB.getInstance().getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nuevaContraseña);
            statement.setInt(2, idUsuario);

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
