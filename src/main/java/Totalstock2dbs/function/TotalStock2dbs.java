package Totalstock2dbs.function;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import com.microsoft.azure.functions.annotation.*;

import Conexiones.DB1Config;
import Conexiones.DB2Config;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.microsoft.azure.functions.*;

/**
 * Azure Functions with HTTP Trigger.
 */
public class TotalStock2dbs {
    /**
     * This function listens at endpoint "/api/TotalStock2dbs". Two ways to invoke it using "curl" command in bash:
     * 1. curl -d "HTTP Body" {your host}/api/TotalStock2dbs
     * 2. curl {your host}/api/TotalStock2dbs?name=HTTP%20Query
     */
    @FunctionName("TotalStock2dbs")
    public HttpResponseMessage run(
        @HttpTrigger(name = "req", methods = {HttpMethod.GET}, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<String>> request,
        final ExecutionContext context) {
    
        context.getLogger().info("Java HTTP trigger processed a request.");
    
        try {
            // Conectar a la primera base de datos
            Connection conn1 = DB1Config.getconnection1();
            Statement stmt1 = conn1.createStatement();
            ResultSet rs1 = stmt1.executeQuery("SELECT SUM(stock) FROM productos");
    
            // Obtener el resultado de la primera consulta
            int total1 = 0;
            if (rs1.next()) {
                total1 = rs1.getInt(1);
            }
    
            // Conectar a la segunda base de datos
            Connection conn2 = DB2Config.getconnection2();
            Statement stmt2 = conn2.createStatement();
            ResultSet rs2 = stmt2.executeQuery("SELECT SUM(stock) FROM productos");
    
            // Obtener el resultado de la segunda consulta
            int total2 = 0;
            if (rs2.next()) {
                total2 = rs2.getInt(1);
            }
    
            // Calcular el total de las dos bases de datos
            int total = total1 + total2;
    
            // Crear el objeto JSON con el total de stock
            JsonObject json = new JsonObject();
            json.addProperty("total", total);
            String jsonString = new Gson().toJson(json);
            return request.createResponseBuilder(HttpStatus.OK).body(jsonString).build();
            
    
        } catch (SQLException e) {
            context.getLogger().warning("Error al obtener el total de stock: " + e.getMessage());
            return request.createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener el total de stock").build();
        } catch (ClassNotFoundException e) {
            context.getLogger().warning("Error al conectar con la base de datos: " + e.getMessage());
            return request.createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al conectar con la base de datos").build();
        }
    }

}
