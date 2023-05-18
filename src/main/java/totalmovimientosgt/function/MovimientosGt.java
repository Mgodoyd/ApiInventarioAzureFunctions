package totalmovimientosgt.function;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import com.microsoft.azure.functions.annotation.*;

import Conexiones.DB2Config;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.microsoft.azure.functions.*;

/**
 * Azure Functions with HTTP Trigger.
 */
public class MovimientosGt {
    /**
     * This function listens at endpoint "/api/MovimientosGt". Two ways to invoke it using "curl" command in bash:
     * 1. curl -d "HTTP Body" {your host}/api/MovimientosGt
     * 2. curl {your host}/api/MovimientosGt?name=HTTP%20Query
     */
    @FunctionName("MovimientosGt")
    public HttpResponseMessage run(
        @HttpTrigger(name = "req", methods = {HttpMethod.GET}, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<String>> request,
        final ExecutionContext context) {
            
        context.getLogger().info("Java HTTP trigger processed a request.");

        
        try (Connection connection = DB2Config.getconnection2()) {
            // Verifica las conexiones
            if (connection != null) {
                System.out.println("Conectado con éxito...");
            } else {
                System.out.println("Error al conectar...");
            }

            // Consulta la tabla productos para obtener la suma de todo el stock
            String query = "SELECT COUNT(*) AS total FROM dbo.movimientos";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            int total = 0;
            if (rs.next()) {
                total = rs.getInt(1);
            }

            // Crea un objeto JSON con el total de stock y lo devuelve como respuesta
            JsonObject json = new JsonObject();
            json.addProperty("total", total);
            String jsonString = new Gson().toJson(json);
            return request.createResponseBuilder(HttpStatus.OK).body(jsonString).build();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error al buscar el total de movimientos: " + e.getMessage());
            return request.createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
}
