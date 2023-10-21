/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejemplo_xml_xstream;

/**
 *
 * @author a22eliassvf
 */
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import com.thoughtworks.xstream.security.TypePermission;

public class ObjetoAXML {
    public static void main(String[] args) {
        Persona persona = new Persona("Juan", 25);

        XStream xstream = new XStream();
        xstream.alias("persona", Persona.class); // Opcional: Puedes cambiar el nombre de la etiqueta XML

        String xml = xstream.toXML(persona);
        System.out.println(xml);
    }
}

