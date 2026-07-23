package dao;
import conexion.Conexiondb;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Usuario;
import modelo.Empleado;

public class UsuarioDAO  implements ICrudDAO<Usuario> {
    
    @Override
    public boolean guardar(Usuario usuario){
        String sql = " INSERT INTO usuario (id_empleado, usuario, clave, rol, activo) "
                    + " VALUES (?, ?, ?, ?, ?)";
        
        try (Connection cn = Conexiondb.getInstance().conectar();
                PreparedStatement ps = cn.prepareStatement(sql)){
            
            ps.setInt(1, usuario.getEmpleado().getIdEmpleado());
            ps.setString(2, usuario.getUsuario());
            ps.setString(3, usuario.getClave());
            ps.setString(4, usuario.getRol());
            ps.setString(5, usuario.getActivo());
            
             return ps.executeUpdate() > 0;
             
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }
    
    @Override
    public boolean actualizar(Usuario usuario){
        String sql = " UPDATE usuario "
                    + " SET id_empleado = ?, "
                    + " usuario = ?,"
                    + " clave = ?,"
                    + " rol = ?,"
                    + " activo = ? "
                    + "WHERE id_usuario = ?";
        
        try (Connection cn = Conexiondb.getInstance().conectar();
                PreparedStatement ps = cn.prepareStatement(sql)){
            
            ps.setInt(1, usuario.getEmpleado().getIdEmpleado());
            ps.setString(2, usuario.getUsuario());
            ps.setString(3, usuario.getClave());
            ps.setString(4, usuario.getRol());
            ps.setString(5, usuario.getActivo());
            ps.setInt(6, usuario.getIdUsuario());
            
            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }
    
    @Override
    public boolean eliminar(int idUsuario){
        String sql = " DELETE FROM Usuario WHERE id_usuario = ?";
        
        try (Connection cn = Conexiondb.getInstance().conectar();
                PreparedStatement ps = cn.prepareStatement(sql)){
            ps.setInt(1, idUsuario);
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }
    
    @Override
    public Usuario buscar(int idUsuario){
        String sql = " SELECT u.id_usuario, u.usuario, "
                    + " u.clave, u.rol, u.activo, "
                    + " e.id_empleado, p.nombres, p.apellidos "
                    + " FROM Usuario u " 
                    + " INNER JOIN Empleado e ON u.id_empleado = e.id_empleado "
                    + " INNER JOIN Persona p ON e.id_persona = p.id_persona "
                    + " WHERE u.id_usuario = ?";
        
        try (Connection cn = Conexiondb.getInstance().conectar();
                PreparedStatement ps = cn.prepareStatement(sql)){
                ps.setInt(1, idUsuario);
            
                try(ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    Usuario u = new Usuario();
                    
                    Empleado emp = new Empleado();

                    emp.setIdEmpleado(rs.getInt("id_empleado"));
                    emp.setNombres(rs.getString("nombres"));
                    emp.setApellidos(rs.getString("apellidos"));

                    u.setIdUsuario(rs.getInt("id_usuario"));
                    u.setEmpleado(emp);
                    u.setUsuario(rs.getString("usuario"));
                    u.setClave(rs.getString("clave"));
                    u.setRol(rs.getString("rol"));
                    u.setActivo(rs.getString("activo"));
                }
             }            
        } catch (SQLException e) {
             System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
    
    @Override
    public List<Usuario> listar(){
        List<Usuario> lista = new ArrayList<>();
        
        String sql = " SELECT u.id_usuario, u.id_empleado,"
                    + " p.nombres, p.apellidos, "
                    + " u.usuario, u.rol, u.activo "
                    + " FROM Usuario u INNER JOIN Empleado e "
                    + " ON u.id_empleado = e.id_empleado "
                    + " INNER JOIN Persona p "
                    + " ON e.id_persona = p.id_persona "
                    + " ORDER BY u.id_usuario ";
        
        try (Connection cn = Conexiondb.getInstance().conectar();
                PreparedStatement ps = cn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()){
            
            while (rs.next()) {
                Usuario u = new Usuario();
                Empleado emp = new Empleado();
                
                emp.setIdEmpleado(rs.getInt("id_empleado"));
                emp.setNombres(rs.getString("nombres"));
                emp.setApellidos(rs.getString("apellidos"));
                
                u.setIdUsuario(rs.getInt("id_usuario"));
                u.setEmpleado(emp);
                u.setUsuario(rs.getString("usuario"));
                u.setRol(rs.getString("rol"));
                u.setActivo(rs.getString("activo"));
                
                lista.add(u);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return lista;        
    }
    
    public Usuario login(String usuario, String clave){
        String sql = " SELECT u.id_usuario, u.id_empleado, "
                    + " p.nombres, p.apellidos, "
                    + " u.usuario, u.clave, u.rol, u.activo "
                    + " FROM Usuario u INNER JOIN Empleado e "
                    + " ON u.id_empleado = e.id_empleado "
                    + " INNER JOIN Persona p "
                    + " ON e.id_persona = p.id_persona "
                    + " WHERE u.usuario = ? "
                    + " AND u.clave = ? "
                    + " AND u.activo = 'Activo'";
        
        try(Connection cn = Conexiondb.getInstance().conectar();
                PreparedStatement ps = cn.prepareStatement(sql)){
            ps.setString(1, usuario);
            ps.setString(2, clave);
            
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    Empleado emp = new Empleado();
                    emp.setIdEmpleado(rs.getInt("id_empleado"));
                    emp.setNombres(rs.getString("nombres"));
                    emp.setApellidos(rs.getString("apellidos"));
                        
                    Usuario u = new Usuario();
                    u.setIdUsuario(rs.getInt("id_usuario"));
                    u.setEmpleado(emp);
                    u.setUsuario(rs.getString("usuario"));
                    u.setClave(rs.getString("clave"));
                    u.setRol(rs.getString("rol"));
                    u.setActivo(rs.getString("activo"));
                    return u;
                }
            }
        } catch(SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
    
    public boolean existeUsuario(String usuario) {
        
        String sql = "SELECT 1 FROM usuario WHERE usuario = ?";
        
        try (Connection cn = Conexiondb.getInstance().conectar();
                PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, usuario);
            
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }
       
}
