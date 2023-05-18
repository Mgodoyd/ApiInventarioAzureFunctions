package Conexiones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class InsertProductsJt {

    public static boolean exists(String nombre) throws ClassNotFoundException {
        try (Connection connection = DB1Config.getconnection1()) {
            // Verifica las conexiones
            if (connection != null) {
                System.out.println("Conectado con éxito...");
            } else {
                System.out.println("Error al conectar...");
            }
    
            // Consulta la tabla productos buscando el nombre
            String query = "SELECT COUNT(*) FROM dbo.productos WHERE nombbre = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, nombre);
            ResultSet rs = pstmt.executeQuery();
            int count = 0;
            if (rs.next()) {
                count = rs.getInt(1);
            }
    
            return count > 0;
        } catch (SQLException e) {
            System.out.println("Error al buscar el producto: " + e.getMessage());
            return false;
        }
    }
    
    public static boolean insert(String nombre, int precio, byte[] image, int stock_disponible, int stock_minimo_requerido) throws ClassNotFoundException {
        try(Connection connection = DB1Config.getconnection1()) {
            // Verifica las conexiones
            if(connection != null) {
                System.out.println("Conectado con éxito...");
            } else {
                System.out.println("Error al conectar...");
            }
   
            // Obtiene el valor máximo del ID_PRODUCTO de la tabla
            String query = "SELECT MAX(id_producto) FROM dbo.productos";
            Statement stmt = connection.createStatement(); 
            ResultSet rs = stmt.executeQuery(query);
            int lastId = 0;
            if (rs.next()) {
                lastId = rs.getInt(1);
            }
            int newId = lastId + 1;
   
            System.out.println("Insertando datos...");
            String insertQuery = "INSERT INTO dbo.productos (id_producto,id_usuario,id_ubicacion,nombbre,precio,img,stock,stock_minimo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
               PreparedStatement pstmt = connection.prepareStatement(insertQuery);
               pstmt.setInt(1, newId);
               pstmt.setInt(2, 2);
               pstmt.setInt(3, 1);
               pstmt.setString(4, nombre);
               pstmt.setInt(5, precio);
               pstmt.setBytes(6, image);
               pstmt.setInt(7, stock_disponible);
               pstmt.setInt(8, stock_minimo_requerido);
               int rowsInserted = pstmt.executeUpdate();
   
            if (rowsInserted > 0) {
                System.out.println("El producto ha sido insertado con éxito.");
                return true;
            } else {
                System.out.println("No se pudo insertar el producto.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error al insertar el producto: " + e.getMessage());
            return false;
        }
    }
}
