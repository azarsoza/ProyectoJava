package servicio;
import dao.UsuarioDAO;
import java.util.List;
import modelo.Usuario;

public class UsuarioService {
    private UsuarioDAO dao;
    
    public UsuarioService(){
        dao = new UsuarioDAO();
    }
    
    public boolean insertar(Usuario usuario){
        return dao.insertar(usuario);
    }
    
    public boolean actualizar(Usuario usuario){
        return dao.actualizar(usuario);
    }
    
    public boolean eliminar(int idUsuario){
        return dao.eliminar(idUsuario);
    }
    
    public Usuario buscar(int idUsuario){
        return dao.buscar(idUsuario);
    }
    
    public List<Usuario> listar(){
        return dao.listar();
    }
    
}
