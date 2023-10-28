package Tarea_Evaluable_UD1_ManejoFicheros;

import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;

public class EmpleadoTXT {

    public void escrituraEmpleados(ArrayList<Empleados> listaEmpleados) {


        File ruta = new File("src\\Tarea_Evaluable_UD1_ManejoFicheros\\empleados.txt");

        try (BufferedWriter escritura = new BufferedWriter(new FileWriter(ruta))) {
            for (Empleados empleado : listaEmpleados) {
                escritura.write(empleado.getNIF() + "," + empleado.getApellidos() + ", " + empleado.getNombre() + ", " + empleado.getSalario());
                escritura.newLine(); // Para moverse a la siguiente línea
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
