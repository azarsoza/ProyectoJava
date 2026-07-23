package servicio;

import dao.CompraDAO;
import java.util.List;
import modelo.Compra;
import modelo.DetalleCompra;

public class CompraService {
    private final CompraDAO dao;
    
    public CompraService() {
        dao = new CompraDAO();
    }
    
    // Este método sirve para mandarle la compra al DAO
    public boolean insertar(Compra compra, List<DetalleCompra> detalles) {
        return dao.insertar(compra, detalles);
    }
    
    // Este método servirá para ver el historial de compras
    public List<Compra> listar() {
        return dao.listar();
    }
    
    public boolean eliminar(int idCompra) {
        return dao.eliminar(idCompra);
    }
    
    public List<Compra> listarCompras() {
        return dao.listarCompras();
    }
    
    public Compra buscarCompra(int idCompra){
        return dao.buscarCompra(idCompra);
    }

    public List<DetalleCompra> listarDetalleCompra(int idCompra){
        return dao.listarDetalleCompra(idCompra);
    }
}