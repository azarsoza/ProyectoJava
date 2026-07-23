package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexiondb {
    private static final String URL = "jdbc:mysql://MYSQL1002.site4now.net:3306/db_aca8ee_tiendas";
    private static final String USER = "aca8ee_tiendas";
    private static final String PASSWORD = "Forza2026";
    
    private static Conexiondb instancia;
    private Connection conexion;
    
    private Conexiondb() {
    }

    public static Conexiondb getInstance() {
        if (instancia == null) {
            instancia = new Conexiondb();
        }
        return instancia;
    }

    public Connection conectar() {
        try {
            if (conexion == null || conexion.isClosed() || !conexion.isValid(2)) {
                 // Cierra la conexion anterior si todavía existe, antes de reemplazarla
                if (conexion != null && !conexion.isClosed()) {
                    try {
                        conexion.close();
                    } catch (SQLException ignored) {}
                }
                conexion = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Conectado a la Base de Datos");
            }
        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
        return conexion;
    }
}