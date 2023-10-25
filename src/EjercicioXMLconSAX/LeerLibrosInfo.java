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
        if (qName.equals("libro")) {
            String anio = attributes.getValue("año");
            System.out.println("Año: " + anio);
        }
        
    }
    public void characters(char[] ch, int start, int length) throws SAXException{
        //super.characters(ch, start, length);
        //String car = new String(ch,start,length);
        //car.replace("[\t\n", "");
        //System.out.printf("\t Valor del elemento %s %n", car);
        currentText.append(ch, start, length);
        
    }
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case "libro":
                System.out.println("");
                break;
            case "titulo":
                System.out.println("Titulo: " + this.currentText.toString());
                break;
            case "apellido":
                System.out.println("Apellido autor: " + this.currentText.toString());
                break;
            case "nombre":
                System.out.println("Nombre autor: " + this.currentText.toString());
                break;
            case "editorial":
                System.out.println("Editorial: " + this.currentText.toString());
                break;
            case "precio":
                System.out.println("Precio: " + this.currentText.toString());
                break;
        }
    }
    
}
