package servicio;
import dao.EmpleadoDAO;
import java.util.List;
import modelo.Empleado;

public class EmpleadoService {
    private final EmpleadoDAO dao;
      
    public EmpleadoService() {
        dao = new EmpleadoDAO();
    }

    public boolean insertar(Empleado empleado) {
        return dao.insertar(empleado);
    }

    public boolean actualizar(Empleado empleado) {
        return dao.actualizar(empleado);
    }

    public boolean eliminar(int idEmpleado) {
        return dao.eliminar(idEmpleado);
    }

    public Empleado buscar(int idEmpleado) {
        return dao.buscar(idEmpleado);
    }

    public List<Empleado> listar() {
        return dao.listar();
    }
}
