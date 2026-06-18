package modelo;

public class DetalleCompra {
    private int idDetalleCompra;
    private Compra compra;
    private Producto producto;
    private double cantidad;
    private double precioCompra;
    private double subtotal;
    
    public DetalleCompra() {
    }

    public DetalleCompra(int idDetalleCompra, Compra compra, Producto producto, double cantidad, double precioCompra, double subtotal) {
        this.idDetalleCompra = idDetalleCompra;
        this.compra = compra;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioCompra = precioCompra;
        this.subtotal = subtotal;
    }

    public int getIdDetalleCompra() {
        return idDetalleCompra;
    }

    public Compra getCompra() {
        return compra;
    }

    public Producto getProducto() {
        return producto;
    }

    public double getCantidad() {
        return cantidad;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public double getSubtotal() {
         return cantidad * precioCompra;
    }

    public void setIdDetalleCompra(int idDetalleCompra) {
        if (idDetalleCompra <= 0) {
            throw new IllegalArgumentException("El ID debe ser mayor que cero");
        }
        this.idDetalleCompra = idDetalleCompra;
    }

    public void setCompra(Compra compra) {
         if (compra == null) {
             throw new IllegalArgumentException("La compra es obligatoria");
        }
         this.compra = compra;
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

    public void setPrecioCompra(double precioCompra) {
        if (precioCompra <= 0) {
            throw new IllegalArgumentException("El precio de compra debe ser mayor que cero");
        }
        this.precioCompra = precioCompra;
    }
}
