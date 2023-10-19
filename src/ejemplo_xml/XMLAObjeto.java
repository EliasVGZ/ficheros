/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejemplo_xml;

/**
 *
 * @author a22eliassvf
 */
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import com.thoughtworks.xstream.security.TypePermission;

public class XMLAObjeto {
    public static void main(String[] args) {
        String xml = "<persona>\n" +
                "  <nombre>Juan</nombre>\n" +
                "  <edad>25</edad>\n" +
                "</persona>";

        XStream xstream = new XStream();
        TypePermission permission = AnyTypePermission.ANY;

        xstream.addPermission(permission);
        xstream.alias("persona", Persona.class); // Opcional: Puedes cambiar el nombre de la etiqueta XML

        Persona persona = (Persona) xstream.fromXML(xml);
        System.out.println(persona);
    }
}
