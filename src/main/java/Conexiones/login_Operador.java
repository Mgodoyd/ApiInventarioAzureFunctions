package Conexiones;


import java.sql.SQLException;
import java.sql.Connection;

public  class login_Operador { //login del operador
    public boolean autenticar(String correo, String contrasena) throws ClassNotFoundException {
        boolean resultado = false;
        boolean resultado2 = false;

        String Rol = "Operador";

        try (Connection connection = DB1Config.getconnection1(); Connection connection2 = DB2Config.getconnection2();) {

            // valida conexiones
            if (connection != null) {
                System.out.println("Conectado con exito...");
            } else {
                System.out.println("Error al conectar...");
            }

            if (connection2 != null) {
                System.out.println("Conectado con exito...");
            } else {
                System.out.println("Error al conectar...");
            }
            // login dbJutiapa
            java.sql.Statement statement = connection.createStatement();
            String selectSql = "SELECT * FROM USUARIOS WHERE CORREO = '" + correo + "' AND CONTRASENA = '" + contrasena + "' AND RO = '" + Rol + "';";
            java.sql.ResultSet resultSet = statement.executeQuery(selectSql);

            if (resultSet.next()) {
                resultado = resultSet.getInt(1) > 0;
                System.out.println("Bienvenido Operador JT!!" + resultado);
            } else {
                System.out.println("No existe Rol en JT  o no es Rol Operador");
            }

            // login dbGuatemala
            java.sql.Statement statement2 = connection2.createStatement();
            String selectSql3 = "SELECT * FROM USUARIOS WHERE CORREO = '" + correo + "' AND CONTRASENA = '" + contrasena + "' AND RO = '" + Rol + "';";
            java.sql.ResultSet resultSet2 = statement2.executeQuery(selectSql3);

            if (resultSet2.next()) {
                resultado2 = resultSet2.getInt(1) > 0;
                System.out.println("Bienvenido Operador GT!!" + resultado);
            } else {
                System.out.println("No existe Rol en GT  o no es Rol Operador");
            }

        } catch (SQLException e) {
            System.out.println("Error al iniciar sesion : " + e.toString());
        }

        return resultado || resultado2;
    }
}
