package principal;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.SQLException;
import vista.FrmProducto;
import vista.Principal;
import vista.FrmLogin;
import vista.FrmCategoria;

public class Main {
    public static void main(String[] args) {         
        FrmCategoria frm = new FrmCategoria();
        frm.setLocationRelativeTo(null);
        frm.setVisible(true);
    }
}
