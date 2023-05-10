package ProductsJtid.function;

import java.util.*;
import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;
import com.google.gson.Gson;
import Entitys.*;
import servicerest.*;

/**
 * Azure Functions with HTTP Trigger.
 */
public class ProductsJtid {
    /**
     * This function listens at endpoint "/api/ProductsJtid". Two ways to invoke it using "curl" command in bash:
     * 1. curl -d "HTTP Body" {your host}/api/ProductsJtid
     * 2. curl {your host}/api/ProductsJtid?name=HTTP%20Query
     */
    @FunctionName("ProductsJtid")
    public HttpResponseMessage run(
        @HttpTrigger(name = "req", methods = {HttpMethod.GET}, route = "jt/{id}", authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<String>> request,
        @BindingName("id") int id,
        final ExecutionContext context) {
        context.getLogger().info("Java HTTP trigger processed a request for ID: " + id);

        try {
            List<Productjt> products = new ProductService().getAll2(id);
            if (products.isEmpty()) {
                return request.createResponseBuilder(HttpStatus.NOT_FOUND)
                    .body(String.format("No se encontró ningún producto con el ID %d", id))
                    .build();
            }
            String json = new Gson().toJson(products);
            return request.createResponseBuilder(HttpStatus.OK).body(json).build();
        } catch (ClassNotFoundException ex) {
            return request.createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al obtener data...\n" + ex.toString())
                .build();
        }
    }
}
