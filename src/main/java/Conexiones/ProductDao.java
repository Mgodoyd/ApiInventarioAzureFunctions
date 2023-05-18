package Conexiones;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import Entitys.*;
 
public class ProductDao  {//obtiene la lista de productos de Guatemala
    
    public List<Product> getAll() throws ClassNotFoundException, IOException{
        List<Product> products = new ArrayList<Product>();
       
        ResultSet resultSet = null;
        
        try (Connection connection= DB2Config.getconnection2();) {
             //valida conexion
             if(connection !=null){
               System.out.println("Conectado con exito...");   
             }else{
               System.out.println("Error al conectar...");   
             }
             
             
            java.sql.Statement statement =  connection.createStatement(); 
            
            String selectSql = "SELECT * from dbo.PRODUCTOS;";
            resultSet = statement.executeQuery(selectSql);
         

            // Print results from select statement
            while (resultSet.next()) {
                Product pd = new Product();
                pd.setId_producto(resultSet.getInt("id_producto"));
                pd.setId_usuario(resultSet.getInt("id_usuario"));
                pd.setId_ubicacion(resultSet.getInt("id_ubicacion"));
                pd.setNombre(resultSet.getString("nombbre"));
                pd.setPrecio(resultSet.getInt("precio"));
               // pd.setImg( resultSet.getString("img"));
             // Obtener los datos binarios de la columna 'img' como un flujo de entrada (InputStream)
                InputStream binaryStream = resultSet.getBinaryStream("img");
                
                // Leer los datos binarios del flujo de entrada y almacenarlos en un arreglo de bytes
                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                int bytesRead;
                byte[] data = new byte[4096];
                while ((bytesRead = binaryStream.read(data, 0, data.length)) != -1) {
                    buffer.write(data, 0, bytesRead);
                }
                byte[] imageData = buffer.toByteArray();
                
                pd.setImg(imageData);
                pd.setStock(resultSet.getInt("stock"));
                pd.setStock_minimo(resultSet.getInt("stock_minimo"));
                products.add(pd);
      
            System.out.println("Data read from the database: " + pd.toString());  
            }
        }
        catch (SQLException e) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE,null, e.toString());
        }
        return products;
         
    }


    public Product get(int id_producto, int id_usuario, int id_ubicacion, String nombre, int precio, byte[] img,
            int stock, int stock_minimo) {
        return null;
    }
}
