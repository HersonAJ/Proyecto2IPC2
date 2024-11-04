/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author herson
 */

public class SaldoCarteraGlobal {

    public boolean agregarSaldo(double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto debe ser positivo.");
        }

        String checkQuery = "SELECT COUNT(*) FROM CarteraGlobal WHERE id_cartera = 1";
        String insertQuery = "INSERT INTO CarteraGlobal (id_cartera, saldo) VALUES (1, 0.00)";
        String updateQuery = "UPDATE CarteraGlobal SET saldo = saldo + ? WHERE id_cartera = 1";

        try (Connection connection = DataSourceDB.getInstance().getConnection();
             PreparedStatement checkStmt = connection.prepareStatement(checkQuery);
             PreparedStatement insertStmt = connection.prepareStatement(insertQuery);
             PreparedStatement updateStmt = connection.prepareStatement(updateQuery)) {

            System.out.println("Conexión a la base de datos establecida.");

            // Verificar si la fila existe
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next() && rs.getInt(1) == 0) {
                // Si no existe, insertar la fila inicial
                insertStmt.executeUpdate();
                System.out.println("Fila inicial insertada.");
            }

            // Actualizar el saldo
            updateStmt.setDouble(1, monto);
            int rowsUpdated = updateStmt.executeUpdate();
            System.out.println("Filas actualizadas: " + rowsUpdated);

            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public double obtenerSaldo() {
        String query = "SELECT saldo FROM CarteraGlobal WHERE id_cartera = 1";
        try (Connection connection = DataSourceDB.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet rs = preparedStatement.executeQuery()) {

            if (rs.next()) {
                return rs.getDouble("saldo");
            } else {
                throw new SQLException("No se encontró la cartera global.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener el saldo.");
        }
    }
}



