package LoginAdministrador.function;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;


import Conexiones.login_Administrador;

//import analisisSistemas.function.LoginOperador;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.azure.functions.annotation.BindingName;
import java.io.IOException;
import java.util.Optional;

public class LoginAdministrador {
    /**
     * This function listens at endpoint "/api/loginAdministrador". Two ways to invoke it using "curl" command in bash:
     * 1. curl -d "HTTP Body" {your host}/api/loginAdministrador
     * 2. curl {your host}/api/loginAdministrador?name=HTTP%20Query
     */
    @FunctionName("loginAdministrador")
    public HttpResponseMessage run(
        @HttpTrigger(name = "req", methods = {HttpMethod.POST}, authLevel = AuthorizationLevel.ANONYMOUS) 
        HttpRequestMessage<Optional<String>> request,
        @BindingName("correo") String correo,
        @BindingName("password") String password,
        final ExecutionContext context) throws ClassNotFoundException {

        JsonNode json;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String requestBody = request.getBody().orElse("");
            json = objectMapper.readTree(requestBody);
        } catch (IOException e) {
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST)
                    .body("La cadena de solicitud HTTP no cumple con el formato esperado")
                    .build();
        }
        

        if (correo == null || password == null) {
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST)
                    .body("La cadena de solicitud HTTP no cumple con el formato esperado")
                    .build();
        }

        login_Administrador loginOperador = new login_Administrador();
        if (loginOperador.autenticar(correo, password)) {
            return request.createResponseBuilder(HttpStatus.OK)
                    .body("Bienvenido Administrador " + correo)
                    .build();
        } else {
            return request.createResponseBuilder(HttpStatus.UNAUTHORIZED)
                    .body("No se puede acceder")
                    .build();
        }
    }
}
