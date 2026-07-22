package dao;
import conexion.Conexion;
import modelo.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO implements ICrudDAO<Categoria>{
    private final Connection cn = Conexion.getInstancia().getConexion();
    
    @Override
    public boolean insertar(Categoria categoria){
        String sql ="INSERT INTO categoria (nombre) "
                + "VALUES(?)";
        try (PreparedStatement ps = cn.prepareStatement(sql)){
                ps.setString(1, categoria.nombre());
                return ps.executeUpdate() > 0;
                
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public boolean actualizar(Categoria categoria){
        String sql ="UPDATE categoria "
                + "SET nombre=? "
                + "WHERE id_categoria=?";
        try (PreparedStatement ps = cn.prepareStatement(sql);){
                ps.setString(1, categoria.nombre());
                ps.setInt(2, categoria.idCategoria());
                return ps.executeUpdate() > 0;
                
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
    
    
    @Override 
    public boolean eliminar(int idCategoria){
        String sql = "DELETE FROM categoria "
                + "WHERE id_categoria=?";
        
        try (PreparedStatement ps = cn.prepareStatement(sql);){
            ps.setInt(1, idCategoria);
            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public Categoria buscar(int idCategoria){
        String sql = "SELECT * FROM categoria "
                + "WHERE id_categoria=?";
        
        try (PreparedStatement ps = cn.prepareStatement(sql);){
                ps.setInt(1, idCategoria);
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    Categoria categoria = new Categoria(
                        rs.getInt("id_categoria"),
                           rs.getString("nombre"));
                    return categoria;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
            return null;
    }
    
    @Override
    public List<Categoria> listar(){
        List<Categoria> lista = new ArrayList<>();
        
        String sql = "SELECT * FROM categoria "
                + "ORDER BY nombre";
        
        try (PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();){
            while(rs.next()){
                Categoria categoria = new Categoria(
                    rs.getInt("id_categoria"),
                       rs.getString("nombre"));

                lista.add(categoria);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return lista;
    }
}
