package UpdatePassword.function;

import java.util.*;
import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;

import Conexiones.UpdatePasswordAll;

/**
 * Azure Functions with HTTP Trigger.
 */
public class UpdatePassword {
    @FunctionName("UpdatePassword")
    public HttpResponseMessage run(
            @HttpTrigger(name = "req", methods = {HttpMethod.PUT}, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<String>> request,
            @BindingName("correo") String correo,
            @BindingName("password") String password,
            final ExecutionContext context) {
        context.getLogger().info("Java HTTP trigger processed a request.");

        if (correo == null || password == null) {
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST).body("Please provide 'correo' and 'password' in the request").build();
        }

        UpdatePasswordAll updatePassword = new UpdatePasswordAll();

        try {
            boolean passwordUpdated = updatePassword.updatePassword(correo, password);
            
            if (passwordUpdated) {
                return request.createResponseBuilder(HttpStatus.OK).body("Password updated successfully").build();
            } else {
                return request.createResponseBuilder(HttpStatus.BAD_REQUEST).body("User does not exist").build();
            }
        } catch (ClassNotFoundException e) {
            return request.createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update password").build();
        }
        
    }
}
