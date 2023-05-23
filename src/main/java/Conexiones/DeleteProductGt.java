package Conexiones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DeleteProductGt {

    public static boolean delete(int id) throws ClassNotFoundException {
        try (Connection connection = DB2Config.getconnection2()) {
            // Verifica las conexiones
            if (connection != null) {
                System.out.println("Conectado con éxito...");
            } else {
                System.out.println("Error al conectar...");
            }
    
            System.out.println("Deleting Data...");


             // Obtener los valores del producto existente
             String productName = null;
             PreparedStatement selectStatement = connection.prepareStatement("SELECT NOMBBRE FROM dbo.PRODUCTOS WHERE ID_PRODUCTO = ?;");
             selectStatement.setInt(1, id);
             ResultSet resultSet = selectStatement.executeQuery();
             if (resultSet.next()) {
                 productName = resultSet.getString("NOMBBRE");
                 System.out.println("Producto encontrado: ID = " + id + ", Nombre = " + productName);
             } else {
                 System.out.println("No se encontró el producto con ID = " + id);
                 return true;
             }
 
             // Insertar los valores en la tabla de bitácora
             PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO dbo.BitacoreDelete (ID_PRODUCTO, NOMBRE_PRODUCTO) VALUES (?, ?);");
             insertStatement.setInt(1, id);
             insertStatement.setString(2, productName);
             int rowsInserted = insertStatement.executeUpdate();
             if (rowsInserted > 0) {
                 System.out.println("Valores insertados en la tabla de bitácora correctamente");
             } else {
                 System.out.println("Error al insertar valores en la tabla de bitácora");
                 return true;
             }
 
    
            PreparedStatement deleteStatement = connection.prepareStatement("DELETE FROM dbo.PRODUCTOS WHERE id_producto = ?;");
            deleteStatement.setInt(1, id);
            
            int rowsDeleted = deleteStatement.executeUpdate();
    
            if (rowsDeleted > 0) {
                System.out.println("Producto Eliminado correctamente");
                return true;
            } else {
                System.out.println(" Error al eliminar el producto");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("No existe el producto con el id : " + id + "   " + e.getMessage());
            return false;
        }
          
        }
    
}
