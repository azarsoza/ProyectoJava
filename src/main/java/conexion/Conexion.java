package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final String URL = "jdbc:mysql://MYSQL1002.site4now.net:3306/db_aca8ee_tiendas";
    private static final String USER = "aca8ee_tiendas";
    private static final String PASSWORD = "Forza2026";
    
    public static Connection getConexion() throws SQLException{
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
            return null;
        }
    }
}