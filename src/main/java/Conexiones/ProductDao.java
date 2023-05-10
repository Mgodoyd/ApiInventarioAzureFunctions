package Conexiones;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import Entitys.*;
import org.apache.commons.lang3.ArrayUtils;

public class ProductDao  {//obtiene la lista de productos de Guatemala
    
    public List<Product> getAll() throws ClassNotFoundException{
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
                byte[] imgBytes = resultSet.getBytes("img");
                Byte[] img = ArrayUtils.toObject(imgBytes);
                pd.setImg(img);
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

    public Product get(int id, int precio, int id_ubicacion, String name, int precio1, Byte[] img1, int stock, int stock_minimo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
