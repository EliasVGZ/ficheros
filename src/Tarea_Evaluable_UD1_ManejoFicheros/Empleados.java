package Tarea_Evaluable_UD1_ManejoFicheros;

import java.io.Serializable;

import static Validaciones.validaciones.*;

public class Empleados implements Serializable {

    private String NIF;
    private String nombre;
    private String apellidos;
    private double salario;

    @Override
    public String toString() {
        return "Empleados{" +
                "NIF='" + NIF + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", salario=" + salario +
                '}';
    }

    public Empleados() {
    }

    public Empleados(String NIF, String nombre, String apellidos, double salario) throws Exception {
        if (!validaDNI_Exp(NIF)) {
            throw new Exception("DNI inválido.");
        }
        if (!validarNombre(nombre)) {
            throw new Exception("Nombre no valido.");
        }
        if (!validarApellido(apellidos)){
            throw new Exception("Apellido no valido.");
        }
        this.NIF = NIF;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.salario = salario;
    }

    public String getNIF() {
        return NIF;
    }

    public void setNIF(String NIF) {
        this.NIF = NIF;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
}
