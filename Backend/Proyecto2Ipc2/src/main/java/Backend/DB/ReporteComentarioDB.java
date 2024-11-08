/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.DB;

import Modelos.Comentario;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author herson
 */


public class ReporteComentarioDB {

    // Método para obtener todos los comentarios de todas las revistas de un usuario en un intervalo de tiempo
    public List<Comentario> obtenerComentariosPorUsuarioYFecha(int idUsuario, Date fechaInicio, Date fechaFin) {
        List<Comentario> comentarios = new ArrayList<>();
        String query = "SELECT c.*, u.nombre AS nombre_usuario, r.titulo AS titulo_revista "
                + "FROM Comentario c "
                + "JOIN Usuario u ON c.id_usuario = u.id_usuario "
                + "JOIN Revista r ON c.id_revista = r.id_revista "
                + "WHERE r.id_usuario = ? AND c.fecha_creacion BETWEEN ? AND ?";

        try (Connection connection = DataSourceDB.getInstance().getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, idUsuario);
            preparedStatement.setDate(2, fechaInicio);
            preparedStatement.setDate(3, fechaFin);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Comentario comentario = new Comentario();
                    comentario.setIdComentario(resultSet.getInt("id_comentario"));
                    comentario.setIdUsuario(resultSet.getInt("id_usuario"));
                    comentario.setIdRevista(resultSet.getInt("id_revista"));
                    comentario.setContenido(resultSet.getString("contenido"));
                    comentario.setFechaCreacion(resultSet.getDate("fecha_creacion"));
                    comentario.setNombreUsuario(resultSet.getString("nombre_usuario"));
                    comentario.setTituloRevista(resultSet.getString("titulo_revista"));
                    comentarios.add(comentario);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comentarios;
    }

    // Método para obtener todos los comentarios de una revista específica en un intervalo de tiempo
    public List<Comentario> obtenerComentariosPorRevistaYFecha(int idRevista, Date fechaInicio, Date fechaFin) {
        List<Comentario> comentarios = new ArrayList<>();
        String query = "SELECT c.*, u.nombre AS nombre_usuario, r.titulo AS titulo_revista "
                + "FROM Comentario c "
                + "JOIN Usuario u ON c.id_usuario = u.id_usuario "
                + "JOIN Revista r ON c.id_revista = r.id_revista "
                + "WHERE c.id_revista = ? AND c.fecha_creacion BETWEEN ? AND ?";

        try (Connection connection = DataSourceDB.getInstance().getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, idRevista);
            preparedStatement.setDate(2, fechaInicio);
            preparedStatement.setDate(3, fechaFin);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Comentario comentario = new Comentario();
                    comentario.setIdComentario(resultSet.getInt("id_comentario"));
                    comentario.setIdUsuario(resultSet.getInt("id_usuario"));
                    comentario.setIdRevista(resultSet.getInt("id_revista"));
                    comentario.setContenido(resultSet.getString("contenido"));
                    comentario.setFechaCreacion(resultSet.getDate("fecha_creacion"));
                    comentario.setNombreUsuario(resultSet.getString("nombre_usuario"));
                    comentario.setTituloRevista(resultSet.getString("titulo_revista"));
                    comentarios.add(comentario);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comentarios;
    }
}
