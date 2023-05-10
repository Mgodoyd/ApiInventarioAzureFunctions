package Conexiones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public  class DB1Config { //conexion de Jutiapa
    public static Connection getconnection1() throws ClassNotFoundException {
        System.out.println("performing setup...");
        String connectionUrl =
                "jdbc:sqlserver://proyectoso2.database.windows.net:1433;"
                        + "database=dbjutiapa;"
                        + "user=adminuser@proyectoso2;"
                        + "password=Proyecto1;"
                        + "encrypt=true;"
                        + "trustServerCertificate=true;"
                        + "loginTimeout=30;";
        System.out.println("selecting data...");

        Connection connection = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(connectionUrl);
            System.out.println("Conectado a la db" + connection.getCatalog());
        } catch (SQLException e) {
            System.out.println("Error al Conectar..." + e.toString());
        }
        return connection;
    }
}
