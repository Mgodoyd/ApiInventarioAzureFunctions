package Conexiones;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertProductsGt {
    public static boolean insert(String nombre_, int precio_, byte[] imagen_, int stock_disponible_, int stock_minimo_requerido_)  throws ClassNotFoundException {
        try(Connection connection = DB2Config.getconnection2()) {
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
               pstmt.setInt(3, 2);
               pstmt.setString(4, nombre_);
               pstmt.setInt(5, precio_);
               pstmt.setBytes(6, imagen_);
               pstmt.setInt(7, stock_disponible_);
               pstmt.setInt(8, stock_minimo_requerido_);
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
