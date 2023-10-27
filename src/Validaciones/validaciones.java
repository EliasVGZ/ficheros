package Validaciones;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class validaciones {


//6. Valida si una cadena tiene el formato de nombre de persona, incluyendo nombres compuestos

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String DNI="12345678Y";
        boolean esCorrecto=false;
        esCorrecto=validaDNI_Exp(DNI);
        if(!esCorrecto){
            System.err.println("No flipes, no es asi");
        }else{
            System.out.println("Esta bien");
        }
    }
    public static boolean validaNumeroEntero_Exp(String texto){
        //    1. Valida si una cadena es un numero entero (positivo/negativo);

        return texto.matches("^-?[0-9]+$");
    }
    public static boolean validaNumeroEnteroPositivo_Exp(String texto){

        return texto.matches("^[0-9]+$");
    }
    public static boolean validaNumeroEnteroNegativo_Exp(String texto){

        return texto.matches("^-[0-9]+$");
    }
    //2. Valida si una cadena  es un numero real (positivo o negativo) con un numero de decimales.
    //@Contract(pure = true)
    public static boolean validarNumeroReal(String numero){
        return numero.matches("^-?[0-9]+([\\.,][0.9]+)?$");
    }


    //            3. Valida si una cadena es un DNI: 8 numeros y una letra al final.
    public static boolean validaDNI_Exp(String DNI){
        boolean respuesta=false;
        if (DNI.matches("^[0-9]{8}[TtRrWwAaGgMmYyFfPpDdXxBbNnJjZzSsQqVvHhLlCcKkEe]{1}$")){
            respuesta= true;
        }
        return respuesta;

    }
    //VALIDAR UNA FECHA
    public static boolean validaNumeroFecha_Exp(String texto) {

        return texto.matches("^(0?[1-9]|[12][0-9]|3[01])[\\/](0?[1-9]|1[012])[/\\/](19|20)\\d{2}$");
    }
    //5. Valida si una cadena tiene formato de telefono (9 numeros)
    public static boolean validarNumeroTelefono(String telefono){
        boolean respuesta = false;
        if(telefono.matches("[0-9]{9}")) {
            respuesta = true;
        }
        return respuesta;
    }

    public static boolean validarNombre(String nombre){
        boolean respuesta=false;
        if(nombre.matches("^([A-Za-z][a-z]+[ ]?){1,20}$")&&nombre.length()<10){
            respuesta =true;
        }
        return respuesta;
    }
    public static boolean validarApellido(String apellido){
        boolean respuesta=false;
        if(apellido.matches("^([A-Za-z][a-z]+[ ]?){1,20}$")&&apellido.length()<20){
            respuesta =true;
        }
        return respuesta;
    }

    public static boolean validarNss(String nss){
        boolean respuesta=false;
        if(nss.matches("^[0-9]{9}")){
            respuesta=true;

        }
        return respuesta;
    }

    public static boolean validarEdad(String edad) {
        Pattern pat = Pattern.compile("[1-9](\\d{1,3})");
        Matcher mat = pat.matcher(edad);
        return mat.matches();
    }

    public static boolean validarSueldo(double salario){
        Pattern pat = Pattern.compile("^[1-9][0-9]{0,4}$");
        Matcher mat = pat.matcher(String.valueOf(salario));
        return mat.matches();
    }


}
