package StockEnviarJt.function;

import java.sql.SQLException;
import java.util.*;
import com.microsoft.azure.functions.annotation.*;

import Conexiones.ValidarStockJt;

import com.microsoft.azure.functions.*;

/**
 * Azure Functions with HTTP Trigger.
 */
public class StockEnviarJt {
    /**
     * This function listens at endpoint "/api/StockEnviarJt". Two ways to invoke it using "curl" command in bash:
     * 1. curl -d "HTTP Body" {your host}/api/StockEnviarJt
     * 2. curl {your host}/api/StockEnviarJt?name=HTTP%20Query
     */
    @FunctionName("StockEnviarJt")
    public HttpResponseMessage run(
            @HttpTrigger(name = "req", methods = {HttpMethod.DELETE}, route = "stockjt/{id}", authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<String>> request,
            @BindingName("id") int idProducto,
            @BindingName("cantidad") int cantidad,
            final ExecutionContext context) {
    
        context.getLogger().info("Java HTTP trigger processed a request.");
    
        try {
            ValidarStockJt stockValidator = new ValidarStockJt();
            boolean stockDisponible = stockValidator.ChangeProductById(idProducto, cantidad);
    
            if (stockDisponible) {
                // Stock disponible, realizar las acciones adicionales necesarias
                // ...
    
                return request.createResponseBuilder(HttpStatus.OK).body("Stock disponible").build();
            } else {
                // Stock no disponible, realizar las acciones adicionales necesarias
                // ...
    
                return request.createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR).body("Stock no disponible").build();
            }
        } catch (NumberFormatException e) {
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST).body("Invalid product ID or quantity").build();
        } catch (SQLException e) {
            return request.createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR).body("Error en la conexión a la base de datos").build();
        }
    }
}
