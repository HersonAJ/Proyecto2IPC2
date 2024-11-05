/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.DB;

import Modelos.MeGusta;
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
public class MeGustaDB {

    
    // Método para obtener "Me Gusta" por revista
    public List<MeGusta> obtenerMeGustaPorRevista(int idRevista) {
        List<MeGusta> meGustas = new ArrayList<>();
        String query = "SELECT * FROM MeGusta WHERE id_revista = ?";

        try (Connection connection = DataSourceDB.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, idRevista);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                MeGusta meGusta = new MeGusta();
                meGusta.setIdMeGusta(resultSet.getInt("id_megusta"));
                meGusta.setIdUsuario(resultSet.getInt("id_usuario"));
                meGusta.setIdRevista(resultSet.getInt("id_revista"));
                meGusta.setFechaMeGusta(resultSet.getDate("fecha_megusta"));
                meGustas.add(meGusta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return meGustas;
    }

    // Método para agregar un "Me Gusta"
    public boolean agregarMeGusta(MeGusta meGusta) {
        String query = "INSERT INTO MeGusta (id_usuario, id_revista, fecha_megusta) VALUES (?, ?, ?)";

        try (Connection connection = DataSourceDB.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, meGusta.getIdUsuario());
            preparedStatement.setInt(2, meGusta.getIdRevista());
            preparedStatement.setDate(3, new java.sql.Date(meGusta.getFechaMeGusta().getTime()));

            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

