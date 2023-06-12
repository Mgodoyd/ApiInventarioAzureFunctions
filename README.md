# ApiInventarioAzureFunctions
* Desarrolladas en Java 17 

Sigue los siguientes pasos para ejecutar la API "ApiInventarioAzureFunctions" en tu entorno local:
1. Asegúrate de tener una cuenta de Microsoft Azure. Si no tienes una cuenta, regístrate en Azure Portal y crea una nueva cuenta.
   
2. Crea una nueva función en Azure Functions en tu cuenta de Azure. Puedes seguir la documentación de Azure para obtener instrucciones detalladas sobre cómo crear una función: Crear una función en Azure

3. Clona el repositorio de "ApiInventarioAzureFunctions" en tu máquina local.

4. Abre el proyecto en tu editor de código preferido.

5. Configura las credenciales y configuraciones necesarias para la función. Puedes encontrar más información sobre cómo configurar las funciones de Azure en la documentación oficial de Azure Functions: Configuración de la aplicación en Azure Functions

6. Implementa la función en tu cuenta de Azure utilizando el comando adecuado para tu entorno de desarrollo. Por ejemplo, si estás utilizando la CLI de Azure, puedes ejecutar el siguiente comando:
```bash
func azure functionapp publish <nombre-de-la-funcion>
```
Esto desplegará la función en tu cuenta de Azure.

7. Una vez que la función esté desplegada, podrás acceder a los endpoints de la API según las rutas y configuraciones que hayas definido en el código.

### Front-end que utiliza AzureFunctions

Estas Functions son utilizadas como backend en el proyecto de React en el siguiente repositorio lo puedes encontrar:  [aqui](https://github.com/Mgodoyd/Inventarios.git). Asegúrate de seguir las instrucciones proporcionadas en la documentación de las Functions y configurarlo correctamente en el proyecto de React para que funcione de manera adecuada.
