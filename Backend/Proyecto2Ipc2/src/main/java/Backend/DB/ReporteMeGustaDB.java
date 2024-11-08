/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.DB;

/**
 *
 * @author herson
 */
import Modelos.MeGusta;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReporteMeGustaDB {

    // Método para obtener las 5 revistas más gustadas en un intervalo de tiempo
public List<MeGusta> obtenerMeGustasPorFecha(Date fechaInicio, Date fechaFin) {
    List<MeGusta> meGustas = new ArrayList<>();
    String query = "SELECT mg.*, u.nombre AS nombre_usuario, r.titulo AS titulo_revista " +
                   "FROM MeGusta mg " +
                   "JOIN Usuario u ON mg.id_usuario = u.id_usuario " +
                   "JOIN Revista r ON mg.id_revista = r.id_revista " +
                   "WHERE mg.fecha_megusta BETWEEN ? AND ? " +
                   "ORDER BY mg.fecha_megusta DESC " +
                   "LIMIT 5";

    try (Connection connection = DataSourceDB.getInstance().getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(query)) {

        preparedStatement.setDate(1, fechaInicio);
        preparedStatement.setDate(2, fechaFin);

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                MeGusta meGusta = new MeGusta();
                meGusta.setIdMeGusta(resultSet.getInt("id_megusta"));
                meGusta.setIdUsuario(resultSet.getInt("id_usuario"));
                meGusta.setIdRevista(resultSet.getInt("id_revista"));
                meGusta.setFechaMeGusta(resultSet.getDate("fecha_megusta"));
                meGusta.setNombreUsuario(resultSet.getString("nombre_usuario"));
                meGusta.setTituloRevista(resultSet.getString("titulo_revista"));
                meGustas.add(meGusta);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return meGustas;
}


    // Método para obtener todas las veces que se ha dado Me Gusta a una revista específica en un intervalo de tiempo
public List<MeGusta> obtenerMeGustasPorRevistaYFecha(int idRevista, Date fechaInicio, Date fechaFin) {
    List<MeGusta> meGustas = new ArrayList<>();
    String query = "SELECT mg.*, u.nombre AS nombre_usuario, r.titulo AS titulo_revista " +
                   "FROM MeGusta mg " +
                   "JOIN Usuario u ON mg.id_usuario = u.id_usuario " +
                   "JOIN Revista r ON mg.id_revista = r.id_revista " +
                   "WHERE mg.id_revista = ? AND mg.fecha_megusta BETWEEN ? AND ? " +
                   "ORDER BY mg.fecha_megusta DESC";

    try (Connection connection = DataSourceDB.getInstance().getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(query)) {

        preparedStatement.setInt(1, idRevista);
        preparedStatement.setDate(2, fechaInicio);
        preparedStatement.setDate(3, fechaFin);

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                MeGusta meGusta = new MeGusta();
                meGusta.setIdMeGusta(resultSet.getInt("id_megusta"));
                meGusta.setIdUsuario(resultSet.getInt("id_usuario"));
                meGusta.setIdRevista(resultSet.getInt("id_revista"));
                meGusta.setFechaMeGusta(resultSet.getDate("fecha_megusta"));
                meGusta.setNombreUsuario(resultSet.getString("nombre_usuario"));
                meGusta.setTituloRevista(resultSet.getString("titulo_revista"));
                meGustas.add(meGusta);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return meGustas;
}

}

