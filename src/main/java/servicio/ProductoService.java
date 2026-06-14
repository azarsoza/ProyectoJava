package servicio;

import dao.ProductoDAO;
import modelo.Producto;
import java.util.List;

public class ProductoService {
    private ProductoDAO dao;
    
    public ProductoService(){
        dao = new ProductoDAO();
    }
    
    public boolean insertar(Producto producto){
        return dao.insertar(producto);
    }
    
    public boolean actualizar(Producto producto){
        return dao.actualizar(producto);
    }
    
    public boolean eliminar(int idProducto){
        return dao.eliminar(idProducto);
    }
    
    public Producto buscar(int idProducto){
        return dao.buscar(idProducto);
    }
    
    public List<Producto> listar(){
        return dao.listar();
    }
}
