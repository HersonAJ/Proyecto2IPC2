/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.DB;

/**
 *
 * @author herson
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SaldoCarteraDB {

    public boolean agregarSaldo(int idUsuario, double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto debe ser positivo.");
        }

        String query = "UPDATE Usuario SET saldo_cartera = saldo_cartera + ? WHERE id_usuario = ?";

        try (Connection connection = DataSourceDB.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            
            preparedStatement.setDouble(1, monto);
            preparedStatement.setInt(2, idUsuario);
            
            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
        public double obtenerSaldo(int idUsuario) {
        String query = "SELECT saldo_cartera FROM Usuario WHERE id_usuario = ?";
        try (Connection connection = DataSourceDB.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, idUsuario);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                return rs.getDouble("saldo_cartera");
            } else {
                throw new SQLException("No se encontró el usuario con id " + idUsuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener el saldo.");
        }
    }
}

