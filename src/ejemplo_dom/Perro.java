/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejemplo_dom;

import java.io.Serializable;
import javax.lang.model.element.Element;
import javax.swing.text.Document;

/**
 *
 * @author a22eliassvf
 */
class Perro implements Serializable {
    private String nombre;
    private int edad;

    public Perro(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
    
    


    public Element toXmlElement(Document doc) {
        Element perroElement = doc.createElement("Perro");

        Element nombreElement = doc.createElement("Nombre");
        nombreElement.appendChild(doc.createTextNode(this.nombre));
        perroElement.appendChild(nombreElement);

        Element edadElement = doc.createElement("Edad");
        edadElement.appendChild(doc.createTextNode(String.valueOf(this.edad)));
        perroElement.appendChild(edadElement);

        return perroElement;
    }

    public static Perro fromXmlElement(Element element) {
        String nombre = element.getElementsByTagName("Nombre").item(0).getTextContent();
        int edad = Integer.parseInt(element.getElementsByTagName("Edad").item(0).getTextContent());

        return new Perro(nombre, edad);
    }

    @Override
    public String toString() {
        return "Perro [nombre=" + nombre + ", edad=" + edad + "]";
    }
}
