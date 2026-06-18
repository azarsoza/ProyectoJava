package modelo;

public class Producto {   
    private int idProducto;
    private Categoria categoria;
    private String nombre;
    private double precioVenta;
    private boolean activo;
    
    public Producto() {
    }
        
    public Producto(int idProducto, Categoria categoria, String nombre, double precioVenta, boolean activo) {
        this.idProducto = idProducto;
        this.categoria = categoria;
        this.nombre = nombre;
        this.precioVenta = precioVenta;
        this.activo = activo;
    }
    
    public int getIdProducto() {
        return idProducto;
    }

    public Categoria getCategoria() {
        return categoria;
    }     
    
    public String getNombre() {
        return nombre;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }                

    public boolean getActivo() {
        return activo;
    }              
        
    public void setIdProducto(int idProducto) {
        if (idProducto <= 0){
            throw new IllegalArgumentException("El Id debe ser mayor que 0.");
        }
        this.idProducto = idProducto;
    }

    public void setCategoria(Categoria categoria) {
        if (categoria == null){
            throw new IllegalArgumentException("La categoria no puede estar vacia");
        }
        this.categoria = categoria;
    }  

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()){
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        this.nombre = nombre;
    }

    public void setPrecioVenta(double precioVenta) {
        if (precioVenta <= 0){
            throw new IllegalArgumentException("El precio debe ser mayor a 0.");
        }
        this.precioVenta = precioVenta;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
