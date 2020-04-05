/*
Ejercicio 3 de Streams por María Rabanales.
Incluye las clases: Ej3 y Alumno. 
Incluye la excepción Excepción3Menor pero como no existe el atributo edad no la puedo lanzar.
 */
package Practica7;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class Ej3 {

    public static void main(String[] args) {
        //LÓGICA: leo el archivo y lo convierto en objetos. Meto los objetos en un array.
        //por cada elemento del array hago el proceso de crear texto e imprimir lo que sea.
        ArrayList<String[]> archivoEnArray = new ArrayList<>();
        try {
            archivoEnArray = leerArchivo(archivoEnArray);
        } catch (IOException ioe) {
            escribirErrores("Error en pasar archivo a Array.", Arrays.toString(ioe.getStackTrace()));
        }
        /* TRAZA:
        for (int i = 0; i < archivoEnArray.size(); i++) {
            System.out.println(Arrays.toString(archivoEnArray.get(i)));
        } */
        for (String[] alumnoArray : archivoEnArray) {    //probando con un foreach
            Alumno a1 = Alumno.convertirAlumno(alumnoArray);
            String archivo = "";
            try {
                archivo = a1.crearArchivoDeAlumno();
            } catch (IOException ioe) {
                escribirErrores("Error en crear archivo de alumno.", Arrays.toString(ioe.getStackTrace()));
            }
            a1.rellenarArchivoDeAlumno(archivo);
        }
    }

    public static ArrayList<String[]> leerArchivo(ArrayList<String[]> archivoEnArray) throws IOException {
        BufferedReader lectorMejorado = new BufferedReader(new FileReader("alumnos.txt"));
        boolean eof = false;
        String linea = "";
        String[] elemento = null;
        while (!eof) {
            linea = lectorMejorado.readLine();
            if (linea != null) {
                elemento = linea.split(" ");
                archivoEnArray.add(elemento);
            } else {
                eof = true;
            }
        }
        lectorMejorado.close();
        return archivoEnArray;
    }

    public static String getFecha() {
        String pattern = "dd/MM/yyyy";
        DateFormat df = new SimpleDateFormat(pattern);
        Date fechahora = Calendar.getInstance().getTime();
        String fechaString = df.format(fechahora);
        return fechaString;
    }

    public static void escribirErrores(String texto, String traza) {
        try (BufferedWriter writerMejorado = new BufferedWriter(new FileWriter("errores.txt", true))) {
            String pattern = "MM/dd/yyyy HH:mm:ss";
            DateFormat df = new SimpleDateFormat(pattern);
            Date fechahora = Calendar.getInstance().getTime();
            String fechahoraString = df.format(fechahora);
            writerMejorado.write(fechahoraString);
            writerMejorado.write(": " + texto);
            writerMejorado.write("\n" + traza + "\n\n");
        } catch (IOException eio) {
            System.out.println("IOException. Error al leer el archivo errores.txt.");         //TODO: lo añado porque me lo exige el IDE; ¿por qué?
        }
    }
}
