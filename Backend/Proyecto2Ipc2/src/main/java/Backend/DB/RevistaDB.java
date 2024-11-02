/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.DB;

import Modelos.Revista;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author herson
 */

public class RevistaDB {

    
    //metodo para insertar revistas
    public boolean insertRevista(Revista revista) {
        String sql = "INSERT INTO Revista (titulo, descripcion, categoria, id_usuario) VALUES (?, ?, ?, ?)";

        try (Connection connection = DataSourceDB.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, revista.getTitulo());
            statement.setString(2, revista.getDescripcion());
            statement.setString(3, revista.getCategoria());
            statement.setInt(4, revista.getIdUsuario());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    //metodo para consultar revistas

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
            revistas.add(revista);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return revistas;
}

    
    
    //metodo para activar o desctivar revista
    public boolean cambiarEstadoRevista(int idRevista, String estado) {
    String query = "UPDATE Revista SET estado = ? WHERE id_revista = ?";

    try (Connection connection = DataSourceDB.getInstance().getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        
        preparedStatement.setString(1, estado);
        preparedStatement.setInt(2, idRevista);
        
        int rowsUpdated = preparedStatement.executeUpdate();
        return rowsUpdated > 0;

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

}

