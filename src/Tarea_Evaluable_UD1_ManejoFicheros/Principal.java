package Tarea_Evaluable_UD1_ManejoFicheros;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.ArrayList;

import static Validaciones.validaciones.validaDNI_Exp;

public class Principal {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static ArrayList<Empleados> listadoEmpleados = new ArrayList<>();
    
    public static void main(String[] args) throws Exception {

        menu();





    }


    public static void menu() throws Exception {

        int opcion = 0;
        while (opcion != 6) {
            System.out.println("Elige una opcion (pulsa 6 para salir)");
            System.out.println("--MENU--");
            System.out.println("1. Consulta");
            System.out.println("2. Insercion");
            System.out.println("3. Modificación");
            System.out.println("4. Borrado");
            System.out.println("5. Listar");
            opcion = Integer.parseInt(br.readLine());

            switch (opcion) {
                case 1: consultar();
                break;
                case 2: {
                }
                break;
                case 3: {
                }
                break;
                case 4: {
                }
                break;
                case 5: {
                }
                break;
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

    public static void insercion() throws IOException {
        boolean encontradonif = false;
        System.out.println("Insertar la informacion del nuevo empleado");
        System.out.println("NIF:");
        String nif = br.readLine();
        for (Empleados info : listadoEmpleados) {
            if (nif.equalsIgnoreCase(info.getNIF())) {
                System.out.println("El empleado ya existe");
                encontradonif = true;
                break;
            }
        }
        // TODO SEGUIR AÑDIENDO EMPLEADO
    }


}
