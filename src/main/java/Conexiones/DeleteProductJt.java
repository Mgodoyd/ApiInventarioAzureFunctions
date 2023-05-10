package Conexiones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteProductJt {
    public static boolean delete(int id) throws ClassNotFoundException {
        try (Connection connection = DB1Config.getconnection1()) {
            // Verifica las conexiones
            if (connection != null) {
                System.out.println("Conectado con éxito...");
            } else {
                System.out.println("Error al conectar...");
            }
    
            System.out.println("Deleting Data...");
    
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
