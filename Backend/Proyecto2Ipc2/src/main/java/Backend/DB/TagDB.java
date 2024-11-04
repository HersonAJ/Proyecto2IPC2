/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.DB;

import Modelos.Tag;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author herson
 */
public class TagDB {

    
    // Método para insertar un tag
    public int insertTag(Tag tag) {
        String sql = "INSERT INTO Tag (nombre) VALUES (?)";

        try (Connection connection = DataSourceDB.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, tag.getNombre());
            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1); // Devuelve el ID generado para el tag
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1; // Indica que no se pudo insertar el tag
    }

    // Método para obtener el ID de un tag por nombre
    public int getTagIdByName(String nombre) {
        String sql = "SELECT id_tag FROM Tag WHERE nombre = ?";
        
        try (Connection connection = DataSourceDB.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, nombre);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("id_tag");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1; // Indica que no se encontró el tag
    }
}

