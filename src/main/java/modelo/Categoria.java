package modelo;

public class Categoria {
    private int idCategoria;
    private String nombre;
 
    public Categoria() {
    }
    
    public Categoria(int idCategoria, String nombre) {
        this.idCategoria = idCategoria;
        this.nombre = nombre;
    }
    
    public int getIdCategoria() {
        return idCategoria;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setIdCategoria(int idCategoria) {
        if (idCategoria <= 0) {
            throw new IllegalArgumentException("Se debe seleccionar una categoria");
        }
        this.idCategoria = idCategoria;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la categoria no puede estar vacio");
        }
        this.nombre = nombre;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        Categoria otra = (Categoria) obj;

        return idCategoria == otra.idCategoria;
    }
    
    @Override
    public String toString() {
        return nombre;
    }
    
}
