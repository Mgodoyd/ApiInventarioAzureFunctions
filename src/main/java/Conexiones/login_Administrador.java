package Conexiones;

import java.sql.SQLException;
import java.sql.Connection;

public class login_Administrador {
    public String autenticar(String correo, String contrasena) throws ClassNotFoundException {
        String rol = null;

        try (Connection connection = DB1Config.getconnection1(); Connection connection2 = DB2Config.getconnection2();) {
            if (connection != null) {
                System.out.println("Conectado con éxito a la base de datos dbJT");
            } else {
                System.out.println("Error al conectar a la base de datos dbJT");
            }

            if (connection2 != null) {
                System.out.println("Conectado con éxito a la base de datos dbGT");
            } else {
                System.out.println("Error al conectar a la base de datos dbGT");
            }

            // Login dbJT
            java.sql.Statement statement = connection.createStatement();
            String selectSql = "SELECT RO FROM USUARIOS WHERE CORREO = '" + correo + "' AND CONTRASENA = '" + contrasena + "' AND (RO = 'Operador' OR RO = 'Administrador');";
            java.sql.ResultSet resultSet = statement.executeQuery(selectSql);

            if (resultSet.next()) {
                rol = resultSet.getString("RO");
                System.out.println("Bienvenido a dbJT! Rol: " + rol);
            } else {
                System.out.println("No se encontró un usuario con el correo y contraseña proporcionados en dbJT.");
            }

            // Login dbGT
            java.sql.Statement statement2 = connection2.createStatement();
            String selectSql2 = "SELECT RO FROM USUARIOS WHERE CORREO = '" + correo + "' AND CONTRASENA = '" + contrasena + "' AND (RO = 'Operador' OR RO = 'Administrador');";
            java.sql.ResultSet resultSet2 = statement2.executeQuery(selectSql2);

            if (resultSet2.next()) {
                rol = resultSet2.getString("RO");
                System.out.println("Bienvenido a dbGT! Rol: " + rol);
            } else {
                System.out.println("No se encontró un usuario con el correo y contraseña proporcionados en dbGT.");
            }

        } catch (SQLException e) {
            System.out.println("Error al iniciar sesión: " + e.toString());
        }

        return rol;
    }
}