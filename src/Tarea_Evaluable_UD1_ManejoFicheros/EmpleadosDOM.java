package Tarea_Evaluable_UD1_ManejoFicheros;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.io.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;

import static Tarea_Evaluable_UD1_ManejoFicheros.Principal.br;

public class EmpleadosDOM {


    public void menuDOM() throws IOException {
        int opcion = 0;
        while (opcion != 8) {
            System.out.println("Elige una opcion (pulsa 9 para salir)");
            System.out.println("--MENU--");
            System.out.println("1. Insertar a XML");
            System.out.println("2. Modificar a XML");
            System.out.println("3. Borrar a XML");
            System.out.println("4. Consultar a XML");

            opcion = Integer.parseInt(br.readLine());

            switch (opcion) {

                default:
                    System.out.println("opcion no valida");
            }

        }
    }
    public void lecturaEmpleados(ArrayList<Empleados> listaEmpleados){

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try{
            // Cargar el archivo XML existente
            File lecturaEmpleados = new File("src\\Tarea_Evaluable_UD1_ManejoFicheros\\empleados.xml");

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbf.newDocumentBuilder();

            Document registroEmpleados;

            if (lecturaEmpleados.exists()) {
                // si el archivo existe se carga
                registroEmpleados = builder.parse(lecturaEmpleados);
                registroEmpleados.getDocumentElement().normalize();

            } else {
                // si el archivo no existe se crea uno nuevo
                registroEmpleados = builder.newDocument();
                registroEmpleados.appendChild(registroEmpleados.createElement("empleados"));
            }

            Element empleadosElement = registroEmpleados.getDocumentElement(); // Obtener el elemento <empleados>

            for (Empleados empleado : listaEmpleados){
                Element empleadoElement = toXmlElement(registroEmpleados, empleado);
                empleadosElement.appendChild(empleadoElement);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(registroEmpleados);

            StreamResult result = new StreamResult(new File("src\\Tarea_Evaluable_UD1_ManejoFicheros\\empleados.xml"));
            transformer.transform(source, result);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    //CREAR FORMATO DEL XML
    public static Element toXmlElement(Document registroEmpleados, Empleados empleados) {

        //creamos un nodo empleado
        Element empleado = registroEmpleados.createElement("empleado");
        //añadimos con hijo de empleados
        registroEmpleados.getDocumentElement().appendChild(empleado);
        //creamos e nodo ID

        Element nif= registroEmpleados.createElement("nif");
        //creamos nodo texto con el valo rde la id
        Text texto = registroEmpleados.createTextNode(empleados.getNIF());
        //añadimos el valor al nodo id
        nif.appendChild(texto);
        //añadimos el nodo ID a empleado
        empleado.appendChild(nif);

        Element nombre = registroEmpleados.createElement("nombre");
        texto = registroEmpleados.createTextNode(empleados.getNombre());
        nombre.appendChild(texto);
        empleado.appendChild(nombre);

        Element apellido = registroEmpleados.createElement("apellido");
        texto = registroEmpleados.createTextNode(empleados.getApellidos());
        apellido.appendChild(texto);
        empleado.appendChild(apellido);

        Element salario = registroEmpleados.createElement("salario");
        texto = registroEmpleados.createTextNode(String.valueOf(empleados.getSalario()));
        salario.appendChild(texto);
        empleado.appendChild(salario);

        empleado.appendChild(nif);
        empleado.appendChild(nombre);
        empleado.appendChild(apellido);
        empleado.appendChild(salario);

        return empleado;
    }

    public void agregarEmpleado(Document registroEmpleados, String nif, String nombre, String apellido, double salario) {
        Element elemEmpleado = registroEmpleados.createElement("empleado");

        Element elemnif = registroEmpleados.createElement("nif");
        elemnif.appendChild(registroEmpleados.createTextNode(nif));
        elemEmpleado.appendChild(elemnif);

        Element elemNombre = registroEmpleados.createElement("nombre");
        elemNombre.appendChild(registroEmpleados.createTextNode(nombre));
        elemEmpleado.appendChild(elemNombre);


        Element elemApellido = registroEmpleados.createElement("apellido");
        elemApellido.appendChild(registroEmpleados.createTextNode(apellido));
        elemEmpleado.appendChild(elemApellido);


        Element elemSalario = registroEmpleados.createElement("salario");
        elemSalario.appendChild(registroEmpleados.createTextNode(Double.toString(salario)));
        elemEmpleado.appendChild(elemSalario);

        registroEmpleados.getDocumentElement().appendChild(elemEmpleado);
    }




}
