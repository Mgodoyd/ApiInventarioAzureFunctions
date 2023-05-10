package getProducts.function;

import java.util.*;
import com.google.gson.Gson;
import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;

import Entitys.*;
import servicerest.*;

/**
 * Azure Functions with HTTP Trigger.
 */

 /*import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.azure.functions.annotation.BindingName;
import java.io.IOException;*/

public class GetProducts {
    /**
     * This function listens at endpoint "/api/GetProducts". Two ways to invoke it using "curl" command in bash:
     * 1. curl -d "HTTP Body" {your host}/api/GetProducts
     * 2. curl {your host}/api/GetProducts?name=HTTP%20Query
     */
    @FunctionName("GetProducts")
    public HttpResponseMessage run(
            @HttpTrigger(name = "req", methods = {HttpMethod.GET}, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<String>> request,
            final ExecutionContext context) {

        context.getLogger().info("Java HTTP trigger processed a request.");

        String json = null;
        String json2 = null;

        try {
            List<Product> products = new ProductService().getAll();
            List<Product1> products2 = new ProductService().getAll1();
            Contenedor conten = new Contenedor(products,products2);
            Gson gson = new Gson();
            String jsonString = gson.toJson(conten);
            return request.createResponseBuilder(HttpStatus.OK).body(jsonString).build();
        } catch (Exception ex) {
            return request.createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener data...\n" + ex.toString()).build();
        }
    }
}
