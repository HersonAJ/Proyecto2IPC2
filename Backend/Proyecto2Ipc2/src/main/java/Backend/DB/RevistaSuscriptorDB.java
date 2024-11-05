/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.DB;

import Modelos.Revista;
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
public class RevistaSuscriptorDB {

    // Método para obtener todas las revistas
    public List<Revista> getTodasLasRevistas() {
        List<Revista> revistas = new ArrayList<>();
        String query = "SELECT * FROM Revista WHERE estado = 'Activo'";

        try (Connection connection = DataSourceDB.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Revista revista = new Revista();
                revista.setIdRevista(resultSet.getInt("id_revista"));
                revista.setTitulo(resultSet.getString("titulo"));
                revista.setDescripcion(resultSet.getString("descripcion"));
                revista.setCategoria(resultSet.getString("categoria"));
                revista.setIdUsuario(resultSet.getInt("id_usuario"));
                revista.setEstado(resultSet.getString("estado"));
                revista.setCostoPorDia(resultSet.getDouble("costo_por_dia"));
                revista.setFechaCreacion(resultSet.getDate("fecha_creacion"));
                revista.setPermiteComentarios(resultSet.getBoolean("permite_comentarios"));
                revista.setPermiteMegusta(resultSet.getBoolean("permite_megusta"));
                revista.setPermiteSuscripcion(resultSet.getBoolean("permite_suscripcion"));
                revistas.add(revista);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return revistas;
    }
    
        // Método para obtener revistas por categoría
    public List<Revista> getRevistasPorCategoria(String categoria) {
        List<Revista> revistas = new ArrayList<>();
        String query = "SELECT * FROM Revista WHERE categoria = ? AND estado = 'Activo'";

        try (Connection connection = DataSourceDB.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, categoria);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Revista revista = new Revista();
                revista.setIdRevista(resultSet.getInt("id_revista"));
                revista.setTitulo(resultSet.getString("titulo"));
                revista.setDescripcion(resultSet.getString("descripcion"));
                revista.setCategoria(resultSet.getString("categoria"));
                revista.setIdUsuario(resultSet.getInt("id_usuario"));
                revista.setEstado(resultSet.getString("estado"));
                revista.setCostoPorDia(resultSet.getDouble("costo_por_dia"));
                revista.setFechaCreacion(resultSet.getDate("fecha_creacion"));
                revista.setPermiteComentarios(resultSet.getBoolean("permite_comentarios"));
                revista.setPermiteMegusta(resultSet.getBoolean("permite_megusta"));
                revista.setPermiteSuscripcion(resultSet.getBoolean("permite_suscripcion"));
                revistas.add(revista);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return revistas;
    }

    // Método para obtener revistas por tag
    public List<Revista> getRevistasPorTag(String tag) {
        List<Revista> revistas = new ArrayList<>();
        String query = "SELECT Revista.* FROM Revista JOIN RevistaTag ON Revista.id_revista = RevistaTag.id_revista JOIN Tag ON RevistaTag.id_tag = Tag.id_tag WHERE Tag.nombre = ? AND Revista.estado = 'Activo'";

        try (Connection connection = DataSourceDB.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, tag);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Revista revista = new Revista();
                revista.setIdRevista(resultSet.getInt("id_revista"));
                revista.setTitulo(resultSet.getString("titulo"));
                revista.setDescripcion(resultSet.getString("descripcion"));
                revista.setCategoria(resultSet.getString("categoria"));
                revista.setIdUsuario(resultSet.getInt("id_usuario"));
                revista.setEstado(resultSet.getString("estado"));
                revista.setCostoPorDia(resultSet.getDouble("costo_por_dia"));
                revista.setFechaCreacion(resultSet.getDate("fecha_creacion"));
                revista.setPermiteComentarios(resultSet.getBoolean("permite_comentarios"));
                revista.setPermiteMegusta(resultSet.getBoolean("permite_megusta"));
                revista.setPermiteSuscripcion(resultSet.getBoolean("permite_suscripcion"));
                revistas.add(revista);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return revistas;
    }

    public boolean suscribirseRevista(int idUsuario, int idRevista, Date fechaSuscripcion) {
        String checkQuery = "SELECT COUNT(*) FROM Suscripcion WHERE id_usuario = ? AND id_revista = ?";
        String insertQuery = "INSERT INTO Suscripcion (id_usuario, id_revista, fecha_suscripcion) VALUES (?, ?, ?)";

        try (Connection connection = DataSourceDB.getInstance().getConnection();
             PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
             PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {

            // Verificar si la suscripción ya existe
            checkStatement.setInt(1, idUsuario);
            checkStatement.setInt(2, idRevista);
            ResultSet resultSet = checkStatement.executeQuery();
            if (resultSet.next() && resultSet.getInt(1) > 0) {
                return false; // Ya está suscrito
            }

            // Insertar nueva suscripción
            insertStatement.setInt(1, idUsuario);
            insertStatement.setInt(2, idRevista);
            insertStatement.setDate(3, new java.sql.Date(fechaSuscripcion.getTime()));

            int rowsInserted = insertStatement.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // metodo para buscar revistas a las que se esta suscrito
    public List<Revista> getRevistasSuscritasPorUsuario(int idUsuario) {
        List<Revista> revistas = new ArrayList<>();
        String query = "SELECT Revista.* FROM Revista " +
                       "JOIN Suscripcion ON Revista.id_revista = Suscripcion.id_revista " +
                       "WHERE Suscripcion.id_usuario = ? AND Revista.estado = 'Activo'";

        try (Connection connection = DataSourceDB.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, idUsuario);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Revista revista = new Revista();
                revista.setIdRevista(resultSet.getInt("id_revista"));
                revista.setTitulo(resultSet.getString("titulo"));
                revista.setDescripcion(resultSet.getString("descripcion"));
                revista.setCategoria(resultSet.getString("categoria"));
                revista.setIdUsuario(resultSet.getInt("id_usuario"));
                revista.setEstado(resultSet.getString("estado"));
                revista.setCostoPorDia(resultSet.getDouble("costo_por_dia"));
                revista.setFechaCreacion(resultSet.getDate("fecha_creacion"));
                revista.setPermiteComentarios(resultSet.getBoolean("permite_comentarios"));
                revista.setPermiteMegusta(resultSet.getBoolean("permite_megusta"));
                revista.setPermiteSuscripcion(resultSet.getBoolean("permite_suscripcion"));
                revistas.add(revista);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return revistas;
    }

}
