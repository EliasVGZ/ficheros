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
import java.io.File;
import java.io.FileWriter;

public class CreateXMLWithSAX {
    public static void main(String[] args) {
        try {
            // Crear un objeto FileWriter para escribir en un archivo
            FileWriter fileWriter = new FileWriter("nuevo.xml");

            // Inicializar un objeto SAXParserFactory
            SAXParserFactory factory = SAXParserFactory.newInstance();

            // Crear un objeto SAXParser
            SAXParser saxParser = factory.newSAXParser();

            // Definir un DefaultHandler para procesar eventos SAX
            DefaultHandler handler = new DefaultHandler() {
                boolean elementoRaiz = true;

                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes)
                        throws SAXException {
                    try {
                        if (elementoRaiz) {
                            fileWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
                            elementoRaiz = false;
                        }

                        fileWriter.write("<" + qName);

                        // Escribir atributos si los hay
                        for (int i = 0; i < attributes.getLength(); i++) {
                            fileWriter.write(" " + attributes.getQName(i) + "=\"" + attributes.getValue(i) + "\"");
                        }

                        fileWriter.write(">");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    try {
                        fileWriter.write("</" + qName + ">\n");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void characters(char ch[], int start, int length) throws SAXException {
                    try {
                        fileWriter.write(new String(ch, start, length));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };

            // Parsear y procesar el contenido
            saxParser.parse(new File("nuevo.xml"), handler);

            // Cerrar el FileWriter
            fileWriter.close();

            System.out.println("Archivo XML creado correctamente.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

