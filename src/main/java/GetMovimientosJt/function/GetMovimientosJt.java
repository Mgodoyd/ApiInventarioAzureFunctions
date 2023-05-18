package GetMovimientosJt.function;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;


import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.azure.functions.ExecutionContext;

import Conexiones.DB1Config;
import Entitys.*;
import GetMovimientosGt.function.GetMovimientosGt;
/**
 * Azure Functions with HTTP Trigger.
 */
public class GetMovimientosJt {
    /**
     * This function listens at endpoint "/api/GetMovimientosJt". Two ways to invoke it using "curl" command in bash:
     * 1. curl -d "HTTP Body" {your host}/api/GetMovimientosJt
     * 2. curl {your host}/api/GetMovimientosJt?name=HTTP%20Query
     */
    @FunctionName("GetMovimientosJt")
    public HttpResponseMessage run(
            @HttpTrigger(name = "req", methods = {HttpMethod.GET}, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<String>> request,
            final ExecutionContext context) throws ClassNotFoundException, JsonProcessingException {
        context.getLogger().info("Java HTTP trigger processed a request.");

        List<MovimientosGT> movimientosList = new ArrayList<>();

        try (Connection connection = DB1Config.getconnection1();) {
            // valida la conexion
            if (connection != null) {
                System.out.println("Conectado con exito...");
            } else {
                System.out.println("Error al conectar...");
            }

            java.sql.Statement statement = connection.createStatement();
            String selectSql = "SELECT * from dbo.MOVIMIENTOS;";
            ResultSet resultSet = statement.executeQuery(selectSql);

            // Almacenar los resultados en una lista
            while (resultSet.next()) {
                MovimientosGT movimiento = new MovimientosGT();
                movimiento.setId_movimiento(resultSet.getInt("id_movimiento"));
                movimiento.setId_producto(resultSet.getInt("id_producto"));
                movimiento.setId_usuario(resultSet.getInt("id_usuario"));
                movimiento.setId_ubicacion(resultSet.getInt("id_ubicacion"));
                movimiento.setCantidad_movidad(resultSet.getInt("cantidad_movida"));
                movimiento.setUbicacion_almacen_anterior(resultSet.getString("ubicacion_almacen_anterior"));
                movimiento.setUbicacion_almacen_nuevo(resultSet.getString("ubicacion_almacen_nuevo"));
                movimientosList.add(movimiento);

                System.out.println("Data read from the database: " + movimiento.toString());
            }
        } catch (SQLException e) {
            Logger.getLogger(GetMovimientosGt.class.getName()).log(Level.SEVERE, null, e.toString());
        }

         // Convertir la lista a JSON
    ObjectMapper objectMapper = new ObjectMapper();
    String json = objectMapper.writeValueAsString(movimientosList);

    // Construir la respuesta HTTP con formato JSON
    return request.createResponseBuilder(HttpStatus.OK)
        .header("Content-Type", "application/json")
        .body(json)
        .build();

    }
}
