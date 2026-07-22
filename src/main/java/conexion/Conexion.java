package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final String URL = "jdbc:mysql://MYSQL1002.site4now.net:3306/db_aca8ee_tiendas";
    private static final String USER = "aca8ee_tiendas";
    private static final String PASSWORD = "Forza2026";
    
    private static Conexion instancia;
    private Connection conexion;
    
    private Conexion(){
        try{
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public static Conexion getInstancia(){
        if(instancia == null){
            instancia = new Conexion();
        }
        return instancia;
    }
    
    public Connection getConexion(){
        return conexion;
    }
    
//    public static Connection getConexion() throws SQLException{
//        return DriverManager.getConnection(URL,USER,PASSWORD);
//    }
}