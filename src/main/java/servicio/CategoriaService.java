package servicio;
import dao.CategoriaDAO;
import java.util.List;
import modelo.Categoria;

public class CategoriaService {
    
    private CategoriaDAO dao = new CategoriaDAO();
    
    public List<Categoria> listar(){
        return dao.listar();
    }
}
