package Conexiones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateProductsGt {

    public static boolean update(int id,String nombre, int precio, byte[] imagen, int stock_disponible, int stock_minimo_requerido) throws ClassNotFoundException{
        try (Connection connection = DB2Config.getconnection2()) {
         // Verifica las conexiones
         if (connection != null) {
             System.out.println("Conectado con éxito...");
         } else {
             System.out.println("Error al conectar...");
         }
 
         System.out.println("Updating Data...");
 
        PreparedStatement updateStatement = connection.prepareStatement("UPDATE dbo.PRODUCTOS SET id_usuario = ?, id_ubicacion = ?, nombbre = ?, precio = ?, img = ?, stock = ?, stock_minimo = ? WHERE id_producto = ?");
         updateStatement.setInt(1, 2);
         updateStatement.setInt(2, 2);
         updateStatement.setString(3, nombre);
         updateStatement.setInt(4, precio);
         updateStatement.setBytes(5, imagen);
         updateStatement.setInt(6, stock_disponible);
         updateStatement.setInt(7, stock_minimo_requerido);
         updateStatement.setInt(8, id);
         
 
         int rowsUpdated = updateStatement.executeUpdate();
 
         if (rowsUpdated> 0) {
             System.out.println("Producto Actualizado correctamente");
             return true;
         } else {
             System.out.println(" Error al actualizar el producto");
             return false;
         }
     } catch (SQLException e) {
         System.out.println("No existe el producto con el id : " + id + "   " + e.getMessage());
         return false;
     }
       
     
     }
    
}
