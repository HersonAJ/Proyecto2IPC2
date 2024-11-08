/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.DB;

/**
 *
 * @author herson
 */
import Modelos.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PerfilesEditoresDB {

    // Método para obtener todos los usuarios que son Editores
    public List<Usuario> obtenerEditores() {
        List<Usuario> editores = new ArrayList<>();
        String query = "SELECT id_usuario, nombre, email, contraseña, foto_perfil, descripcion, hobbies, temas_interes, tipo_usuario, saldo_cartera " +
                       "FROM Usuario WHERE tipo_usuario = 'Editor'";

        try (Connection connection = DataSourceDB.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Usuario editor = new Usuario();
                editor.setIdUsuario(resultSet.getInt("id_usuario"));
                editor.setNombre(resultSet.getString("nombre"));
                editor.setEmail(resultSet.getString("email"));
                editor.setContraseña(resultSet.getString("contraseña"));
                editor.setFotoPerfil(resultSet.getBytes("foto_perfil"));
                editor.setDescripcion(resultSet.getString("descripcion"));
                editor.setHobbies(List.of(resultSet.getString("hobbies").split(","))); // Asumiendo que los hobbies están separados por comas
                editor.setTemasInteres(List.of(resultSet.getString("temas_interes").split(","))); // Asumiendo que los temas de interés están separados por comas
                editor.setTipoUsuario(resultSet.getString("tipo_usuario"));
                editor.setSaldoCartera(resultSet.getDouble("saldo_cartera"));
                editores.add(editor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return editores;
    }
}
