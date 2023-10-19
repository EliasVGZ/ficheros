
package proyectos_ficheros;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

/**
 *Crear un programa que muestre por defecto un listado de los ficheros y directorios que contiene el directorio desde el que se ejecuta el programa. 
 * Si se le pasa la ruta de un directorio o fichero, muestra la información acerca de él y, si se trata de un directorio,
 * muestra los ficheros y directorios que contiene.
 * 
 */
public class Listar_Directorio {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        try{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        System.out.println("Escribe una ruta");
        String ruta = br.readLine();
        
        if(ruta.isEmpty()){
            ruta = ".";
        }
        
        File archivo = new File(ruta);
        
            if(archivo.isDirectory()){
                System.out.println("info de: "+archivo.getAbsolutePath());
                /*String[] elementos = archivo.list();
                for (String elemento:elementos){
                    System.out.println("- "+elemento);
                }*/
               // Tambien se puede hacer
                File [] ficheros = archivo.listFiles();
                for(File fichero : ficheros){
                    System.out.println("- "+fichero);
                        }
            }else if (archivo.isFile()){
                System.out.println("info acerca de: "+archivo.getAbsolutePath());
                System.out.println("nombre del archivo: "+archivo.getName());
                System.out.println("tamaño: "+archivo.length()+" bytes");
                System.out.println("ruta: "+archivo.getPath());
                
            }else{
                System.out.println("ruta no existe");
            }
        }catch(IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    
    } 
        
        
    
}
