package InsertProductJt.function;

import java.io.IOException;
import com.microsoft.azure.functions.annotation.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.azure.functions.*;
import Conexiones.*;
import java.util.logging.LogRecord;



import java.util.logging.Level;

/**
 * Azure Functions with HTTP Trigger.
 */
public class InsertProductJt {
    /**
     * This function listens at endpoint "/api/InsertProductJt". Two ways to invoke it using "curl" command in bash:
     * 1. curl -d "HTTP Body" {your host}/api/InsertProductJt
     * 2. curl {your host}/api/InsertProductJt?name=HTTP%20Query
     */
    @FunctionName("InsertProductJt")
    public HttpResponseMessage run(
        @HttpTrigger(
            name = "req",
            methods = {HttpMethod.POST},
            authLevel = AuthorizationLevel.ANONYMOUS,
            route = "insert/jt") HttpRequestMessage<String> request,
        final ExecutionContext context) {
    
        // Obtener el contenido del cuerpo de la solicitud HTTP como String
        String requestBody = request.getBody();
    
        // Deserializar el objeto JSON en variables
        String nombre = null;
        int precio = 0;
        byte[] imagen = null;
        int stock = 0;
        int stock_minimo = 0;
    
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(requestBody);
            
            if (rootNode.get("NOMBRE") != null) {
                nombre = rootNode.get("NOMBRE").asText();
            }
            
            if (rootNode.get("PRECIO") != null) {
                precio = rootNode.get("PRECIO").asInt();
            }
            
            if (rootNode.get("IMG") != null) {
                imagen = rootNode.get("IMG").binaryValue();
            }
            
            if (rootNode.get("STOCK") != null) {
                stock = rootNode.get("STOCK").asInt();
            }
            
            if (rootNode.get("STOCK_MINIMO") != null) {
                stock_minimo = rootNode.get("STOCK_MINIMO").asInt();
            }
            
           // System.out.println(requestBody);
        } catch (IOException e) {
            e.printStackTrace();
            String errorMessage = "Error al procesar la solicitud: " + e.getMessage();
            context.getLogger().log(new LogRecord(Level.SEVERE, errorMessage));
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST)
                .body(errorMessage)
                .build();
        }

        // Validar si el producto ya existe
        try {
            boolean exists = InsertProductsJt.exists(nombre);
            if (exists) {
                return request.createResponseBuilder(HttpStatus.CONFLICT)
                    .body("El producto ya existe")
                    .build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            String errorMessage = "Error al buscar el producto: " + e.getMessage();
            context.getLogger().log(new LogRecord(Level.SEVERE, errorMessage));
            return request.createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorMessage)
                .build();
        }

    
        // Realizar la inserción
        try {
            boolean isInserted = InsertProductsJt.insert(nombre, precio, imagen, stock, stock_minimo);
    
            // Devolver la respuesta
            if (isInserted) {
                return request.createResponseBuilder(HttpStatus.OK)
                    .body("Producto insertado correctamente")
                    .build();
            } else {
                return request.createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al insertar el producto")
                    .build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            String errorMessage = "Error al insertar el producto: " + e.getMessage();
            context.getLogger().log(new LogRecord(Level.SEVERE, errorMessage));
            return request.createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorMessage)
                .build();
        }
    }
    
                
}
