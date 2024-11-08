/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.DB;

/**
 *
 * @author herson
 */
import Modelos.Revista;
import Modelos.Tag;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoriaDB {

    // Método para obtener revistas por categoría
    public Map<String, List<Revista>> obtenerRevistasPorCategoria() {
        Map<String, List<Revista>> revistasPorCategoria = new HashMap<>();
        String query = "SELECT r.id_revista, r.titulo, r.descripcion, r.categoria, r.id_usuario, u.nombre as nombre_usuario, t.id_tag, t.nombre as tag_nombre " +
                       "FROM Revista r " +
                       "JOIN Usuario u ON r.id_usuario = u.id_usuario " +
                       "LEFT JOIN RevistaTag rt ON r.id_revista = rt.id_revista " +
                       "LEFT JOIN Tag t ON rt.id_tag = t.id_tag";

        try (Connection connection = DataSourceDB.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int idRevista = resultSet.getInt("id_revista");
                String titulo = resultSet.getString("titulo");
                String descripcion = resultSet.getString("descripcion");
                String categoria = resultSet.getString("categoria");
                int idUsuario = resultSet.getInt("id_usuario");
                String nombreUsuario = resultSet.getString("nombre_usuario");

                Revista revista = new Revista();
                revista.setIdRevista(idRevista);
                revista.setTitulo(titulo);
                revista.setDescripcion(descripcion);
                revista.setCategoria(categoria);
                revista.setIdUsuario(idUsuario);
                revista.setTags(new ArrayList<>());

                if (resultSet.getString("tag_nombre") != null) {
                    Tag tag = new Tag(resultSet.getInt("id_tag"), resultSet.getString("tag_nombre"));
                    revista.getTags().add(tag);
                }

                revistasPorCategoria.computeIfAbsent(categoria, k -> new ArrayList<>()).add(revista);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return revistasPorCategoria;
    }
}

