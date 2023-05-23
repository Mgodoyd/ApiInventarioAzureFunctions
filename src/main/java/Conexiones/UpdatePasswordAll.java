package Conexiones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdatePasswordAll {
    public boolean updatePassword(String correo, String newPassword) throws ClassNotFoundException {
        try (Connection connection1 = DB1Config.getconnection1();
             Connection connection2 = DB2Config.getconnection2()) {
            if (connection1 != null && connection2 != null) {
                System.out.println("Conexiones exitosas a las bases de datos DB1 y DB2");
                
                // Verificar la existencia del usuario en DB1
                String selectSql1 = "SELECT * FROM USUARIOS WHERE CORREO = ?";
                PreparedStatement selectStmt1 = connection1.prepareStatement(selectSql1);
                selectStmt1.setString(1, correo);
                ResultSet resultSet1 = selectStmt1.executeQuery();
                
                if (!resultSet1.next()) {
                    System.out.println("El usuario no existe en la base de datos DB1");
                }
                
                // Verificar la existencia del usuario en DB2
                String selectSql2 = "SELECT * FROM USUARIOS WHERE CORREO = ?";
                PreparedStatement selectStmt2 = connection2.prepareStatement(selectSql2);
                selectStmt2.setString(1, correo);
                ResultSet resultSet2 = selectStmt2.executeQuery();
                
                if (!resultSet2.next()) {
                    System.out.println("El usuario no existe en la base de datos DB2");
                }
                
                // Actualizar la contraseña en la tabla userpackage de DB1
                String updateSql1 = "UPDATE USUARIOS SET CONTRASENA = ? WHERE CORREO = ?";
                PreparedStatement pstmt1 = connection1.prepareStatement(updateSql1);
                pstmt1.setString(1, newPassword);
                pstmt1.setString(2, correo);
                int rowsUpdated1 = pstmt1.executeUpdate();
                
                // Actualizar la contraseña en la tabla userpackage de DB2
                String updateSql2 = "UPDATE USUARIOS SET CONTRASENA = ? WHERE CORREO = ?";
                PreparedStatement pstmt2 = connection2.prepareStatement(updateSql2);
                pstmt2.setString(1, newPassword);
                pstmt2.setString(2, correo);
                int rowsUpdated2 = pstmt2.executeUpdate();
                
                if (rowsUpdated1 > 0 && rowsUpdated2 > 0) {
                    System.out.println("Contraseña actualizada con éxito en ambas bases de datos");
                    return true;
                } else {
                    System.out.println("No se pudo actualizar la contraseña en una o ambas bases de datos");
                    return false;
                }
            } else {
                System.out.println("Error al conectar a las bases de datos DB1 y DB2");
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar la contraseña: " + e.toString());
        }
        return false;
    }
}


