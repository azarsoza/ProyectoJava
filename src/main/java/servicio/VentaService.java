package servicio;
import dao.VentaDAO;
import java.util.List;
import modelo.Venta;
import modelo.DetalleVenta;

public class VentaService {
    private final VentaDAO dao;
    
    public VentaService() {
        dao = new VentaDAO();
    }
    
    // Este método sirve para mandarle la venta y el carrito al DAO
    public boolean insertar(Venta venta, List<DetalleVenta> detalles) {
        return dao.insertar(venta, detalles);
    }
    
    // Este método servirá para ver el historial en una tabla
    public List<Venta> listar() {
        return dao.listar();
    }
    
    public boolean eliminar(int idVenta) {
        return dao.eliminar(idVenta);
    }
    
    public List<Venta> listarVentas() {
        return dao.listarVentas();
    }
    
    public Venta buscarVenta(int idVenta){
        return dao.buscarVenta(idVenta);
    }

    public List<DetalleVenta> listarDetalleVenta(int idVenta){
        return dao.listarDetalleVenta(idVenta);
    }
}
