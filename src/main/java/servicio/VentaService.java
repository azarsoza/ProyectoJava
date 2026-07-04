package servicio;

import dao.VentaDAO;
import java.util.List;
import modelo.Venta;
import modelo.DetalleVenta;

public class VentaService {
    private VentaDAO dao;
    
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
}
