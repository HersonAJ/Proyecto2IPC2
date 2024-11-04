/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.DB;

import Modelos.Revista;
import Modelos.Tag;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author herson
 */

public class RevistaDB {

    // Método para insertar revistas y sus tags
    public boolean insertRevista(Revista revista) {
        String sql = "INSERT INTO Revista (titulo, descripcion, categoria, id_usuario, costo_por_dia, fecha_creacion, permite_comentarios, permite_megusta, permite_suscripcion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String sqlTag = "INSERT INTO RevistaTag (id_revista, id_tag) VALUES (?, ?)";
        TagDB tagDB = new TagDB();

        try (Connection connection = DataSourceDB.getInstance().getConnection()) {
            // Comenzar transacción
            connection.setAutoCommit(false);

            // Insertar revista
            try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, revista.getTitulo());
                statement.setString(2, revista.getDescripcion());
                statement.setString(3, revista.getCategoria());
                statement.setInt(4, revista.getIdUsuario());
                statement.setDouble(5, revista.getCostoPorDia());
                statement.setDate(6, new java.sql.Date(revista.getFechaCreacion().getTime()));
                statement.setBoolean(7, revista.isPermiteComentarios());
                statement.setBoolean(8, revista.isPermiteMegusta());
                statement.setBoolean(9, revista.isPermiteSuscripcion());

                int rowsInserted = statement.executeUpdate();
                if (rowsInserted == 0) {
                    connection.rollback();
                    return false;
                }

                // Obtener el ID generado para la revista
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int idRevista = generatedKeys.getInt(1);

                    // Insertar tags asociados
                    try (PreparedStatement tagStatement = connection.prepareStatement(sqlTag)) {
                        for (Tag tag : revista.getTags()) {
                            int tagId = tagDB.getTagIdByName(tag.getNombre());
                            if (tagId == -1) {
                                tagId = tagDB.insertTag(tag);
                            }
                            if (tagId != -1) {
                                tagStatement.setInt(1, idRevista);
                                tagStatement.setInt(2, tagId);
                                tagStatement.addBatch();
                            } else {
                                connection.rollback();
                                return false;
                            }
                        }
                        tagStatement.executeBatch();
                    }
                } else {
                    connection.rollback();
                    return false;
                }

                // Confirmar transacción
                connection.commit();
                return true;
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para obtener revistas por usuario
    public List<Revista> getRevistasPorUsuario(int idUsuario) {
        List<Revista> revistas = new ArrayList<>();
        String query = "SELECT * FROM Revista WHERE id_usuario = ? AND estado = 'Activo'";

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

    
    public boolean eliminarRevista(int idRevista, String estado) {
        String query = "UPDATE Revista SET estado = ? WHERE id_revista = ?";
        System.out.println("Ejecutando query: " + query + " con idRevista: " + idRevista + " y estado: " + estado);
        Connection connection = null;
        try {
            connection = DataSourceDB.getInstance().getConnection();
            connection.setAutoCommit(false); // Desactivar auto-commit
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, estado);
                preparedStatement.setInt(2, idRevista);
                int rowsUpdated = preparedStatement.executeUpdate();
                System.out.println("Filas actualizadas: " + rowsUpdated);
                connection.commit(); // Confirmar transacción
                return rowsUpdated > 0;
            } catch (SQLException e) {
                connection.rollback(); // Revertir transacción en caso de error
                throw e;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Método para actualizar permiteComentarios
    public boolean actualizarPermiteComentarios(int idRevista, boolean permiteComentarios) {
        String query = "UPDATE Revista SET permite_comentarios = ? WHERE id_revista = ?";

        try (Connection connection = DataSourceDB.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setBoolean(1, permiteComentarios);
            preparedStatement.setInt(2, idRevista);

            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
        // Método para actualizar permiteMeGusta
    public boolean actualizarPermiteMeGusta(int idRevista, boolean permiteMeGusta) {
        String query = "UPDATE Revista SET permite_megusta = ? WHERE id_revista = ?";

        try (Connection connection = DataSourceDB.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setBoolean(1, permiteMeGusta);
            preparedStatement.setInt(2, idRevista);

            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para actualizar permiteSuscripcion
    public boolean actualizarPermiteSuscripcion(int idRevista, boolean permiteSuscripcion) {
        String query = "UPDATE Revista SET permite_suscripcion = ? WHERE id_revista = ?";

        try (Connection connection = DataSourceDB.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setBoolean(1, permiteSuscripcion);
            preparedStatement.setInt(2, idRevista);

            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}

