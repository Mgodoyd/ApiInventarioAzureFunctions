package StockEnviarGt.function;

import java.sql.SQLException;
import java.util.Optional;
import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.*;

import Conexiones.validarStock;

public class StockEnviarGt {
    @FunctionName("StockEnviarGt")
    public HttpResponseMessage run(
            @HttpTrigger(name = "req", methods = {HttpMethod.DELETE}, route = "stockgt/{id}", authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<String>> request,
            @BindingName("id") int idProducto,
            @BindingName("cantidad") int cantidad,
            final ExecutionContext context) {
    
        context.getLogger().info("Java HTTP trigger processed a request.");
    
        try {
            validarStock stockValidator = new validarStock();
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
