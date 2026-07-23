package principal;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.SQLException;
import vista.Principal;
import vista.FrmLogin;


public class Main {
    public static void main(String[] args) {         
        FrmLogin frm = new FrmLogin();
        frm.setLocationRelativeTo(null);
        frm.setVisible(true);
    }
}
