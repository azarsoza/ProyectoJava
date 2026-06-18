package modelo;

public class DetalleVenta {
    private int idDetalleVenta;
    private Venta venta;
    private Producto producto;
    private double cantidad;
    private double precioVenta;
    
    public DetalleVenta() {
    }

    public DetalleVenta(int idDetalleVenta, Venta venta, Producto producto, double cantidad, double precioVenta) {
        this.idDetalleVenta = idDetalleVenta;
        this.venta = venta;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioVenta = precioVenta;
    }

    public int getIdDetalleVenta() {
        return idDetalleVenta;
    }

    public Venta getVenta() {
        return venta;
    }

    public Producto getProducto() {
        return producto;
    }

    public double getCantidad() {
        return cantidad;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public double getSubtotal() {
        return cantidad * precioVenta;
    }

    public void setIdDetalleVenta(int idDetalleVenta) {
        if (idDetalleVenta <= 0) {
            throw new IllegalArgumentException("El ID debe ser mayor que cero");
        }
        this.idDetalleVenta = idDetalleVenta;
    }

    public void setVenta(Venta venta) {
        if (venta == null) {
            throw new IllegalArgumentException("La venta es obligatoria");
        }
        this.venta = venta;
    }

    public void setProducto(Producto producto) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto es obligatorio");
        }
        this.producto = producto;
    }

    public void setCantidad(double cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que cero");
        }
        this.cantidad = cantidad;
    }

    public void setPrecioVenta(double precioVenta) {
        if (precioVenta <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor que cero");
        }
        this.precioVenta = precioVenta;
    }
}
