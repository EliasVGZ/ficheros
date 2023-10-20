/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejemplos_SAX_XML;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class ReadXMLWithSAX {

    public static void main(String[] args) {
        try {
            // Inicializar un objeto SAXParserFactory
            SAXParserFactory factory = SAXParserFactory.newInstance();

            // Crear un objeto SAXParser
            SAXParser saxParser = factory.newSAXParser();

            // Definir un DefaultHandler para procesar eventos SAX
            DefaultHandler handler = new DefaultHandler() {
                boolean bNombre = false;
                boolean bEdad = false;

                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    if (qName.equalsIgnoreCase("Nombre")) {
                        bNombre = true;
                    } else if (qName.equalsIgnoreCase("Edad")) {
                        bEdad = true;
                    }
                }

                @Override
                public void characters(char[] ch, int start, int length) throws SAXException {
                    if (bNombre) {
                        System.out.println("Nombre: " + new String(ch, start, length));
                        bNombre = false;
                    } else if (bEdad) {
                        System.out.println("Edad: " + new String(ch, start, length));
                        bEdad = false;
                    }
                }
            };

            // Parsear el archivo XML
            saxParser.parse("nuevo.xml", handler);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

