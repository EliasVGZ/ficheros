package Tarea_Evaluable_UD1_ManejoFicheros;

import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;

public class EmpleadoTXT {

    public void escrituraEmpleados(ArrayList<Empleados> listaEmpleados) {


        File ruta = new File("src\\Tarea_Evaluable_UD1_ManejoFicheros\\empleados.txt");

        try (BufferedWriter escritura = new BufferedWriter(new FileWriter(ruta, false))) {
            for (Empleados empleado : listaEmpleados) {
                if(!empleado.isBorrado()){//si no esta borrado lo muestra, sino al borrar en el array no lo borraba en el txt
                    escritura.write(empleado.getNIF() + "," + empleado.getApellidos() + ", " + empleado.getNombre() + ", " + empleado.getSalario());
                    escritura.newLine(); // moverse a la siguiente línea
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void lecturaEmpleados()    {

        File ruta = new File("src\\Tarea_Evaluable_UD1_ManejoFicheros\\empleados.txt");
        try (BufferedReader lectura = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = lectura.readLine()) != null) {
                String[] partes = linea.split(",");
                String nif = partes[0].trim();
                String apellidos = partes[1].trim();
                String nombre = partes[2].trim();
                double salario = Double.parseDouble(partes[3].trim());

                //mostrar info del txt
                System.out.println("NIF: " + nif);
                System.out.println("Apellidos: " + apellidos);
                System.out.println("Nombre: " + nombre);
                System.out.println("Salario: " + salario);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //todo añadir los empleados que estan en txt al arraylist, sino al reinicar el pro solo listaba los 5 que estan autoguardados
    public ArrayList<Empleados> lecturaEmpleadosaTXT() {
        ArrayList<Empleados> listaEmpleados = new ArrayList<>();
        File ruta = new File("src\\Tarea_Evaluable_UD1_ManejoFicheros\\empleados.txt");
        try (BufferedReader lectura = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = lectura.readLine()) != null) {
                String[] partes = linea.split(",");
                String nif = partes[0].trim();
                String apellidos = partes[1].trim();
                String nombre = partes[2].trim();
                double salario = Double.parseDouble(partes[3].trim());

                Empleados empleado = new Empleados(nif, nombre, apellidos, salario);
                listaEmpleados.add(empleado);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaEmpleados;
    }








}
