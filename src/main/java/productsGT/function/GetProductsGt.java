package productsGT.function;

import java.util.*;
import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;
import com.google.gson.Gson;
import Entitys.*;
import servicerest.*;

/**
 * Azure Functions with HTTP Trigger.
 */
public class GetProductsGt { 
    /**
     * This function listens at endpoint "/api/GetProductsGt". Two ways to invoke it using "curl" command in bash:
     * 1. curl -d "HTTP Body" {your host}/api/GetProductsGt
     * 2. curl {your host}/api/GetProductsGt?name=HTTP%20Query
     */
    @FunctionName("GetProductsGt")
    public HttpResponseMessage run(
        @HttpTrigger(name = "req", methods = {HttpMethod.GET}, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<String>> request,
        final ExecutionContext context) {

    context.getLogger().info("Java HTTP trigger processed a request.");


    try {
        List<Product> products = new ProductService().getAll();
 
        Gson gson = new Gson();
        String jsonString = gson.toJson(products);
        return request.createResponseBuilder(HttpStatus.OK).body(jsonString).build();
    } catch (Exception ex) {
        return request.createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener data...\n" + ex.toString()).build();
    }
}
}
