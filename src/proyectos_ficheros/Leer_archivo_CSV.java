/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectos_ficheros;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 *
 * @author a22eliassvf
 */
public class Leer_archivo_CSV {
    
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String archivoCSV = "src\\proyectos_ficheros\\ficheros\\estudiantes.csv";
        String archivoSalida = "src\\proyectos_ficheros\\ficheros\\salida_estudiantes";
        String linea;
        
        try{
            BufferedReader archivoReader = new BufferedReader(new FileReader(archivoCSV));//LECTURA DE ARCHIVO
            BufferedWriter bw = new BufferedWriter(new FileWriter(archivoSalida));//ESCRITURA DE ARCHIVO
            
            
            while ((linea = archivoReader.readLine()) != null) {
                String[] datos = linea.split(",");
                String nombre = datos[0];
                int edad = Integer.parseInt(datos[1]);
                int nota1 = Integer.parseInt(datos[2]);
                int nota2 = Integer.parseInt(datos[3]);
                int nota3 = Integer.parseInt(datos[4]);
                System.out.println("Linea leida: " + linea); //VERIFICO LECTURA DE CADA LINEA PORQUE NO ME FUNCIONABA
                System.out.println(Arrays.toString(datos)); // LECTURA POR MEDIO DE ARRAYS
                System.out.println("Longitud de datos: " + datos.length); //LONGITUD PARA VERIFICAR QUE REALMENTE HAY 5
                
                double promedio = (double)(nota1+nota2+nota3)/3;
                
                //SALIDA POR PANTALLA
                /*
                System.out.println("Nombre: " + nombre);
                System.out.println("Edad: " + edad);
                System.out.println("Nota 1: " + nota1);
                System.out.println("Nota 2: " + nota2);
                System.out.println("Nota 3: " + nota3);
                System.out.println("Promedio: " + promedio);
                System.out.println();*/
                
                //SALIDA POR ARCHIVO NUEVO
                
                bw.write("nombre: " + nombre + "\n");
                bw.write("edad: " + edad + "\n");
                bw.write("nota 1: " + nota1 + "\n");
                bw.write("nota 2: " + nota2 + "\n");
                bw.write("nota 3: " + nota3 + "\n");
                bw.write("promedio: " + promedio + "\n");
            }
            
            bw.close();
        }catch(IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        
    }
    
}
