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


public class CobroDB {

    public void actualizarCostoDiario(double nuevoCostoPorDia) {
        String query = "UPDATE Revista SET costo_por_dia = ?";

        try (Connection connection = DataSourceDB.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setDouble(1, nuevoCostoPorDia);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void procesarCobroDiario() {
        String sumarCostosQuery = "SELECT SUM(costo_por_dia) AS totalCostoDiario FROM Revista";
        String restarSaldoQuery = "UPDATE CarteraGlobal SET saldo = saldo - ? WHERE id_cartera = ?";

        Connection connection = null;
        try {
            connection = DataSourceDB.getInstance().getConnection();
            // Iniciar transacción
            connection.setAutoCommit(false);

            double totalCostoDiario = 0.0;

            // Sumar el costo diario de todas las revistas
            try (PreparedStatement sumarCostosStmt = connection.prepareStatement(sumarCostosQuery);
                 ResultSet resultSet = sumarCostosStmt.executeQuery()) {

                if (resultSet.next()) {
                    totalCostoDiario = resultSet.getDouble("totalCostoDiario");
                }
            }

            // Restar el saldo de la cartera global
            try (PreparedStatement restarSaldoStmt = connection.prepareStatement(restarSaldoQuery)) {
                restarSaldoStmt.setDouble(1, totalCostoDiario);
                restarSaldoStmt.setInt(2, 1); // unica cartera global  id = 1
                restarSaldoStmt.executeUpdate();
            }

            // Confirmar transacción
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            // En caso de error, revertir transacción
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
        } finally {
            // Cerrar la conexión
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException closeEx) {
                    closeEx.printStackTrace();
                }
            }
        }
    }

    public int contarRevistasActivas() {
        String query = "SELECT COUNT(*) AS cantidad FROM Revista WHERE estado = 'Activo'";

        try (Connection connection = DataSourceDB.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            if (resultSet.next()) {
                return resultSet.getInt("cantidad");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public double obtenerCostoDiarioAsignado() {
        String query = "SELECT AVG(costo_por_dia) AS costoDiario FROM Revista WHERE estado = 'Activo'";

        try (Connection connection = DataSourceDB.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            if (resultSet.next()) {
                return resultSet.getDouble("costoDiario");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    public void fijarPrecioAnuncio(String tipo, String duracion, double precio) {
        String query = "REPLACE INTO PrecioAnuncio (tipo, duracion, precio) VALUES (?, ?, ?)";

        try (Connection connection = DataSourceDB.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, tipo);
            preparedStatement.setString(2, duracion);
            preparedStatement.setDouble(3, precio);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public double obtenerPrecioAnuncio(String tipo, String duracion) {
        String query = "SELECT precio FROM PrecioAnuncio WHERE tipo = ? AND duracion = ?";

        try (Connection connection = DataSourceDB.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, tipo);
            preparedStatement.setString(2, duracion);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getDouble("precio");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }
}


