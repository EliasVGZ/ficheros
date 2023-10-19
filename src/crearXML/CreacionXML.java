/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crearXML;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/**
 *
 * @author a22eliassvf
 */
public class CreacionXML {
    public static void main(String[] args) throws ParserConfigurationException, TransformerConfigurationException, TransformerException {
        
        
        
        
        //CREAMOS UN DOCUMENT BUILDER
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder=dbf.newDocumentBuilder();
        
        Document registroEmpleados = builder.newDocument();
        
        //DOMImplementation implementacion=builder.getDOMImplementation();
        //Document registroEmpleados = implementacion.createDocument(null, "empleados", null);
        registroEmpleados.setXmlVersion("1.0");
        
        Element raiz = registroEmpleados.createElement("empleados");
        registroEmpleados.appendChild(raiz);
        
        //creamos un nodo empleado
        Element empleado = registroEmpleados.createElement("persona");
        //añadimos con hijo de empleados
        registroEmpleados.getDocumentElement().appendChild(empleado);
        //creamos e nodo ID
        Element id= registroEmpleados.createElement("id");
        //creamos nodo texto con el valo rde la id
        Text texto = registroEmpleados.createTextNode("01");
        //añadimos el valor al nodo id
        id.appendChild(texto);
        //añadimos el nodo ID a empleado
        empleado.appendChild(id);
        
        Element nombre = registroEmpleados.createElement("nombre");
        texto=registroEmpleados.createTextNode("Antonio");
        nombre.appendChild(texto);
        empleado.appendChild(nombre);
        
        Element apellidos=registroEmpleados.createElement("apellido");
        texto=registroEmpleados.createTextNode("Morales");
        apellidos.appendChild(texto);
        empleado.appendChild(apellidos);
        
        Element direccion=registroEmpleados.createElement("direccion");
        texto=registroEmpleados.createTextNode("Camelias 100");
        direccion.appendChild(texto);
        empleado.appendChild(direccion);
        
        //OTRO EMPLEADO----LAS ETIQUETAS YA ESTAN CREADAS, HAY QUE DARLE OTRA INFORMACION!!!!!!!!!!!!!!
        empleado = registroEmpleados.createElement("persona");
        registroEmpleados.getDocumentElement().appendChild(empleado);

        id = registroEmpleados.createElement("id");
        texto = registroEmpleados.createTextNode("02");
        id.appendChild(texto);
        empleado.appendChild(id);

        nombre = registroEmpleados.createElement("nombre");
        texto = registroEmpleados.createTextNode("Juan");
        nombre.appendChild(texto);
        empleado.appendChild(nombre);

        apellidos = registroEmpleados.createElement("apellido");
        texto = registroEmpleados.createTextNode("García");
        apellidos.appendChild(texto);
        empleado.appendChild(apellidos);

        direccion = registroEmpleados.createElement("direccion");
        texto = registroEmpleados.createTextNode("Camelias 101");
        direccion.appendChild(texto);
        empleado.appendChild(direccion);
        
        
        
     
        //CREA DOCUMENTO XML 
        
        String rutaArchivo = "src\\proyectos_ficheros\\ficheros\\registroEmpleados.xml";
        
        // Transformar el DOM en un archivo XML
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        // Agregar indentación para hacer el XML legible

        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        // Definir la fuente (DOMSource) y el resultado (StreamResult)
        DOMSource source = new DOMSource(registroEmpleados);
        StreamResult result = new StreamResult(new File(rutaArchivo));

        // Transformar y guardar el archivo XML
        transformer.transform(source, result);

        System.out.println("Archivo XML creado exitosamente.");
        
    }
    
}
