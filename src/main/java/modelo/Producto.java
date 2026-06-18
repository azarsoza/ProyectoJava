package modelo;

public class Producto {
    private int idProducto;
    private int idCategoria;
    private String nombre;
    private double precioVenta;
    private int stock;
    private int activo;

    public Producto() {}

    public Producto(int idProducto, int idCategoria, String nombre, double precioVenta, int stock, int activo) {
        this.idProducto = idProducto;
        this.idCategoria = idCategoria;
        this.nombre = nombre;
        this.precioVenta = precioVenta;
        this.stock = stock;
        this.activo = activo;
    }

    public int getIdProducto() { return idProducto; }
    public void setIdProducto(int idProducto) {
        if (idProducto <= 0)
            throw new IllegalArgumentException("El Id debe ser mayor que 0.");
        this.idProducto = idProducto;
    }

    public int getIdCategoria() { return idCategoria; }
    public void setIdCategoria(int idCategoria) {
        if (idCategoria <= 0)
            throw new IllegalArgumentException("La categoría es obligatoria.");
        this.idCategoria = idCategoria;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty())
            throw new IllegalArgumentException("El nombre es obligatorio.");
        this.nombre = nombre;
    }

    public double getPrecioVenta() { return precioVenta; }
    public void setPrecioVenta(double precioVenta) {
        if (precioVenta <= 0)
            throw new IllegalArgumentException("El precio debe ser mayor a 0.");
        this.precioVenta = precioVenta;
    }

    public int getStock() { return stock; }
    public void setStock(int stock) {
        if (stock < 0)
            throw new IllegalArgumentException("El stock no puede ser negativo.");
        this.stock = stock;
    }

    public int getActivo() { return activo; }
    public void setActivo(int activo) { this.activo = activo; }
}
