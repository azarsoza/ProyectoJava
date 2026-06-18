package principal;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.SQLException;
import vista.FrmProducto;

public class Main {
    public static void main(String[] args) {
        try {
            Connection cn = Conexion.getConexion() ;
            System.out.println("Conexion exitosa");
            cn.close();
            
            FrmProducto frm = new FrmProducto();
            frm.setLocationRelativeTo(null);
            frm.setVisible(true);
            
        } catch (SQLException e) {
            System.out.println("Error de conexion" + e.getMessage());
        }
    }
}
