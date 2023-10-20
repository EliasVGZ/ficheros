/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio2_libros;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * 2. Crea un programa en Java que utilizando el fichero libros2.xml, analicemos el archivo XML y calcularemos el precio promedio de los libros.
 */
public class PrecioLibros {
    public static void main(String[] args) {
        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // parsear el archivo XML
            
            Document doc = (Document) builder.parse(new File("src\\proyectos_ficheros\\ficheros\\libros2.xml"));
            // obtener la lista de nodos 'libro'
            NodeList libros = doc.getElementsByTagName("libro");
            
            double sumaPrecio = 0;
            
            
            
            // iterar a través de los nodos 'libro'
            for (int i = 0; i < libros.getLength(); i++) {
                Node libro = libros.item(i);
                if (libro.getNodeType() == Node.ELEMENT_NODE) {
                    Element elementoLibro = (Element) libro;

                    // Obtener el título
                    String titulo = elementoLibro.getElementsByTagName("titulo").item(0).getTextContent();

                    // Obtener el autor
                    String autor = elementoLibro.getElementsByTagName("autor").item(0).getTextContent();

                    // Obtener el precio
                    double precio = Double.parseDouble(elementoLibro.getElementsByTagName("precio").item(0).getTextContent());

                     sumaPrecio+=precio;
                    // Mostrar la información del libro
                    System.out.println("titulo: " + titulo);
                    System.out.println("autor: " + autor);
                    System.out.println("precio: " + precio);
                    
                    System.out.println();
                }
            }
            
                    double precioPromedio = calcularPrecioPromedio(sumaPrecio, libros.getLength());
                    System.out.println(String.format("Precio promedio LIBROS %.2f", precioPromedio)); //SOLO ME APARECEN 2 DECIMALES
            
    
    
    
        }catch (Exception e) {
            e.printStackTrace();
        }
    
    }
    
    public static double calcularPrecioPromedio(double sumaPrecio, int cantidadLibros){
        
        return sumaPrecio/cantidadLibros;
    }
    
}
