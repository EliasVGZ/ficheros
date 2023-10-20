/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio3_agregarEstudiantes;

import java.io.File;
import java.io.IOException;
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
       
           
        try{
            // Paso 1: Crear el Documento XML
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder=dbf.newDocumentBuilder();
            
            Document registroEstudiantes = builder.newDocument(); //Sustitui estÃ¡ linea por las dos siguientes en comentarios
            
            registroEstudiantes.setXmlVersion("1.0");
            
            // Paso 2: Crear elementos y agregarlos al documento
            Element elemRaiz = registroEstudiantes.createElement("estudiante");
            registroEstudiantes.appendChild(elemRaiz);
            
            //añadir estudiante AL ARCHIVO XML
            agregarEstudiante(registroEstudiantes, 3, "pepe", 32);
            agregarEstudiante(registroEstudiantes, 4, "maria", 31);

            
            
            
            // Paso 3: Escribir el contenido del documento XML a un archivo
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            
            //XML INDENTADO
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            
            DOMSource source = new DOMSource(registroEstudiantes);
            
            //Especifica la ubicaciÃ³n del archivo de salida
            StreamResult result = new StreamResult("src\\proyectos_ficheros\\ficheros\\estudiantes.xml");

            // Realiza la transformaciÃ³n y escribe en el archivo
            transformer.transform(source, result);

            System.out.println("estudiantes agregados .");
           
        
        }catch (Exception e){
            e.printStackTrace();
        }
        
        
        
    }
    
    private static void agregarEstudiante(Document registroEstudiantes, int id, String nombre, int edad) {
        Element elemEstudiante = registroEstudiantes.createElement("estudiante");
        registroEstudiantes.getDocumentElement().appendChild(elemEstudiante);

        Element elemId = registroEstudiantes.createElement("id");
        elemId.appendChild(registroEstudiantes.createTextNode(Integer.toString(id)));
        elemEstudiante.appendChild(elemId);

        Element elemNombre = registroEstudiantes.createElement("nombre");
        elemNombre.appendChild(registroEstudiantes.createTextNode(nombre));
        elemEstudiante.appendChild(elemNombre);

        Element elemEdad = registroEstudiantes.createElement("edad");
        elemEdad.appendChild(registroEstudiantes.createTextNode(Integer.toString(edad)));
        elemEstudiante.appendChild(elemEdad);

        
        
    }

    
}
