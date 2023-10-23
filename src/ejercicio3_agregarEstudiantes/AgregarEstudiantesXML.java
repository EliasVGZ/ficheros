/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio3_agregarEstudiantes;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author a22eliassvf
 */
public class AgregarEstudiantesXML {
    
    public static void main(String[] args) throws TransformerException, SAXException, IOException, ParserConfigurationException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        try {
            
            // Cargar el archivo XML existente
            File lecturaArchivo = new File("src\\proyectos_ficheros\\ficheros\\estudiantes.xml");

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbf.newDocumentBuilder();

            Document registroEstudiantes;
            
            if (lecturaArchivo.exists()) {
                // si el archivo existe se carga
                registroEstudiantes = builder.parse(lecturaArchivo);
                registroEstudiantes.getDocumentElement().normalize();
                
                //METODO MOSTRAR ESTUDIANTES
                mostrarEstudiantesExistente(registroEstudiantes);
            } else {
                // si el archivo no existe se crea uno nuevo
                registroEstudiantes = builder.newDocument();
                registroEstudiantes.appendChild(registroEstudiantes.createElement("estudiantes"));
            }

            String opcion;
            do{
                
                 // Agregar nuevos estudiantes
                System.out.println("ingrese id del nuevo estudiante");
                int id = Integer.parseInt(br.readLine());
                System.out.println("ingrese nombre del nuevo estudiante");
                String nombre = br.readLine();
                System.out.println("ingrese edad del nuevo estudiante");
                int edad = Integer.parseInt(br.readLine());
                agregarEstudiante(registroEstudiantes, id, nombre, edad);
                System.out.println("estudiante agregado.");
                
                System.out.println("Quieres añadir un nuevo estudiante (s/n)");
                opcion = br.readLine();
                
            }while(opcion.equalsIgnoreCase("s"));
           
           
            
            //agregarEstudiante(registroEstudiantes, 4, "marta", 40);

            // Guardar el documento modificado
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            
           
            
            DOMSource source = new DOMSource(registroEstudiantes);
            
            StreamResult result = new StreamResult(new File("src\\proyectos_ficheros\\ficheros\\estudiantes.xml"));
            transformer.transform(source, result);

            

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void agregarEstudiante(Document registroEstudiantes, int id, String nombre, int edad) {
        Element elemEstudiante = registroEstudiantes.createElement("estudiante");

        Element elemId = registroEstudiantes.createElement("id");
        elemId.appendChild(registroEstudiantes.createTextNode(Integer.toString(id)));
        elemEstudiante.appendChild(elemId);

        Element elemNombre = registroEstudiantes.createElement("nombre");
        elemNombre.appendChild(registroEstudiantes.createTextNode(nombre));
        elemEstudiante.appendChild(elemNombre);

        Element elemEdad = registroEstudiantes.createElement("edad");
        elemEdad.appendChild(registroEstudiantes.createTextNode(Integer.toString(edad)));
        elemEstudiante.appendChild(elemEdad);

        registroEstudiantes.getDocumentElement().appendChild(elemEstudiante);
    }
    
    
    //LECTURA DEL ARCHIVO Y MOSTRAR POR PANTALLA
    private static void mostrarEstudiantesExistente(Document documento) {
        NodeList listaEstudiantes = documento.getElementsByTagName("estudiante");
    
        System.out.println("Estudiantes existentes en el XML:");

            for (int i = 0; i < listaEstudiantes.getLength(); i++) {
                Node nodo = listaEstudiantes.item(i);
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element estudiante = (Element) nodo;
                    String id = estudiante.getElementsByTagName("id").item(0).getTextContent();
                    String nombre = estudiante.getElementsByTagName("nombre").item(0).getTextContent();
                    String edad = estudiante.getElementsByTagName("edad").item(0).getTextContent();
                    System.out.println("ID: " + id + " Nombre: " + nombre + " Edad: " + edad);
                }
            }
     }



    
}
