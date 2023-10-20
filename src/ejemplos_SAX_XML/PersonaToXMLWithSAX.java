/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejemplos_SAX_XML;

/**
 *
 * @author a22eliassvf
 */
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileWriter;

public class PersonaToXMLWithSAX {

    public static void main(String[] args) {
        try {
            
            String rutaPersona= "src\\proyectos_ficheros\\ficheros\\persona.xml";
            
            Persona persona = new Persona(1, "Juan", "Perez");

            // Crear un objeto FileWriter para escribir en un archivo
            FileWriter fileWriter = new FileWriter(rutaPersona);

            // Escribir la cabecera XML
            fileWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");

            // Inicializar un objeto SAXParserFactory
            SAXParserFactory factory = SAXParserFactory.newInstance();

            // Crear un objeto SAXParser
            SAXParser saxParser = factory.newSAXParser();

            // Definir un DefaultHandler para procesar eventos SAX
            DefaultHandler handler = new DefaultHandler() {
                boolean bId = false;
                boolean bNombre = false;
                boolean bApellido = false;

                @Override
                public void startElement(String uri, String localName, String qName, org.xml.sax.Attributes attributes) {
                    if (qName.equalsIgnoreCase("id")) {
                        bId = true;
                    } else if (qName.equalsIgnoreCase("nombre")) {
                        bNombre = true;
                    } else if (qName.equalsIgnoreCase("apellido")) {
                        bApellido = true;
                    }
                }

                @Override
                public void characters(char[] ch, int start, int length) {
                    try {
                        if (bId) {
                            fileWriter.write("<id>" + new String(ch, start, length) + "</id>\n");
                            bId = false;
                        } else if (bNombre) {
                            fileWriter.write("<nombre>" + new String(ch, start, length) + "</nombre>\n");
                            bNombre = false;
                        } else if (bApellido) {
                            fileWriter.write("<apellido>" + new String(ch, start, length) + "</apellido>\n");
                            bApellido = false;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };

            // Iniciar la escritura del XML
            fileWriter.write("<Persona>\n");

            // Convertir la instancia de Persona a XML
            saxParser.parse(rutaPersona, handler);

            // Finalizar la escritura del XML
            fileWriter.write("</Persona>");

            // Cerrar el FileWriter
            fileWriter.close();

            System.out.println("Archivo XML creado correctamente.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

