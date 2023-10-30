package Tarea_Evaluable_UD1_ManejoFicheros;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class EmpleadosSAX extends DefaultHandler {
    private StringBuilder currentText;

    public EmpleadosSAX(){
        super();
    }

    public void startDocument() throws SAXException {
        super.startDocument();
        System.out.println("Inicio de documento");
    }

    public void endDocument() throws SAXException{
        super.endDocument();
        System.out.println("Fin del documento");
    }
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException{

        currentText = new StringBuilder();

    }
    public void characters(char[] ch, int start, int length) throws SAXException{

        currentText.append(ch, start, length);

    }
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case "empleado":
                System.out.println("");
                break;
            case "nif":
                System.out.println("Nif: " + this.currentText.toString());
                break;
            case "nombre":
                System.out.println("Nombre: " + this.currentText.toString());
                break;
            case "apellido":
                System.out.println("Apellido: " + this.currentText.toString());
                break;
            case "salario":
                System.out.println("Salario: " + this.currentText.toString());
                break;
        }
    }

}
