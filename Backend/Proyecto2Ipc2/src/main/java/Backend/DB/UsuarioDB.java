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

public class UsuarioDB {
    public boolean correoExiste(String email) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Usuario WHERE email = ?";
        try (Connection connection = DataSourceDB.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    public void insertarUsuario(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO Usuario (nombre, email, contraseña, foto_perfil, descripcion, hobbies, temas_interes, tipo_usuario, saldo_cartera) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DataSourceDB.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getEmail());
            statement.setString(3, usuario.getContraseña());
            statement.setBytes(4, usuario.getFotoPerfil());
            statement.setString(5, usuario.getDescripcion());
            statement.setString(6, String.join(",", usuario.getHobbies()));
            statement.setString(7, String.join(",", usuario.getTemasInteres()));
            statement.setString(8, usuario.getTipoUsuario());
            statement.setDouble(9, usuario.getSaldoCartera());
            statement.executeUpdate();
        }
    }
}
