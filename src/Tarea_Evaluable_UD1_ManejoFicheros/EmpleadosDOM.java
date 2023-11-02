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
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.io.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import static Tarea_Evaluable_UD1_ManejoFicheros.Principal.br;
import static Tarea_Evaluable_UD1_ManejoFicheros.Principal.listadoEmpleados;

public class EmpleadosDOM {



    public void menuDOM() throws IOException{


        int opcion = 0;
        while (opcion != 6) {
            System.out.println("Elige una opcion (pulsa 6 para salir)");
            System.out.println("--MENU--");
            System.out.println("1. Agregar empleado a XML");
            System.out.println("2. Modificar Salario Empleado en XML");
            System.out.println("3. Borrar a XML");
            System.out.println("4. Consultar a XML");
            System.out.println("5. Lectura XML con SAX");

            opcion = Integer.parseInt(br.readLine());

            switch (opcion) {
                case 1:
                    lecturaEmpleados();
                break;
                case 2:
                    System.out.println("Introduce el DNI:");
                    String dni = br.readLine();
                    System.out.println("Introduce el nuevo salario:");
                    double nuevoSalario = Double.parseDouble(br.readLine());
                    modificarXML(dni, nuevoSalario);
                    break;
                case 3:
                    System.out.println("introduce el dni: ");
                    String nif = br.readLine();
                    borrarEmpleado(nif);
                break;
                case 4:
                    System.out.println("dni del empleado: ");
                    String dniConsultar = br.readLine();
                    consultarXML(dniConsultar);
                break;
                case 5: lecturaSax();
                break;
                default:
                    System.out.println("opcion no valida");
            }

        }
    }

    private void consultarXML(String dni) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbf.newDocumentBuilder();

            File archivo = new File("src\\Tarea_Evaluable_UD1_ManejoFicheros\\empleados.xml");
            Document doc = builder.parse(archivo);

            NodeList empleados = doc.getElementsByTagName("empleado");

            for (int i = 0; i < empleados.getLength(); i++) {
                Element empleado = (Element) empleados.item(i);
                String nif = empleado.getElementsByTagName("nif").item(0).getTextContent();

                if (nif.equalsIgnoreCase(dni)) {
                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
                    Transformer transformer = transformerFactory.newTransformer();
                    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                    DOMSource source = new DOMSource(empleado);

                    //
                    StringWriter writer = new StringWriter();
                    StreamResult result = new StreamResult(writer);


                    transformer.transform(source, result);

                    String empleadoXML = writer.toString();
                    System.out.println("Empleado encontrado en  XML:\n" + empleadoXML);
                }
            }

            System.out.println("DNI no encontrado en el archivo XML.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private void borrarEmpleado(String dni) {

        try{
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbf.newDocumentBuilder();

            File archivo = new File("src\\Tarea_Evaluable_UD1_ManejoFicheros\\empleados.xml");
            Document doc = builder.parse(archivo);

            NodeList empleados = doc.getElementsByTagName("empleado");

            for(int i =0; i < empleados.getLength(); i++){
                Element empleado = (Element) empleados.item(i);
                String nif = empleado.getElementsByTagName("nif").item(0).getTextContent();

                if(nif.equalsIgnoreCase(dni)){
                    empleado.getParentNode().removeChild(empleado); // Elimina el nodo del empleado
                    System.out.println("empleado con dni: "+dni+ " borrado");
                }

                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");

                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(archivo);
                transformer.transform(source, result);


            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void modificarXML(String dni, double nuevoSalario) {

        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbf.newDocumentBuilder();

            File archivo = new File("src\\Tarea_Evaluable_UD1_ManejoFicheros\\empleados.xml");
            Document doc = builder.parse(archivo);

            NodeList empleados = doc.getElementsByTagName("empleado");

            for (int i = 0; i < empleados.getLength(); i++) {
                Element empleado = (Element) empleados.item(i);
                String nif = empleado.getElementsByTagName("nif").item(0).getTextContent();

                if (nif.equals(dni)) {
                    Element salarioElement = (Element) empleado.getElementsByTagName("salario").item(0);
                    salarioElement.setTextContent(Double.toString(nuevoSalario));

                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
                    Transformer transformer = transformerFactory.newTransformer();
                    transformer.setOutputProperty(OutputKeys.INDENT, "yes");

                    DOMSource source = new DOMSource(doc);
                    StreamResult result = new StreamResult(archivo);
                    transformer.transform(source, result);

                    System.out.println("Salario modificado correctamente.");

                }else{
                    System.out.println("Dni no encontradooo");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void lecturaEmpleados(){

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

            String opcion;

            do{
                // Agregar nuevos empleados
                System.out.println("ingrese nif del nuevo empleado");
                String nif  = br.readLine();
                System.out.println("ingrese nombre del nuevo empleado");
                String nombre = br.readLine();
                System.out.println("ingrese apellido del nuevo empleado");
                String apellido = br.readLine();
                System.out.println("ingrese salario del empleado");
                Double salario = Double.parseDouble(br.readLine());
                agregarEmpleado(registroEmpleados,nif, nombre, apellido, salario);
                System.out.println("empleado agregado.");

                System.out.println("Quieres añadir un nuevo empleado? (s/n)");
                opcion = br.readLine();

            }while(opcion.equalsIgnoreCase("s"));

            Element empleadosElement = registroEmpleados.getDocumentElement(); // Obtener el elemento <empleados>

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

    public static void agregarEmpleado(Document registroEmpleados, String nif, String nombre, String apellido, double salario) {
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

    public static void lecturaSax(){
        try{
            File archivo = new File("src\\Tarea_Evaluable_UD1_ManejoFicheros\\empleados.xml");

            if (!archivo.exists()) {
                // Si el archivo no existe, crea uno nuevo
                archivo.createNewFile();
            }

            SAXParserFactory factory = SAXParserFactory.newInstance();

            SAXParser parser = factory.newSAXParser();
            EmpleadosSAX leeEmpleados = new EmpleadosSAX();
            parser.parse(archivo, leeEmpleados);

        }catch (ParserConfigurationException | SAXException | IOException ex){
            System.out.println(ex.getMessage());
        }


    }





}
