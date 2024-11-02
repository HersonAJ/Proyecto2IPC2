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

/**
 *
 * @author herson
 */


public class LoginDB {
    public Usuario autenticarUsuario(String username, String contraseñaCodificada) {
        Usuario usuario = null;
        try (Connection connection = DataSourceDB.getInstance().getConnection()) {
            String sql = "SELECT * FROM Usuario WHERE email = ? AND contraseña = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, username);
                statement.setString(2, contraseñaCodificada);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        usuario = new Usuario();
                        usuario.setEmail(username);
                        usuario.setTipoUsuario(resultSet.getString("tipo_usuario"));
                        usuario.setIdUsuario(resultSet.getInt("id_usuario"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }
}


