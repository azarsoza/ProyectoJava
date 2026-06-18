package modelo;
import java.sql.Date;

public class Venta {
    private int idVenta;
    private Usuario usuario;
    private Date fecha;
    private double valorImpuesto;
    private double impuesto;
    private double total;
    
    public Venta() {
    }

    public Venta(int idVenta, Usuario usuario, Date fecha, double valorImpuesto, double impuesto, double total) {
        this.idVenta = idVenta;
        this.usuario = usuario;
        this.fecha = fecha;
        this.valorImpuesto = valorImpuesto;
        this.impuesto = impuesto;
        this.total = total;
    }

    public int getIdVenta() {
        return idVenta;
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

    public void setIdVenta(int idVenta) {
        if (idVenta <= 0) {
            throw new IllegalArgumentException("El ID de venta debe ser mayor que cero");
        }
        this.idVenta = idVenta;
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
    
    public void calcularImportes(double subtotal) {
        this.impuesto = subtotal * valorImpuesto;
        this.total = subtotal + impuesto;
    }
}
