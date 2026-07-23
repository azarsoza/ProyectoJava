package modelo;

public class Usuario {
    private int idUsuario;
    private Empleado empleado;
    private String usuario;
    private String clave;
    private String rol;
    private String activo;

    public Usuario() {
    }

    public Usuario(int idUsuario, Empleado empleado, String usuario,
                String clave, String rol, String activo) {

        this.idUsuario = idUsuario;
        this.empleado = empleado;
        this.usuario = usuario;
        this.clave = clave;
        this.rol = rol;
        this.activo = activo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }
    
    public Empleado getEmpleado() {
        return empleado;
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
    
    public String getActivo() {
        return activo;
    }
        
    public void setIdUsuario(int idUsuario) {
        if (idUsuario < 0) {
            throw new IllegalArgumentException("El código del usuario no puede ser negativo.");
        }
        this.idUsuario = idUsuario;
    }

    public void setEmpleado(Empleado empleado) {
        if (empleado == null) {
            throw new IllegalArgumentException("Debe seleccionar un empleado.");
        }
        this.empleado = empleado;
    }

    public void setUsuario(String usuario) {
        if (usuario == null || usuario.trim().isEmpty()) {
            throw new IllegalArgumentException("El usuario no puede estar vacío.");
        }
        this.usuario = usuario.trim();
    }

    public void setClave(String clave) {
        if (clave == null || clave.trim().isEmpty()) {
            throw new IllegalArgumentException("La clave no puede estar vacía.");
        }
        this.clave = clave.trim();
    }

    public void setRol(String rol) {
        if (rol == null || rol.trim().isEmpty()) {
            throw new IllegalArgumentException("Debe seleccionar un rol.");
        }
        this.rol = rol;
    }

    public void setActivo(String activo) {
        if (activo == null || activo.trim().isEmpty()) {
            throw new IllegalArgumentException("Debe indicar el estado.");
        }
        this.activo = activo;
    }

    @Override
    public String toString() {
        return usuario;
    }
}
