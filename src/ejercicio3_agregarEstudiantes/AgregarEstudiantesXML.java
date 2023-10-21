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
        try {
            
            // Cargar el archivo XML existente
            File lecturaArchivo = new File("src\\proyectos_ficheros\\ficheros\\estudiantes.xml");

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbf.newDocumentBuilder();

            Document registroEstudiantes;
            
            if (lecturaArchivo.exists()) {
                // Si el archivo ya existe, cargarlo
                registroEstudiantes = builder.parse(lecturaArchivo);
                registroEstudiantes.getDocumentElement().normalize();
            } else {
                // Si el archivo no existe, crear un nuevo documento
                registroEstudiantes = builder.newDocument();
                registroEstudiantes.appendChild(registroEstudiantes.createElement("estudiantes"));
            }

            // Agregar nuevos estudiantes
            agregarEstudiante(registroEstudiantes, 3, "tania", 31);
            agregarEstudiante(registroEstudiantes, 4, "marta", 40);

            // Guardar el documento modificado
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            
            DOMSource source = new DOMSource(registroEstudiantes);
            
            StreamResult result = new StreamResult(new File("src\\proyectos_ficheros\\ficheros\\estudiantes.xml"));
            transformer.transform(source, result);

            System.out.println("estudiante agregado.");

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


    
}
