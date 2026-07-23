package modelo;
import java.time.LocalDate;

public class Empleado extends Persona{
   
    private int idEmpleado;
    private String cargo;
    private LocalDate fechaIngreso;
    private double salario;
    
    public Empleado() {
    }
     
    public Empleado(int idEmpleado, int idPersona, String nombres, String apellidos,
                    String dni, String telefono, String direccion, String correo,
                    boolean activo, String cargo, LocalDate fechaIngreso, double salario) {
        super(idPersona, nombres, apellidos, dni, telefono, direccion,correo, activo);
        this.idEmpleado = idEmpleado;
        this.cargo = cargo;
        this.fechaIngreso = fechaIngreso;
        this.salario = salario;
    }
    
    public int getIdEmpleado() {
        return idEmpleado;
    }

    public String getCargo() {
        return cargo;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public double getSalario() {
        return salario;
    }
        
    public void setIdEmpleado(int idEmpleado) {
        if (idEmpleado < 0) {
            throw new IllegalArgumentException("El ID del empleado no puede ser negativo.");
        }
        this.idEmpleado = idEmpleado;
    }

    public void setCargo(String cargo) {
        if (cargo == null || cargo.trim().isEmpty()) {
            throw new IllegalArgumentException("El cargo es obligatorio.");
        }
        this.cargo = cargo;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        if (fechaIngreso == null) {
            throw new IllegalArgumentException("La fecha de ingreso es obligatoria.");
        }
        this.fechaIngreso = fechaIngreso;
    }

    public void setSalario(double salario) {
        if (salario <= 0) {
            throw new IllegalArgumentException("El salario debe ser mayor que cero.");
        }
        this.salario = salario;
    }

    @Override
    public String toString() {
        return getNombres() + " " + getApellidos();
    }
}
