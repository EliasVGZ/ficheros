/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectos_ficheros;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * Crear un programa que muestre los contenidos de un fichero de texto línea a línea, numerando las líneas. (readLine de BufferedReader)
 */
public class MostrarFicheros_Linea_Linea {
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int sumarLineas=0;
        int numeroLinea=1;

        try {
            System.out.println("Ingrese la ruta del fichero");
            String ruta = br.readLine();

            if (ruta.isEmpty()) {
                ruta = ".";
            }

            // nuevo BufferedReader para leer el archivo
            BufferedReader archivoReader = new BufferedReader(new FileReader(ruta));

            String linea;
            
            while ((linea = archivoReader.readLine()) != null) {
                System.out.println(numeroLinea+" :" +linea);
                numeroLinea++;
                
            }
            sumarLineas+=numeroLinea;
            System.out.println("el numero de lineas es: "+sumarLineas+ " lineas");

            archivoReader.close(); // ¡cerrar el archivo!
            
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        
        
    }
    
}
