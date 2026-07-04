package principal;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.SQLException;
import vista.FrmProducto;
import vista.Principal;

public class Main {
    public static void main(String[] args) {
        //try {
         //   Connection cn = Conexion.getConexion() ;
         //   System.out.println("Conexion exitosa");
         //   cn.close();
            
            Principal frm = new Principal();
            frm.setLocationRelativeTo(null);
            frm.setVisible(true);
            
        //} catch (SQLException e) {
        //    System.out.println("Error de conexion" + e.getMessage());
        //}
    }
}
