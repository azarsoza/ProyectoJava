package dao;
import conexion.Conexiondb;
import modelo.Persona;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;

public class PersonaDAO  implements ICrudDAO<Persona>{
    
    @Override
    public boolean guardar(Persona persona) {
        String sql = " INSERT INTO persona "
                     + " (nombres, apellidos, dni, "
                     + " telefono, direccion, "
                     + " correo, activo) "
                     + " VALUES(?,?,?,?,?,?,?) ";

        try (Connection cn = Conexiondb.getInstance().conectar();
                PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, persona.getNombres());
            ps.setString(2, persona.getApellidos());
            ps.setString(3, persona.getDni());
            ps.setString(4, persona.getTelefono());
            ps.setString(5, persona.getDireccion());
            ps.setString(6, persona.getCorreo());
            ps.setBoolean(7, persona.isActivo());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        } 
    }

    @Override
    public boolean actualizar(Persona persona) {
         String sql = " UPDATE persona SET nombres=?, apellidos=?, "
                      + " dni=?, telefono=?, direccion=?, "
                      + " correo=?, activo=? "
                      + " WHERE id_persona=? ";

        try (Connection cn = Conexiondb.getInstance().conectar();
                PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, persona.getNombres());
            ps.setString(2, persona.getApellidos());
            ps.setString(3, persona.getDni());
            ps.setString(4, persona.getTelefono());
            ps.setString(5, persona.getDireccion());
            ps.setString(6, persona.getCorreo());
            ps.setBoolean(7, persona.isActivo());
            ps.setInt(8, persona.getIdPersona());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(int idPersona) {
        String sql = " DELETE FROM persona "
                      + " WHERE id_persona=? ";

        try (Connection cn = Conexiondb.getInstance().conectar();
                PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idPersona);
            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public Persona buscar(int idPersona) {
        String sql = " SELECT * FROM persona "
                      + " WHERE id_persona=? ";

        try (Connection cn = Conexiondb.getInstance().conectar();
                PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idPersona);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Persona(
                            rs.getInt("id_persona"),
                            rs.getString("nombres"),
                            rs.getString("apellidos"),
                            rs.getString("dni"),
                            rs.getString("telefono"),
                            rs.getString("direccion"),
                            rs.getString("correo"),
                            rs.getBoolean("activo"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Persona> listar() {
        List<Persona> lista = new ArrayList<>();

        String sql = " SELECT * FROM persona "
                     + " ORDER BY apellidos,nombres ";

        try (Connection cn = Conexiondb.getInstance().conectar();
                PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Persona persona = new Persona(
                        rs.getInt("id_persona"),
                        rs.getString("nombres"),
                        rs.getString("apellidos"),
                        rs.getString("dni"),
                        rs.getString("telefono"),
                        rs.getString("direccion"),
                        rs.getString("correo"),
                        rs.getBoolean("activo"));
                
                lista.add(persona);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return lista;
    }
    
    public int retornandoId(Persona persona,  Connection cn) throws SQLException {
        String sql = " INSERT INTO persona"
                    + " (nombres, apellidos, dni,"
                    + " telefono, direccion,"
                    +" correo, activo) "
                    + "VALUES (?,?,?,?,?,?,?)";
        
        try (
                PreparedStatement ps = cn.prepareStatement(sql,
            Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, persona.getNombres());
            ps.setString(2, persona.getApellidos());
            ps.setString(3, persona.getDni());
            ps.setString(4, persona.getTelefono());
            ps.setString(5, persona.getDireccion());
            ps.setString(6, persona.getCorreo());
            ps.setBoolean(7, persona.isActivo());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1);
                    }
                }
            }
        } 
        return -1;
    }
    
    public boolean actualizar(Persona persona, Connection cn) throws SQLException {
         String sql = " UPDATE persona SET nombres=?, apellidos=?, "
                      + " dni=?, telefono=?, direccion=?, "
                      + " correo=?, activo=? "
                      + " WHERE id_persona=? ";

        try (PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, persona.getNombres());
            ps.setString(2, persona.getApellidos());
            ps.setString(3, persona.getDni());
            ps.setString(4, persona.getTelefono());
            ps.setString(5, persona.getDireccion());
            ps.setString(6, persona.getCorreo());
            ps.setBoolean(7, persona.isActivo());
            ps.setInt(8, persona.getIdPersona());

            return ps.executeUpdate() > 0;
        }
    }
}
