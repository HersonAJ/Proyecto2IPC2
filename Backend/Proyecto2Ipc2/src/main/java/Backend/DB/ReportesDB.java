/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.DB;

/**
 *
 * @author herson
 */
import Modelos.RevistaPopularAdmin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReportesDB {

public List<RevistaPopularAdmin> obtenerRevistasMasPopulares(String fechaInicio, String fechaFin) {
    String query = "SELECT r.id_revista, r.titulo, COUNT(s.id_suscripcion) AS totalSuscripciones, GROUP_CONCAT(u.nombre SEPARATOR ', ') AS usuariosSuscritos " +
                   "FROM Revista r " +
                   "JOIN Suscripcion s ON r.id_revista = s.id_revista " +
                   "JOIN Usuario u ON s.id_usuario = u.id_usuario " +
                   "WHERE s.fecha_suscripcion BETWEEN ? AND ? AND r.estado = 'Activo' " +
                   "GROUP BY r.id_revista, r.titulo " +
                   "ORDER BY totalSuscripciones DESC " +
                   "LIMIT 5";

    List<RevistaPopularAdmin> revistasPopulares = new ArrayList<>();

    try (Connection connection = DataSourceDB.getInstance().getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(query)) {

        preparedStatement.setString(1, fechaInicio);
        preparedStatement.setString(2, fechaFin);

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int idRevista = resultSet.getInt("id_revista");
                String titulo = resultSet.getString("titulo");
                int totalSuscripciones = resultSet.getInt("totalSuscripciones");
                String usuariosSuscritos = resultSet.getString("usuariosSuscritos");

                RevistaPopularAdmin revistaPopular = new RevistaPopularAdmin(idRevista, titulo, totalSuscripciones, usuariosSuscritos);
                revistasPopulares.add(revistaPopular);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return revistasPopulares;
}

}
