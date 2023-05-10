package UpdateProductGt.function;

import java.io.StringReader;
import java.util.*;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.Form;

import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;

import com.microsoft.azure.functions.annotation.*;

import Conexiones.UpdateProductsGt;

import com.google.api.client.json.Json;
import com.google.gson.JsonObject;
import com.microsoft.azure.functions.*;





/**
 * Azure Functions with HTTP Trigger.
 */
public class UpdateProductGt {
    /**
     * This function listens at endpoint "/api/UpdateProductGt". Two ways to invoke it using "curl" command in bash:
     * 1. curl -d "HTTP Body" {your host}/api/UpdateProductGt
     * 2. curl {your host}/api/UpdateProductGt?name=HTTP%20Query
     */
    
                @FunctionName("UpdateProductGT")
                public HttpResponseMessage run(
                    @HttpTrigger(
                        name = "req",
                        methods = {HttpMethod.PUT},
                        route = "update/gt/{id}",
                        authLevel = AuthorizationLevel.ANONYMOUS)
                    HttpRequestMessage<Optional<JsonObject>> request,
                    @BindingName("id") String id,
                    final ExecutionContext context) {
            
                    context.getLogger().info("Java HTTP trigger processed a request.");
            
                    try {
                        // Obtiene los datos del cuerpo del mensaje como un objeto JSON
                        JsonObject body = request.getBody().get();
            
                        // Obtiene los valores de los campos del objeto JSON
                        String nombre = body.get("NOMBRE").getAsString();
                        int precio = body.get("PRECIO").getAsInt();
                        byte[] imagen = body.get("IMG").getAsString().getBytes();
                        int stock_disponible = body.get("STOCK").getAsInt();
                        int stock_minimo_requerido = body.get("STOCK_MINIMO").getAsInt();
            
                        // Llama a la lógica de tu aplicación para procesar los datos
                        UpdateProductsGt updateProductGT = new UpdateProductsGt();
                        boolean isInserted = updateProductGT.update(Integer.parseInt(id), nombre, precio, imagen, stock_disponible, stock_minimo_requerido);
            
                        // Retorna una respuesta
                        if (isInserted) {
                            return request.createResponseBuilder(HttpStatus.OK)
                                .body("Producto actualizado correctamente")
                                .build();
                        } else {
                            return request.createResponseBuilder(HttpStatus.BAD_REQUEST)
                                .body("Error al actualizar el producto")
                                .build();
                        }
                    } catch (Exception ex) {
                        return request.createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body("Error del servidor: " + ex.getMessage())
                            .build();
                    }
                }
    }
    
    



