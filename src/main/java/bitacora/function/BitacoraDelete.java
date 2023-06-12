package bitacora.function;

import java.util.*;
import com.microsoft.azure.functions.annotation.*;

import Entitys.BitacoraAll;
import Entitys.BitacoraJtAll;
import Entitys.Contenedor2;
import servicerest.ProductService;

import com.google.gson.Gson;
import com.microsoft.azure.functions.*;

/**
 * Azure Functions with HTTP Trigger.
 */
public class BitacoraDelete {
    /**
     * This function listens at endpoint "/api/BitacoraDelete". Two ways to invoke it using "curl" command in bash:
     * 1. curl -d "HTTP Body" {your host}/api/BitacoraDelete
     * 2. curl {your host}/api/BitacoraDelete?name=HTTP%20Query
     */
    @FunctionName("BitacoraDelete") //nombre de la funcion
    public HttpResponseMessage run(
        @HttpTrigger(name = "req", methods = {HttpMethod.GET}, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<String>> request,//metodo http y nivel de autorizacion anonimo para que cualquiera pueda acceder
        final ExecutionContext context) {

    context.getLogger().info("Java HTTP trigger processed a request.");


    String json = null;
    String json2 = null;

    try {
        List<BitacoraAll> products = new ProductService().getAll4(); //obtiene todos los registros de la tabla
        List<BitacoraJtAll> products2 = new ProductService().getAll5(); //obtiene todos los registros de la tabla
        Contenedor2 conten = new Contenedor2(products,products2);
        Gson gson = new Gson();
        String jsonString = gson.toJson(conten);
        return request.createResponseBuilder(HttpStatus.OK).body(jsonString).build();
    } catch (Exception ex) {
        return request.createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener data...\n" + ex.toString()).build();
    }
}

   
}
