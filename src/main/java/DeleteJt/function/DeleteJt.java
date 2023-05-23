package DeleteJt.function;

import java.util.*;
import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;
import Conexiones.*;
/**
 * Azure Functions with HTTP Trigger.
 */
public class DeleteJt {
    /**
     * This function listens at endpoint "/api/DeleteJt". Two ways to invoke it using "curl" command in bash:
     * 1. curl -d "HTTP Body" {your host}/api/DeleteJt
     * 2. curl {your host}/api/DeleteJt?name=HTTP%20Query
     */
    @FunctionName("DeleteJt")
    public HttpResponseMessage run(
        @HttpTrigger(name = "req", methods = {HttpMethod.DELETE}, route = "delete/jt/{id}", authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<String>> request,
        @BindingName("id") int id,
        final ExecutionContext context) {
    context.getLogger().info("Java HTTP trigger processed a request.");

    try {
        boolean isDeleted = new DeleteProductJt().delete(id);
        if(isDeleted){
            return request.createResponseBuilder(HttpStatus.OK).body("Producto Eliminado correctamente").build();
        }else{
            return request.createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR).body("No Existe Id para eliminar el producto" + "  " + id).build();
        }
    } catch (Exception e) {
        e.printStackTrace();
        String errorMessage = "Error al eliminar el producto: " + e.getMessage();
        context.getLogger().info(errorMessage);
        return request.createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage).build();
    }
}
}
