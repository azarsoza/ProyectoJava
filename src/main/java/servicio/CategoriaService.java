package servicio;
import dao.CategoriaDAO;
import java.util.List;
import modelo.Categoria;

public class CategoriaService {
    private CategoriaDAO dao;
    
    public CategoriaService(){
        dao = new CategoriaDAO();
    }
    
    public boolean insertar(Categoria categoria){
        return dao.insertar(categoria);
    }
    
    public boolean actualizar(Categoria categoria){
        return dao.actualizar(categoria);
    }
    
    public boolean eliminar(int idCategoria){
        return dao.elimnar(idCategoria);
    }
    
    public Categoria buscar(int idCategoria){
        return dao.buscar(idCategoria);
    }
    
    public List<Categoria> listar(){
        return dao.listar();
    }
}
