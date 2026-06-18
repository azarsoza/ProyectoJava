package dao;
import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Usuario;

public class UsuarioDAO {
    
    public boolean insertar(Usuario usuario){
        String sql ="";
        
        try (
                Connection cn = Conexion.getConexion();
                PreparedStatement ps = cn.prepareStatement(sql)
                ){
            
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getUsuario());
            ps.setString(3, usuario.getRol());
            ps.setBoolean(4, usuario.getActivo());
            
             return ps.executeUpdate() > 0;
             
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
    
    public boolean actualizar(Usuario usuario){
        String sql = "";
        
        try (
                Connection cn = Conexion.getConexion();
                PreparedStatement ps = cn.prepareStatement(sql);
                ){
            
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getUsuario());
            ps.setString(3, usuario.getRol());
            ps.setBoolean(4, usuario.getActivo());
            ps.setInt(5,usuario.getIdUsuario());
            
            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
    
    public boolean eliminar(int idUsuario){
        String sql = "";
        
        try (
                Connection cn = Conexion.getConexion();
                PreparedStatement ps = cn.prepareStatement(sql); 
                ){
            
            ps.setInt(1, idUsuario);
            ps.executeUpdate();
            
            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
    
    public Usuario buscar(int idUsuario){
        String sql ="";
        
        try (
                Connection cn = Conexion.getConexion();
                PreparedStatement ps = cn.prepareStatement(sql);
                ){
             try(ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    Usuario usuario = new Usuario();
                    
                    usuario.setIdUsuario(rs.getInt("idUsuario"));
                    usuario.setNombre(rs.getString("nombre"));
                    usuario.setUsuario(rs.getString("usuario"));
                    usuario.setClave(rs.getString("clave"));
                    usuario.setActivo(rs.getBoolean("activo"));
                    
                    return usuario;
                }
             }            
        } catch (SQLException e) {
             System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
    
    public List<Usuario> listar(){
        List<Usuario> lista = new ArrayList<>();
        
        String sql = "";
        
        try (
                Connection cn = Conexion.getConexion();
                PreparedStatement ps = cn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                ){
            
            while(rs.next()){
                Usuario usuario = new Usuario();
                
                    usuario.setIdUsuario(rs.getInt("idUsuario"));
                    usuario.setNombre(rs.getString("nombre"));
                    usuario.setUsuario(rs.getString("usuario"));
                    usuario.setClave(rs.getString("clave"));
                    usuario.setActivo(rs.getBoolean("activo"));
                    lista.add(usuario);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
            return lista;        
    }
}
