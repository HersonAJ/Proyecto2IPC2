/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.DB;

/**
 *
 * @author herson
 */
import jakarta.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransaccionDB {

    // Método para obtener el precio de un anuncio
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

    // Método para procesar la compra del anuncio
    public Response procesarCompraAnuncio(int idUsuario, String tipoAnuncio, String duracion) {
        String consultarSaldoQuery = "SELECT saldo_cartera FROM Usuario WHERE id_usuario = ?";
        String restarSaldoUsuarioQuery = "UPDATE Usuario SET saldo_cartera = saldo_cartera - ? WHERE id_usuario = ?";
        String sumarSaldoGlobalQuery = "UPDATE CarteraGlobal SET saldo = saldo + ? WHERE id_cartera = 1"; // cartera global con id = 1
        String registrarTransaccionQuery = "INSERT INTO MovimientoCartera (id_usuario, tipo_movimiento, id_referencia, monto, fecha_movimiento, descripcion) VALUES (?, ?, ?, ?, NOW(), ?)";

        Connection connection = null;
        try {
            connection = DataSourceDB.getInstance().getConnection();
            connection.setAutoCommit(false);

            // Obtener el precio del anuncio
            double precioAnuncio = obtenerPrecioAnuncio(tipoAnuncio, duracion);

            // Consultar saldo del usuario
            double saldoUsuario = 0.0;
            try (PreparedStatement consultarSaldoStmt = connection.prepareStatement(consultarSaldoQuery)) {
                consultarSaldoStmt.setInt(1, idUsuario);
                try (ResultSet resultSet = consultarSaldoStmt.executeQuery()) {
                    if (resultSet.next()) {
                        saldoUsuario = resultSet.getDouble("saldo_cartera");
                    }
                }
            }

            // Validar si el usuario tiene fondos suficientes
            if (saldoUsuario < precioAnuncio) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("{\"message\":\"Fondos insuficientes para realizar esta compra.\"}")
                        .build();
            }

            // Restar el saldo del usuario
            try (PreparedStatement restarSaldoUsuarioStmt = connection.prepareStatement(restarSaldoUsuarioQuery)) {
                restarSaldoUsuarioStmt.setDouble(1, precioAnuncio);
                restarSaldoUsuarioStmt.setInt(2, idUsuario);
                restarSaldoUsuarioStmt.executeUpdate();
            }

            // Sumar el saldo a la cartera global
            try (PreparedStatement sumarSaldoGlobalStmt = connection.prepareStatement(sumarSaldoGlobalQuery)) {
                sumarSaldoGlobalStmt.setDouble(1, precioAnuncio);
                sumarSaldoGlobalStmt.executeUpdate();
            }

            // Registrar la transacción
            try (PreparedStatement registrarTransaccionStmt = connection.prepareStatement(registrarTransaccionQuery)) {
                registrarTransaccionStmt.setInt(1, idUsuario);
                registrarTransaccionStmt.setString(2, "Compra_Anuncio");
                registrarTransaccionStmt.setInt(3, 0); 
                registrarTransaccionStmt.setDouble(4, precioAnuncio);
                registrarTransaccionStmt.setString(5, "Compra de espacio publicitario: " + tipoAnuncio + " por " + duracion);
                registrarTransaccionStmt.executeUpdate();
            }

            // Confirmar la transacción
            connection.commit();
            return Response.status(Response.Status.OK)
                    .entity("{\"message\":\"Compra realizada exitosamente.\"}")
                    .build();

        } catch (SQLException e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"message\":\"Error al procesar la compra del anuncio.\"}")
                    .build();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException closeEx) {
                    closeEx.printStackTrace();
                }
            }
        }
    }
}

