package Tarea_Evaluable_UD1_ManejoFicheros;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;

public class Principal {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) {





    }


    public static void menu() throws IOException {
        int opcion = 0;
        while (opcion != 6)
        System.out.println("Elige una opcion (pulsa 6 para salir)");
        opcion = Integer.parseInt(br.readLine());

        System.out.println("--MENU--");
        System.out.println("1. Consulta");
        System.out.println("2. Insercion");
        System.out.println("3. Modificación");
        System.out.println("4. Borrado");
        System.out.println("5. Listar");

        switch (opcion){
            case 1: {}
            break;
            case 2: {}
            break;
            case 3: {}
            break;
            case 4: {}
            break;
            case 5: {}
            break;
        }


    }


}
