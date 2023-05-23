package Conexiones;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import Entitys.*;

public class Bitacora {


    public List<BitacoraAll> getAll4() throws ClassNotFoundException {
        List<BitacoraAll> bitacoraEntries = new ArrayList<>();
        
        try (Connection connection = DB2Config.getconnection2()) {
            // Verifica la conexión
            if (connection != null) {
                System.out.println("Conectado con éxito...");
            } else {
                System.out.println("Error al conectar...");
               // return bitacoraEntries;
            }
            
            System.out.println("Obteniendo datos de la tabla de bitácora...");
            
            // Ejecuta la consulta SELECT
            String selectQuery = "SELECT ID_PRODUCTO, NOMBRE_PRODUCTO, FECHA_ELIMINACION FROM dbo.BitacoreDelete;";
            ResultSet resultSet = connection.createStatement().executeQuery(selectQuery);
            
            // Recorre los resultados y crea objetos BitacoraEntry
            while (resultSet.next()) {
                BitacoraAll pd = new BitacoraAll();
                pd.setId_producto(resultSet.getInt("ID_PRODUCTO"));
                pd.setNombre_producto(resultSet.getString("NOMBRE_PRODUCTO"));
                pd.setFecha(resultSet.getString("FECHA_ELIMINACION"));
               
                bitacoraEntries.add(pd);
            }
            
            System.out.println("Datos de la tabla de bitácora obtenidos correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al obtener datos de la tabla de bitácora: " + e.getMessage());
        }
        
        return bitacoraEntries;
    }
}
