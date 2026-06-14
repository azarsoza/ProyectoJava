package modelo;

public class Producto {   
    private int idProducto;
    private String nombre;
    private double precioVenta;
    private int stock;
    
        public Producto() {
    }
        
    public Producto(int idProducto, String nombre, double precioVenta, int stock) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.precioVenta = precioVenta;
        this.stock = stock;
    }
    
        public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        if (idProducto <= 0){
            throw new IllegalArgumentException("El Id debe ser mayor que 0.");
        }
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()){
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        this.nombre = nombre;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        if (precioVenta <= 0){
            throw new IllegalArgumentException("El precio debe ser mayor a 0.");
        }
        this.precioVenta = precioVenta;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        if(stock < 0){
            throw new IllegalArgumentException("El stock no puede ser negativo");
        }
        this.stock = stock;
    }
}
