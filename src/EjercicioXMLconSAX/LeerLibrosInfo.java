/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EjercicioXMLconSAX;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author a22eliassvf
 */
public class LeerLibrosInfo extends DefaultHandler{
    
    private String currentElement;
    private StringBuilder currentText;
    
    public LeerLibrosInfo(){
        super();
    }
    
    public void startDocument() throws SAXException{
        super.startDocument();
        System.out.println("Inicio de documento");
    }    
    
    public void endDocument() throws SAXException{
        super.endDocument();
        System.out.println("Fin del documento");
    }
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException{
        //super.startElement(uri, localName, qName, attributes);
        currentElement = qName;
        //System.out.printf("\t Inicio del elemento %s %n", localName);
        currentText = new StringBuilder();
        
    }
    public void characters(char[] ch, int start, int length) throws SAXException{
        //super.characters(ch, start, length);
        //String car = new String(ch,start,length);
        //car.replace("[\t\n", "");
        //System.out.printf("\t Valor del elemento %s %n", car);
        currentText.append(ch, start, length);
        
    }
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("libro")) {
            System.out.println("Año: " + currentText.toString().trim());
        } else if (qName.equals("titulo") || qName.equals("apellido") || qName.equals("nombre") || qName.equals("editorial") || qName.equals("precio")) {
            System.out.println(qName.substring(0, 1).toUpperCase() + qName.substring(1) + ": " + currentText.toString().trim());
        }
    }
    
}
