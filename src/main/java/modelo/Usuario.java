package modelo;

public class Usuario {
    private int idUsuario;
    private String nombre;
    private String usuario;
    private String clave;
    private String rol;
    private boolean activo;

    public Usuario() {
    }   

    public Usuario(int idUsuario, String nombre, String usuario, String clave, String rol, boolean activo) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.usuario = usuario;
        this.clave = clave;
        this.rol = rol;
        this.activo = activo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getClave() {
        return clave;
    }

    public String getRol() {
        return rol;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setIdUsuario(int idUsuario) {
        if(idUsuario <= 0){
            throw new IllegalArgumentException("El Id debe ser mayor que cero");
        }
        this.idUsuario = idUsuario;
    }
    
    public void setNombre(String nombre) {
        if(nombre == null || nombre.trim().isEmpty()){
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        if(nombre.length() > 100){
            throw new IllegalArgumentException("El nombre no puede exceder 100 caracteres");
        }
        this.nombre = nombre;
    }

    public void setUsuario(String usuario) {
        if(usuario == null || usuario.trim().isEmpty()){
            throw new IllegalArgumentException("El usuario es obligatorio");
        }
        if(usuario.length() < 4){
            throw new IllegalArgumentException("El usuario debe tener al menos 4 caracteres");
        }
        this.usuario = usuario;
    }

    public void setClave(String clave) {
        if(clave == null || clave.trim().isEmpty()){
        throw new IllegalArgumentException("La clave es obligatoria");
        }
        if(clave.length() < 6){
            throw new IllegalArgumentException("La clave debe tener al menos 6 caracteres");
        }
        this.clave = clave;
    }

    public void setRol(String rol) {
        if(rol == null || rol.trim().isEmpty()){
        throw new IllegalArgumentException("El rol es obligatorio");
        }
        if(!rol.equals("Administrador") && !rol.equals("Vendedor")){
            throw new IllegalArgumentException("Rol no válido");
        }
        this.rol = rol;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

}
