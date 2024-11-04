/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.DB;

import Modelos.Edicion;
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
public class EdicionDB {

    public boolean insertarEdicion(Edicion edicion) throws SQLException {
    String sql = "INSERT INTO EdicionRevista (id_revista, titulo_edicion, fecha_creacion, archivo_pdf, estado) VALUES (?, ?, ?, ?, 'Activo')";
    
    try (Connection connection = DataSourceDB.getInstance().getConnection();
         PreparedStatement statement = connection.prepareStatement(sql)) {

        statement.setInt(1, edicion.getIdRevista());
        statement.setString(2, edicion.getTituloEdicion());
        statement.setDate(3, new java.sql.Date(edicion.getFechaCreacion().getTime()));
        statement.setBytes(4, edicion.getArchivoPdf());

        
        int rowsInserted = statement.executeUpdate();
        return rowsInserted > 0;
    }
}
 
    public List<Edicion> obtenerEdicionesPorRevista(int idRevista) {
    List<Edicion> ediciones = new ArrayList<>();
    String query = "SELECT id_edicion, id_revista, titulo_edicion, fecha_creacion, estado FROM EdicionRevista WHERE id_revista = ? AND estado = 'Activo'";

    try (Connection connection = DataSourceDB.getInstance().getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        
        preparedStatement.setInt(1, idRevista);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        while (resultSet.next()) {
            Edicion edicion = new Edicion();
            edicion.setIdEdicion(resultSet.getInt("id_edicion"));
            edicion.setIdRevista(resultSet.getInt("id_revista"));
            edicion.setTituloEdicion(resultSet.getString("titulo_edicion"));
            edicion.setFechaCreacion(resultSet.getDate("fecha_creacion"));
            edicion.setEstado(resultSet.getString("estado"));

            ediciones.add(edicion);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return ediciones;
}

    
    //el siguiente metodo es para enviar el pdf correctamente 
    

    // Método para obtener el archivo PDF por ID de edición
    public byte[] obtenerArchivoPdfPorId(int idEdicion) throws SQLException {
        String sql = "SELECT archivo_pdf FROM EdicionRevista WHERE id_edicion = ?";
        try (Connection connection = DataSourceDB.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idEdicion);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getBytes("archivo_pdf");
                }
            }
        }
        return null;
    }
    
    //metodo para desactivar edicion "borrar"
public boolean cambiarEstadoEdicion(int idEdicion, String estado) {
    String query = "UPDATE EdicionRevista SET estado = ? WHERE id_edicion = ?";

    try (Connection connection = DataSourceDB.getInstance().getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        
        preparedStatement.setString(1, estado);
        preparedStatement.setInt(2, idEdicion);
        
        int rowsUpdated = preparedStatement.executeUpdate();
        return rowsUpdated > 0;

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}


}


