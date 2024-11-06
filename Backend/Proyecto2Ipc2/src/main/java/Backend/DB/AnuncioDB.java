/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.DB;

/**
 *
 * @author herson
 */
import Modelos.Anuncio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AnuncioDB {

    public void insertarAnuncioTexto(Anuncio anuncio) {
        String query = "INSERT INTO Anuncio (tipo, contenido, id_usuario, fecha_inicio, fecha_fin, estado) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DataSourceDB.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, anuncio.getTipo());
            preparedStatement.setString(2, anuncio.getContenido());
            preparedStatement.setInt(3, anuncio.getIdUsuario());
            preparedStatement.setDate(4, new java.sql.Date(anuncio.getFechaInicio().getTime()));
            preparedStatement.setDate(5, new java.sql.Date(anuncio.getFechaFin().getTime()));
            preparedStatement.setString(6, anuncio.getEstado());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertarAnuncioTextoImagen(Anuncio anuncio) {
        String query = "INSERT INTO Anuncio (tipo, contenido, imagen, id_usuario, fecha_inicio, fecha_fin, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DataSourceDB.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, anuncio.getTipo());
            preparedStatement.setString(2, anuncio.getContenido());
            preparedStatement.setString(3, anuncio.getImagen());
            preparedStatement.setInt(4, anuncio.getIdUsuario());
            preparedStatement.setDate(5, new java.sql.Date(anuncio.getFechaInicio().getTime()));
            preparedStatement.setDate(6, new java.sql.Date(anuncio.getFechaFin().getTime()));
            preparedStatement.setString(7, anuncio.getEstado());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertarAnuncioVideo(Anuncio anuncio) {
        String query = "INSERT INTO Anuncio (tipo, contenido, video, id_usuario, fecha_inicio, fecha_fin, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DataSourceDB.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, anuncio.getTipo());
            preparedStatement.setString(2, anuncio.getContenido());
            preparedStatement.setString(3, anuncio.getVideo());
            preparedStatement.setInt(4, anuncio.getIdUsuario());
            preparedStatement.setDate(5, new java.sql.Date(anuncio.getFechaInicio().getTime()));
            preparedStatement.setDate(6, new java.sql.Date(anuncio.getFechaFin().getTime()));
            preparedStatement.setString(7, anuncio.getEstado());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
