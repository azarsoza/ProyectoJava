package dao;
import conexion.Conexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Empleado;

public class EmpleadoDAO implements ICrudDAO<Empleado>{
    private final Connection cn = Conexion.getInstancia().getConexion();
    private final PersonaDAO personaDAO = new PersonaDAO();
    
    @Override
    public boolean insertar(Empleado empleado) {
        
        String sql = " INSERT INTO empleado "
                    + " (id_persona, cargo, fecha_ingreso, salario) "
                    + " VALUES (?, ?, ?, ?) ";
        
        try{
            cn.setAutoCommit(false);      
            int idPersona = personaDAO.retornandoId(empleado);
            
            if(idPersona == -1){
                cn.rollback();
                return false;
            }
            
            try(PreparedStatement ps = cn.prepareStatement(sql)){
                ps.setInt(1, idPersona);
                ps.setString(2, empleado.getCargo());
                ps.setDate(3, Date.valueOf(empleado.getFechaIngreso()));
                ps.setDouble(4, empleado.getSalario());
            
                if (ps.executeUpdate() <= 0) {
                    cn.rollback();
                    return false;
                }

                cn.commit();
                return true;
            }   
        }catch (SQLException e){
            rollback();
            System.out.println("Error: " + e.getMessage());
            return false;
        }finally{
           restaurarAutoCommit();
        }
    }

    @Override
    public boolean actualizar(Empleado empleado) {
        String sql = " UPDATE empleado "
                     + " SET cargo=?, "
                     + " fecha_ingreso=?, "
                     + " salario=? "
                     + " WHERE id_empleado=? ";

        try{
            cn.setAutoCommit(false);
            
            if(!personaDAO.actualizar(empleado)){
                cn.rollback();
                return false;
            }        
            
            try (PreparedStatement ps = cn.prepareStatement(sql)){
            
                ps.setString(1, empleado.getCargo());
                ps.setDate(2, Date.valueOf(empleado.getFechaIngreso()));
                ps.setDouble(3, empleado.getSalario());
                ps.setInt(4, empleado.getIdEmpleado());

                if (ps.executeUpdate() <= 0){
                    cn.rollback();
                    return false;
                }

                cn.commit();
                return true;            
            }
        } catch (SQLException e) {
            rollback();            
            System.out.println("Error: " + e.getMessage());
            return false;
        } finally{
            restaurarAutoCommit();
        }
    }

    @Override
    public boolean eliminar(int idEmpleado) {
        String sqlBuscar = "SELECT id_persona FROM empleado "
                           + "WHERE id_empleado=?";
        
        String sqlEliminaEmpleado = "DELETE FROM empleado "
                                    + " WHERE id_empleado=? ";
        
        String sqlEliminaPersona = "DELETE FROM persona "
                                   + " WHERE id_persona=? ";
         

        try{
            cn.setAutoCommit(false);
            int idPersona;
            
            try (PreparedStatement ps = cn.prepareStatement(sqlBuscar)) {
                ps.setInt(1, idEmpleado);
                try(ResultSet rs = ps.executeQuery()) {
                    if(!rs.next()) {
                        cn.rollback();
                        return false;
                    }
                    idPersona = rs.getInt("idPersona");
                }
            } 
        
            //AQUI ELIMINAMOS EL EMPLEADO
            try (PreparedStatement ps = cn.prepareStatement(sqlEliminaEmpleado)) {
                ps.setInt(1, idEmpleado);

                if (ps.executeUpdate() <= 0) {
                    cn.rollback();
                    return false;
                }
            }
        
            // AQUI ELMINAMOS A LA PERSONA
            try (PreparedStatement ps = cn.prepareStatement(sqlEliminaPersona)) {
                ps.setInt(1, idPersona);

                if (ps.executeUpdate() <= 0) {
                    cn.rollback();
                    return false;
                }
            }
            cn.commit();
            return true;
        }catch (SQLException e) {
            rollback();
            System.out.println("Error: " + e.getMessage());
            return false;
        }finally{
            restaurarAutoCommit();
        }
    }

    @Override
    public Empleado buscar(int idEmpleado) {
         String sql = " SELECT e.id_empleado, p.id_persona, p.nombres, "
                    + " p.apellidos, p.dni, p.telefono, p.direccion, "
                    + " p.correo, p.activo, e.cargo, e.fecha_ingreso, e.salario "
                    + " FROM empleado e INNER JOIN persona p "
                    + " ON e.id_persona = p.id_persona "
                    + " WHERE e.id_empleado=?";

        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idEmpleado);
            
            try(ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Empleado(
                        rs.getInt("id_empleado"),
                        rs.getInt("id_persona"),
                        rs.getString("nombres"),
                        rs.getString("apellidos"),
                        rs.getString("dni"),
                        rs.getString("telefono"),
                        rs.getString("direccion"),
                        rs.getString("correo"),
                        rs.getBoolean("activo"),
                        rs.getString("cargo"),
                        rs.getDate("fecha_ingreso").toLocalDate(),
                        rs.getDouble("salario")
                    );
                }
            }
            
        } catch(SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Empleado> listar() {
        List<Empleado> lista = new ArrayList<>();

        String sql = "SELECT e.id_empleado, p.id_persona, p.nombres, "
                    + " p.apellidos, p.dni, p.telefono, p.direccion, "
                    + " p.correo, p.activo, e.cargo, e.fecha_ingreso, e.salario "
                    + " FROM empleado e INNER JOIN persona p "
                    + " ON e.id_persona = p.id_persona "
                    + " ORDER BY p.apellidos, p.nombres";

        try (PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Empleado(
                    rs.getInt("id_empleado"),
                    rs.getInt("id_persona"),
                    rs.getString("nombres"),
                    rs.getString("apellidos"),
                    rs.getString("dni"),
                    rs.getString("telefono"),
                    rs.getString("direccion"),
                    rs.getString("correo"),
                    rs.getBoolean("activo"),
                    rs.getString("cargo"),
                    rs.getDate("fecha_ingreso").toLocalDate(),
                    rs.getDouble("salario")
            ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return lista;
    }
   
    private void rollback() {
        try {
            cn.rollback();
        } catch (SQLException e) {
            System.out.println("Error al hacer rollback: " + e.getMessage());
        }
    }
    
    private void restaurarAutoCommit() {
        try {
            cn.setAutoCommit(true);
        } catch (SQLException e) {
            System.out.println("Error al restaurar AutoCommit: " + e.getMessage());
        }
    }
    
}
