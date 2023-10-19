/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio_libro;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Element;
import org.w3c.dom.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import javax.xml.parsers.*;
import java.io.*;



/**
 *
 * @author a22eliassvf
 */
public class LeerLibroDOM {
    public static void main(String[] args) {
        
        
        try {
            // crear una instancia de DocumentBuilder
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // parsear el archivo XML
            
            Document doc = (Document) builder.parse(new File("libros.xml"));

            // obtener la lista de nodos 'libro'
            NodeList libros = doc.getElementsByTagName("libro");

            // iterar a través de los nodos 'libro'
            for (int i = 0; i < libros.getLength(); i++) {
                Node libro = libros.item(i);
                if (libro.getNodeType() == Node.ELEMENT_NODE) {
                    Element elementoLibro = (Element) libro;

                    // Obtener el título
                    String titulo = elementoLibro.getElementsByTagName("titulo").item(0).getTextContent();

                    // Obtener el autor
                    String autor = elementoLibro.getElementsByTagName("autor").item(0).getTextContent();

                    // Obtener el año
                    int anio = Integer.parseInt(elementoLibro.getElementsByTagName("anio").item(0).getTextContent());

                    // Mostrar la información del libro
                    System.out.println("titulo: " + titulo);
                    System.out.println("autor: " + autor);
                    System.out.println("anioo: " + anio);
                    System.out.println();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }
    
}
