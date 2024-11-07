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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnuncioDB {

    public void insertarAnuncioTexto(Anuncio anuncio) {
        String query = "INSERT INTO Anuncio (tipo, contenido, id_usuario, fecha_inicio, fecha_fin, estado) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DataSourceDB.getInstance().getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {

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

        try (Connection connection = DataSourceDB.getInstance().getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {

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

        try (Connection connection = DataSourceDB.getInstance().getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {

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

    //metodo para obtener los anuncios
    public List<Anuncio> obtenerAnunciosActivos() {
        String query = "SELECT * FROM Anuncio WHERE estado = 'Activo'";
        List<Anuncio> anuncios = new ArrayList<>();

        try (Connection connection = DataSourceDB.getInstance().getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query); ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Anuncio anuncio = new Anuncio();
                anuncio.setIdAnuncio(resultSet.getInt("id_anuncio"));
                anuncio.setTipo(resultSet.getString("tipo"));
                anuncio.setContenido(resultSet.getString("contenido"));
                anuncio.setImagen(resultSet.getString("imagen"));
                anuncio.setVideo(resultSet.getString("video"));
                anuncio.setIdUsuario(resultSet.getInt("id_usuario"));
                anuncio.setFechaInicio(resultSet.getDate("fecha_inicio"));
                anuncio.setFechaFin(resultSet.getDate("fecha_fin"));
                anuncio.setEstado(resultSet.getString("estado"));
                anuncios.add(anuncio);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return anuncios;
    }
    
    //metodo para actualizar el estado de los anuncios

    public void cambiarEstadoAnuncio(int idAnuncio, String nuevoEstado) {
        String query = "UPDATE Anuncio SET estado = ? WHERE id_anuncio = ?";

        try (Connection connection = DataSourceDB.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, nuevoEstado);
            preparedStatement.setInt(2, idAnuncio);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



