package dao;
import conexion.Conexiondb;
import modelo.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO implements ICrudDAO<Categoria>{
    
    @Override
    public boolean guardar(Categoria categoria){
        String sql ="INSERT INTO categoria (nombre) VALUES(?)";
        
        try (Connection cn = Conexiondb.getInstance().conectar();
                PreparedStatement ps = cn.prepareStatement(sql)){
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
        
        try (Connection cn = Conexiondb.getInstance().conectar();
                PreparedStatement ps = cn.prepareStatement(sql)){
            
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
        
        try (Connection cn = Conexiondb.getInstance().conectar();
                PreparedStatement ps = cn.prepareStatement(sql)){
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
        
        try (Connection cn = Conexiondb.getInstance().conectar();
                PreparedStatement ps = cn.prepareStatement(sql)){
                ps.setInt(1, idCategoria);
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    return new Categoria(
                            rs.getInt("id_categoria"),
                            rs.getString("nombre")
                    );
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
        
        try (Connection cn = Conexiondb.getInstance().conectar();
                PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){
            while(rs.next()){
                lista.add(new Categoria(
                        rs.getInt("id_categoria"),
                        rs.getString("nombre")
));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return lista;
    }
}
