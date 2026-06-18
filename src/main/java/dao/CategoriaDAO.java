package dao;

import conexion.Conexion;
import modelo.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CategoriaDAO {
    
    public boolean insertar(Categoria categoria){
        
        String sql ="INSER INTO categoria (nombre)"
                + "VALUES(?)";
        try {
            Connection cn = Conexion.getConexion();
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, categoria.getNombre());
            
            ps.executeUpdate();
            ps.close();
            cn.close();
            
            return true;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
    
    public boolean actualizar(Categoria categoria){
        
        String sql ="UPDATE categoria"
                + "SET nombre=?"
                + "WHERE id_categoria=?";
        try {
            Connection cn = Conexion.getConexion();
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, categoria.getNombre());
            ps.setInt(2, categoria.getIdCategoria());
            
            ps.executeUpdate();
            ps.close();
            cn.close();
            
            return true;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
    
    public boolean elimnar(int idCategoria){
        
        String sql = "DELETE FROM categoria"
                + "WHERE id_categoria=?";
        
        try {
            Connection cn = Conexion.getConexion();
            PreparedStatement ps = cn.prepareStatement(sql);
            
            ps.setInt(1, idCategoria);
            ps.executeUpdate();
            
            ps.close();
            cn.close();
            return true;
            
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
    
    public Categoria buscar(int idCategoria){
        
        String sql = "SELECT * FROM categoria"
                + "WHERE id_categoria=?";
        
        try {
            Connection cn = Conexion.getConexion();
            PreparedStatement ps = cn.prepareStatement(sql);
            
            ps.setInt(1, idCategoria);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                Categoria categoria = new Categoria();
                
                categoria.setIdCategoria(rs.getInt("id_categoria"));
                categoria.setNombre(rs.getString("nombre"));
                
                rs.close();
                ps.close();
                cn.close();
                
                return categoria;
            }
            
            rs.close();
            ps.close();
            cn.close();            
            
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
    
    public List<Categoria> listar(){
        List<Categoria> lista = new ArrayList<>();
        
        String sql = "SELECT * FROM categoria "
                + "ORDER BY nombre";
        
        try {
            Connection cn = Conexion.getConexion();
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Categoria categoria = new Categoria();
                
                categoria.setIdCategoria(rs.getInt("id_categoria"));
                categoria.setNombre(rs.getString("nombre"));
                lista.add(categoria);
            }
            
            rs.close();
            ps.close();
            cn.close();
            
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return lista;
    }
}
