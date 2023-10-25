/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EjercicioXMLconSAX;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

/**
 *
 * @author a22eliassvf
 */
public class Principal {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {

        try{
            File archivo = new File("src\\proyectos_ficheros\\ficheros\\librosinfo.xml");

            if (!archivo.exists()) {
                // Si el archivo no existe, crea uno nuevo
                archivo.createNewFile();
            }

            SAXParserFactory factory = SAXParserFactory.newInstance();

            SAXParser parser = factory.newSAXParser();
            LeerLibrosInfo leerLibro = new LeerLibrosInfo();
            parser.parse(archivo, leerLibro);

        }catch (ParserConfigurationException | SAXException | IOException ex){
            System.out.println(ex.getMessage());
        }

        
      
    }
    
}
