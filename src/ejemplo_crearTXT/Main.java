package ejemplo_crearTXT;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("empleados.txt", true));

            while (true) {
                System.out.print("Ingrese el nombre del empleado (o 'salir' para terminar): ");
                String nombre = scanner.nextLine();

                if (nombre.equalsIgnoreCase("salir")) {
                    break;
                }

                System.out.print("Ingrese el ID del empleado: ");
                int id = Integer.parseInt(scanner.nextLine());

                System.out.print("Ingrese el DNI del empleado: ");
                String dni = scanner.nextLine();

                // Escribir en el archivo
                writer.write(nombre + "," + id + "," + dni);
                writer.newLine();
            }

            writer.close();
            System.out.println("Datos de empleados guardados en empleados.txt.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
