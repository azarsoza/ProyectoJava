package principal;

import conexion.Conexion;
import java.sql.Connection;
import vista.FrmProducto;

public class Main {
    public static void main(String[] args) {
        Connection cn = Conexion.getConexion();
        
        if (cn != null) {
            System.out.println("Conexion exitosa");
            FrmProducto frm = new FrmProducto();
            frm.setLocationRelativeTo(null);
            frm.setVisible(true);
        }else{
            System.out.println("Error de conexion");
        }
    }
}
