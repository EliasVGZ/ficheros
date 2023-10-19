/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejemplo_dom;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
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
public class Main {
    public static void main(String[] args) throws TransformerException {
        // Crear un objeto Perro
        Perro miPerro = new Perro("Fido", 5);

        // Convertir el objeto a un elemento XML
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = (Document) builder.newDocument();

            Element perroElement = miPerro.toXmlElement(doc);
            doc.appendChild(perroElement);

            // Escribir el documento XML en un archivo
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource((Node) doc);
            StreamResult result = new StreamResult(new File("perro.xml"));
            transformer.transform(source, result);

            System.out.println("Perro guardado correctamente como XML.");
        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }

        // Leer el objeto desde el archivo XML
        try {
            File xmlFile = new File("perro.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = (Document) dBuilder.parse(xmlFile);

            Element rootElement = doc.getDocumentElement();
            Perro perroCargado = Perro.fromXmlElement((javax.lang.model.element.Element) rootElement);

            System.out.println("Perro cargado desde el archivo XML: " + perroCargado);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
