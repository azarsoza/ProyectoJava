package servicio;
import dao.PersonaDAO;
import java.util.List;
import modelo.Persona;

public class PersonaService {
    private final PersonaDAO dao;
    
    public PersonaService(){
        dao = new PersonaDAO();
    }
    
    public boolean guardar(Persona persona){
        return dao.guardar(persona);
    }
    
    public boolean actualizar(Persona persona){
        return dao.actualizar(persona);
    }
    
    public boolean eliminar(int idPersona){
        return dao.eliminar(idPersona);
    }
    
    public Persona buscar(int idPersona){
        return dao.buscar(idPersona);
    }
    
    public List<Persona> listar(){
        return dao.listar();
    }
}

