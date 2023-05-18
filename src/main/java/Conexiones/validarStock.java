package Conexiones;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class validarStock {
    public boolean ChangeProductById(int id, int cantidad) throws SQLException {
        ResultSet resultSet = null;

        try ( Connection connection2 = DB2Config.getconnection2()) {
            // Valida las conexiones

            if (connection2 != null) {
                System.out.println("Conectado con éxito 2...");
            } else {
                System.out.println("Error al conectar 2...");
            }

            // Obtiene el producto de la base de datos de Guatemala
            String selectSql = "SELECT * FROM dbo.PRODUCTOS WHERE ID_PRODUCTO = ?";
            PreparedStatement statement2 = connection2.prepareStatement(selectSql);
            statement2.setInt(1, id);
            resultSet = statement2.executeQuery();

            if (resultSet.next()) {
                System.out.println("Producto encontrado en la db: " + connection2);

                int stock = resultSet.getInt("stock");

                if (stock != 0) {
                    // Actualiza el stock restando la cantidad ingresada
                    String updateSql = "UPDATE dbo.PRODUCTOS SET STOCK = STOCK - ? WHERE ID_PRODUCTO = ?";
                    PreparedStatement updateStatement = connection2.prepareStatement(updateSql);
                    updateStatement.setInt(1, cantidad);
                    updateStatement.setInt(2, id);
                    int rowsUpdated = updateStatement.executeUpdate();

                    if (rowsUpdated > 0) {
                        System.out.println("Productos Enviados");
                    } else {
                        System.out.println("No se pudo actualizar el stock");
                    }
                } else {
                    System.out.println("No hay stock disponible");
                    return false;
                }
            } else {
                System.out.println("Producto no encontrado");
                return false;
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }

        return true;
    }
}
