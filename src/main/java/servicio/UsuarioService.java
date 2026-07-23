package servicio;
import dao.UsuarioDAO;
import java.util.List;
import modelo.Usuario;

public class UsuarioService {
    private final UsuarioDAO dao;
    
    public UsuarioService(){
        dao = new UsuarioDAO();
    }
    
    public boolean guardar(Usuario usuario){
        return dao.guardar(usuario);
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
    
    public Usuario login(String usuario, String clave){
        return dao.login(usuario, clave);
    }
    
    public boolean existeUsuario(String usuario) {
       return dao.existeUsuario(usuario);
    }
    
}
