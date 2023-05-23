package changeGT.function;

import java.util.*;
import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;

import Conexiones.*;
/**
 * Azure Functions with HTTP Trigger.
 */
public class ChangeGt {
    /**
     * This function listens at endpoint "/api/ChangeGt". Two ways to invoke it using "curl" command in bash:
     * 1. curl -d "HTTP Body" {your host}/api/ChangeGt
     * 2. curl {your host}/api/ChangeGt?name=HTTP%20Query
     */
    @FunctionName("ChangeGt")
        public HttpResponseMessage run(
        @HttpTrigger(name = "req", methods = { HttpMethod.DELETE }, route = "gtdel/{id}", authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<String>> request,
        @BindingName("id") int id,
        @BindingName("stockresto") int stockresto,
        final ExecutionContext context) {
        
        context.getLogger().info("Java HTTP trigger processed a request for ID: " + id);

        try {
            boolean isDeleted = new ChangeProductGt().ChangeProductById(id,stockresto);
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
