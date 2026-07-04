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
        String sql ="INSERT INTO usuario (nombre, usuario, clave, rol, activo) "
                + "VALUES (?, ?, ?, ?, ?)";
        
        try (
                Connection cn = Conexion.getConexion();
                PreparedStatement ps = cn.prepareStatement(sql)
                ){
            
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getUsuario());
            ps.setString(3, usuario.getClave());
            ps.setString(4, usuario.getRol());
            ps.setString(5, usuario.getActivo());
            
             return ps.executeUpdate() > 0;
             
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
    
    public boolean actualizar(Usuario usuario){
        String sql = "UPDATE usuario "
                + "SET nombre = ?, usuario = ?,"
                + "clave = ?, rol = ?, activo = ? "
                + "WHERE id_usuario = ?";
        
        try (
                Connection cn = Conexion.getConexion();
                PreparedStatement ps = cn.prepareStatement(sql);
                ){
            
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getUsuario());
            ps.setString(3, usuario.getClave());
            ps.setString(4, usuario.getRol());
            ps.setString(5, usuario.getActivo());
            ps.setInt(6,usuario.getIdUsuario());
            
            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
    
    public boolean eliminar(int idUsuario){
        String sql = "UPDATE usuario "
                + "SET activo = 'Inactivo' "
                + "WHERE id_usuario = ?";
        
        try (
                Connection cn = Conexion.getConexion();
                PreparedStatement ps = cn.prepareStatement(sql); 
                ){
            
            ps.setInt(1, idUsuario);
            
            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
    
    public Usuario buscar(int idUsuario){
        String sql =" SELECT * FROM usuario "
                + "WHERE id_usuario = ?";
        
        try (
                Connection cn = Conexion.getConexion();
                PreparedStatement ps = cn.prepareStatement(sql);
                ){
                
                ps.setInt(1, idUsuario);
             try(ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    Usuario usuario = new Usuario();
                    
                    usuario.setIdUsuario(rs.getInt("id_usuario"));
                    usuario.setNombre(rs.getString("nombre"));
                    usuario.setUsuario(rs.getString("usuario"));
                    usuario.setRol(rs.getString("rol"));
                    usuario.setActivo(rs.getString("activo"));
                    
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
        
        String sql = "SELECT * FROM usuario "
                + "ORDER BY nombre";
        
        try (
                Connection cn = Conexion.getConexion();
                PreparedStatement ps = cn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                ){
            
            while(rs.next()){
                Usuario usuario = new Usuario();
                
                    usuario.setIdUsuario(rs.getInt("id_usuario"));
                    usuario.setNombre(rs.getString("nombre"));
                    usuario.setUsuario(rs.getString("usuario"));
                    usuario.setRol(rs.getString("rol"));
                    usuario.setActivo(rs.getString("activo"));
                    lista.add(usuario);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
            return lista;        
    }
    
    public Usuario login(String usuario, String clave){
        String sql = "SELECT * FROM usuario "
                    + "WHERE usuario = ? "
                    + "AND clave = ? "
                    + "AND activo = 'Activo'";
        
        try(
                Connection cn = Conexion.getConexion();
                PreparedStatement ps = cn.prepareStatement(sql)){
            
                ps.setString(1, usuario);
                ps.setString(2, clave);
                
               try(ResultSet rs = ps.executeQuery()){
                
                    if(rs.next()) {
                        Usuario usu = new Usuario();

                        usu.setIdUsuario(rs.getInt("id_usuario"));
                        usu.setNombre(rs.getString("nombre"));
                        usu.setUsuario(rs.getString("usuario"));
                        usu.setClave(rs.getString("clave"));
                        usu.setRol(rs.getString("rol"));
                        usu.setActivo(rs.getString("activo"));

                        return usu;
                    }
                }
            } catch(SQLException e){
                System.out.println("Error: " + e.getMessage());
            }
        return null;
    }
}
