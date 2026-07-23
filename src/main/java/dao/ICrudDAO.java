package dao;
import java.util.List;

public interface ICrudDAO<T> {
    boolean guardar(T objeto);
    boolean actualizar(T objeto);
    boolean eliminar(int id);
    T buscar(int id);
    List<T> listar();
}
