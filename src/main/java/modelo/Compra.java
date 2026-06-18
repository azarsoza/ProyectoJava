package modelo;
import java.sql.Date;

public class Compra {
    private int idCompra;
    private Usuario usuario;
    private Date fecha;
    private double valorImpuesto;
    private double impuesto;
    private double total;
    
    public Compra() {
    }

    public Compra(int idCompra, Usuario usuario, Date fecha, double valorImpuesto, double impuesto, double total) {
        this.idCompra = idCompra;
        this.usuario = usuario;
        this.fecha = fecha;
        this.valorImpuesto = valorImpuesto;
        this.impuesto = impuesto;
        this.total = total;
    }

    public int getIdCompra() {
        return idCompra;
    }
    
    public Usuario getUsuario() {
       return usuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public double getValorImpuesto() {
        return valorImpuesto;
    }

    public double getImpuesto() {
        return impuesto;
    }

    public double getTotal() {
        return total;
    }

    public void setIdCompra(int idCompra) {
        if (idCompra <= 0) {
            throw new IllegalArgumentException("El ID de compra debe ser mayor que cero");
        }
        this.idCompra = idCompra;
    }

    public void setUsuario(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("El usuario es obligatorio");
        }
        this.usuario = usuario;
    }
    
    public void setFecha(Date fecha) {
        if (fecha == null) {
            throw new IllegalArgumentException("La fecha es obligatoria");
        }
        this.fecha = fecha;
    }
    
    public void calcularImportes(double subtotal){
        impuesto = subtotal * valorImpuesto;
        total = subtotal + impuesto;
    }
}
