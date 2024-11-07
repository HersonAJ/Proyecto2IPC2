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

public class MisAnunciosDB {

    public List<Anuncio> obtenerAnunciosPorUsuario(int idUsuario) {
        String query = "SELECT * FROM Anuncio WHERE id_usuario = ?";
        List<Anuncio> anuncios = new ArrayList<>();

        try (Connection connection = DataSourceDB.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, idUsuario);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return anuncios;
    }
}

