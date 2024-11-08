/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.DB;

/**
 *
 * @author herson
 */
import Modelos.Suscripcion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReporteSuscripcionDB {

    // Método para obtener todas las suscripciones de todas las revistas de un usuario en un intervalo de tiempo
    public List<Suscripcion> obtenerSuscripcionesPorUsuarioYFecha(int idUsuario, Date fechaInicio, Date fechaFin) {
        List<Suscripcion> suscripciones = new ArrayList<>();
        String query = "SELECT s.*, u.nombre AS nombre_usuario, r.titulo AS titulo_revista " +
                       "FROM Suscripcion s " +
                       "JOIN Usuario u ON s.id_usuario = u.id_usuario " +
                       "JOIN Revista r ON s.id_revista = r.id_revista " +
                       "WHERE r.id_usuario = ? AND s.fecha_suscripcion BETWEEN ? AND ?";

        try (Connection connection = DataSourceDB.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, idUsuario);
            preparedStatement.setDate(2, fechaInicio);
            preparedStatement.setDate(3, fechaFin);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Suscripcion suscripcion = new Suscripcion();
                    suscripcion.setIdUsuario(resultSet.getInt("id_usuario"));
                    suscripcion.setFechaSuscripcion(resultSet.getDate("fecha_suscripcion"));
                    suscripcion.setNombreUsuario(resultSet.getString("nombre_usuario")); // Nombre del usuario
                    suscripcion.setTituloRevista(resultSet.getString("titulo_revista"));  // Título de la revista
                    suscripcion.setIdRevista(resultSet.getInt("id_revista"));
                    suscripciones.add(suscripcion);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return suscripciones;
    }

    // Método para obtener todas las suscripciones de una revista específica en un intervalo de tiempo
    public List<Suscripcion> obtenerSuscripcionesPorRevistaYFecha(int idRevista, Date fechaInicio, Date fechaFin) {
        List<Suscripcion> suscripciones = new ArrayList<>();
        String query = "SELECT s.*, u.nombre AS nombre_usuario, r.titulo AS titulo_revista " +
                       "FROM Suscripcion s " +
                       "JOIN Usuario u ON s.id_usuario = u.id_usuario " +
                       "JOIN Revista r ON s.id_revista = r.id_revista " +
                       "WHERE s.id_revista = ? AND s.fecha_suscripcion BETWEEN ? AND ?";

        try (Connection connection = DataSourceDB.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, idRevista);
            preparedStatement.setDate(2, fechaInicio);
            preparedStatement.setDate(3, fechaFin);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Suscripcion suscripcion = new Suscripcion();
                    suscripcion.setIdUsuario(resultSet.getInt("id_usuario"));
                    suscripcion.setFechaSuscripcion(resultSet.getDate("fecha_suscripcion"));
                    suscripcion.setNombreUsuario(resultSet.getString("nombre_usuario")); // Nombre del usuario
                    suscripcion.setTituloRevista(resultSet.getString("titulo_revista"));  // Título de la revista
                    suscripcion.setIdRevista(resultSet.getInt("id_revista"));
                    suscripciones.add(suscripcion);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return suscripciones;
    }
}

