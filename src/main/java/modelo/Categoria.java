package modelo;

public record Categoria(int idCategoria,String nombre){
    public Categoria{
        if (idCategoria < 0){
            throw new IllegalArgumentException("Se debe seleccionar una categoria.");
        }
        
        if (nombre == null || nombre.trim().isEmpty()){
             throw new IllegalArgumentException("El nombre de la categoría no puede estar vacío.");
        }
    }   

    @Override
    public String toString() {
        return nombre;
    }    
}
