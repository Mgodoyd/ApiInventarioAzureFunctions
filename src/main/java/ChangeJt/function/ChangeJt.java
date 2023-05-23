package ChangeJt.function;

import java.util.*;
import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;

import Conexiones.*;
/**
 * Azure Functions with HTTP Trigger.
 */
public class ChangeJt {
    /**
     * This function listens at endpoint "/api/ChangeJt". Two ways to invoke it using "curl" command in bash:
     * 1. curl -d "HTTP Body" {your host}/api/ChangeJt
     * 2. curl {your host}/api/ChangeJt?name=HTTP%20Query
     */
    @FunctionName("ChangeJt")
    public HttpResponseMessage run(
        @HttpTrigger(name = "req", methods = { HttpMethod.DELETE }, route = "jtdel/{id}", authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<String>> request,
        @BindingName("id") int id,
        @BindingName("stockresto") int stockresto,
        final ExecutionContext context) {
        
        context.getLogger().info("Java HTTP trigger processed a request for ID: " + id);

        try {
            boolean isDeleted = new ChangeProductJt().ChangeProductById(id,stockresto);
            if (isDeleted) {
                return request.createResponseBuilder(HttpStatus.OK).body("Stock actualizado").build();
            } else {
                return request.createResponseBuilder(HttpStatus.NOT_FOUND).body("No existe el ID para actualizar el stock").build();
            }
        } catch (Exception ex) {
            return request.createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener data...\n" + ex.toString()).build();
        }
    }
}
