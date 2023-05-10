package GetProductsJt.function;

import java.util.*;
import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;
import com.google.gson.Gson;
import Entitys.*;
import servicerest.*;

/**
 * Azure Functions with HTTP Trigger.
 */
public class GetProductsJt {
    /**
     * This function listens at endpoint "/api/GetProductsJt". Two ways to invoke it using "curl" command in bash:
     * 1. curl -d "HTTP Body" {your host}/api/GetProductsJt
     * 2. curl {your host}/api/GetProductsJt?name=HTTP%20Query
     */
    @FunctionName("GetProductsJt")
    public HttpResponseMessage run(
            @HttpTrigger(name = "req", methods = {HttpMethod.GET}, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<String>> request,
            final ExecutionContext context) {

        context.getLogger().info("Java HTTP trigger processed a request.");


        try {
            List<Product1> products2 = new ProductService().getAll1();
            Gson gson = new Gson();
            String jsonString = gson.toJson(products2 );
            return request.createResponseBuilder(HttpStatus.OK).body(jsonString).build();
        } catch (Exception ex) {
            return request.createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener data...\n" + ex.toString()).build();
        }
    }
}
