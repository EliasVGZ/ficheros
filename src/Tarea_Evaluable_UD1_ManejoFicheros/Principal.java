package Tarea_Evaluable_UD1_ManejoFicheros;

import EjercicioXMLconSAX.LeerLibrosInfo;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Iterator;

import static Validaciones.validaciones.validaDNI_Exp;

public class Principal {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static ArrayList<Empleados> listadoEmpleados = new ArrayList<>();
    
    public static void main(String[] args) throws Exception {

        EmpleadoTXT empleadoTXT = new EmpleadoTXT();
        EmpleadosDOM empleadoxml = new EmpleadosDOM();



        //Creo 5 empleados para tener
        Empleados empleado1 = new Empleados("00000000Y", "Juan", "Perez", 2000.0);
        Empleados empleado2 = new Empleados("98765432B", "Maria", "Gomez", 2500.0);
        Empleados empleado3 = new Empleados("55555555C", "Pedro", "Rodriguez", 1800.0);
        Empleados empleado4 = new Empleados("11111111D", "Laura", "Martinez", 2200.0);
        Empleados empleado5 = new Empleados("88888888E", "Carlos", "Sanchez", 1900.0);
        listadoEmpleados.add(empleado1);
        listadoEmpleados.add(empleado2);
        listadoEmpleados.add(empleado3);
        listadoEmpleados.add(empleado4);
        listadoEmpleados.add(empleado5);

        empleadoTXT.escrituraEmpleados(listadoEmpleados);
        empleadoxml.lecturaEmpleados(listadoEmpleados);


        menu();





    }


    public static void menu() throws Exception {



        int opcion = 0;
        while (opcion != 7) {
            System.out.println("Elige una opcion (pulsa 6 para salir)");
            System.out.println("--MENU--");
            System.out.println("1. Consulta");
            System.out.println("2. Insercion");
            System.out.println("3. Modificación");
            System.out.println("4. Borrado");
            System.out.println("5. Listar");
            System.out.println("6. Lectura archivo con SAX");
            opcion = Integer.parseInt(br.readLine());

            switch (opcion) {
                case 1: consultar();
                break;
                case 2: insercion();
                break;
                case 3: modificacion();
                break;
                case 4: borrado();
                break;
                case 5: listarEmpleados();
                break;
                case 6: lecturaSax();
                    break;
                default:
                    System.out.println("opcion no valida");
            }

        }
    }

    public static void consultar() throws Exception {
        String consulta;
        //PEDIR DNI MIENTRA NO SEA VALIDO
        do {
            System.out.println("Escribe el NIF del usuario para consultar su INFO");
            consulta = br.readLine();

            if (!validaDNI_Exp(consulta)) {
                System.out.println("DNI invalido");
            }
        } while (!validaDNI_Exp(consulta));

        boolean encontrado = false;

        //SI LO ENCUENTRA MUESTRA LA INFORMACION DEL EMPELADO
        for (Empleados info : listadoEmpleados) {
            if (consulta.equalsIgnoreCase(info.getNIF())) {
                System.out.println(info.toString());
                encontrado = true;
                break;
            }

        }
        if (!encontrado){
            System.out.println("Empleado no encontrado");
        }
    }

    public static void insercion() throws Exception {


        EmpleadosDOM empleadoDom = new EmpleadosDOM();
        String opcion;

        do {
            System.out.println("Insertar la informacion del nuevo empleado");
            System.out.println("NIF:");
            String nif = br.readLine();

            boolean encontradonif = false;

            for (Empleados info : listadoEmpleados) {
                if (nif.equalsIgnoreCase(info.getNIF())) {
                    System.out.println("El empleado ya existe");
                    encontradonif = true;
                    break;
                }
            }

            if (!encontradonif) {
                System.out.println("Nombre del empleado");
                String nombre = br.readLine();
                System.out.println("Apellido del empleado");
                String apellido = br.readLine();
                System.out.println("Salario");
                Double salario = Double.parseDouble(br.readLine());

                Empleados e1 = new Empleados(nif, nombre, apellido, salario);
                listadoEmpleados.add(e1);
                //TODO NO FUNCIONA empleadoDom.agregarEmpleado(registroEmpleados, nif, nombre, apellido, salario );
                System.out.println("Empleado agregado");
            }

            System.out.println("Quieres agregar otro empleado? (s/n)");
            opcion = br.readLine();

        } while (opcion.equalsIgnoreCase("s"));
    }

    public static void modificacion() throws IOException {


        String opcion;

        do {
            System.out.println("modificar salario de un empleado");
            System.out.println("NIF:");
            String nif = br.readLine();

            boolean encontradonif = false;

            for (Empleados info : listadoEmpleados) {
                if (nif.equalsIgnoreCase(info.getNIF())) {
                    System.out.println("Empleado encontrado, escriba el nuevo salario");
                    Double nuevoSalario = Double.parseDouble(br.readLine());
                    info.setSalario(nuevoSalario);
                    encontradonif = true;
                    break;
                }
            }

            if (!encontradonif) {
                System.out.println("Empleado no existe");
            }

            System.out.println("Quieres modificar salario de otro empleado? (s/n)");
            opcion = br.readLine();
        } while (opcion.equalsIgnoreCase("s"));
    }

    public static void borrado() throws IOException {

        //TODO NO SÉ SI ESTO ES LO QUE SE PIDE
        String opcion;



        do {
            System.out.println("Borrar empleado");
            System.out.println("NIF:");
            String nif = br.readLine();

            boolean encontradonif = false;

            /*Iterator<Empleados> iterator = listadoEmpleados.iterator();
            while(iterator.hasNext()){
                Empleados info = iterator.next();
                if(nif.equalsIgnoreCase(info.getNIF())){
                    iterator.remove();
                    System.out.println("empleado eliminad");
                    encontradonif=true;
                }
            }*/
            for (Empleados info : listadoEmpleados) {
                if (nif.equalsIgnoreCase(info.getNIF())) {
                    info.setBorrado(true); //BORRADO LOGICO
                    System.out.println("Empleado con NIF " + nif + " borrado lógicamente.");
                    encontradonif = true;
                    break;
                }
            }

            if (!encontradonif) {
                System.out.println("Empleado no existe");
            }

            System.out.println("Quieres borrar otro empleado? (s/n)");
            opcion = br.readLine();
        } while (opcion.equalsIgnoreCase("s"));
    }

    public static void listarEmpleados(){
        System.out.println("Lista empleados");
        for(Empleados info: listadoEmpleados){
            if(!info.isBorrado()){
                System.out.println(info.toString());
            }
        }
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
