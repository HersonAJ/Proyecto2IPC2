/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.DB;

/**
 *
 * @author herson
 */
import Modelos.Comentario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ComentarioDB {

    // Método para obtener comentarios por revista
    public List<Comentario> obtenerComentariosPorRevista(int idRevista) {
        List<Comentario> comentarios = new ArrayList<>();
        String query = "SELECT * FROM Comentario WHERE id_revista = ?";

        try (Connection connection = DataSourceDB.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, idRevista);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Comentario comentario = new Comentario();
                comentario.setIdComentario(resultSet.getInt("id_comentario"));
                comentario.setIdUsuario(resultSet.getInt("id_usuario"));
                comentario.setIdRevista(resultSet.getInt("id_revista"));
                comentario.setContenido(resultSet.getString("contenido"));
                comentario.setFechaCreacion(resultSet.getDate("fecha_creacion"));
                comentarios.add(comentario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comentarios;
    }

    // Método para verificar si la revista permite comentarios
    public boolean permiteComentarios(int idRevista) {
        String query = "SELECT permite_comentarios FROM Revista WHERE id_revista = ?";
        try (Connection connection = DataSourceDB.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, idRevista);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getBoolean("permite_comentarios");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Método para agregar un nuevo comentario
    public boolean agregarComentario(Comentario comentario) {
        // Verificar si la revista permite comentarios
        if (!permiteComentarios(comentario.getIdRevista())) {
            return false; // La revista no permite comentarios
        }

        String query = "INSERT INTO Comentario (id_usuario, id_revista, contenido, fecha_creacion) VALUES (?, ?, ?, ?)";

        try (Connection connection = DataSourceDB.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, comentario.getIdUsuario());
            preparedStatement.setInt(2, comentario.getIdRevista());
            preparedStatement.setString(3, comentario.getContenido());
            preparedStatement.setDate(4, new java.sql.Date(comentario.getFechaCreacion().getTime()));

            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

