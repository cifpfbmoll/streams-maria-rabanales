/*
Ejercicio 3 de Streams por María Rabanales.
Incluye las clases: Ej3 y Alumno
*/
package Practica7;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class Ej3 {
    
    public static void main(String[] args) {
        //LÓGICA: leo el archivo y lo convierto en objetos. Meto los objetos en un array.
        //por cada elemento del array hago el proceso de crear texto e imprimir lo que sea.
        leerArchivo();
        //TODO ver cómo enlazo todo esto
        //for cada alumno en archivo:
        Alumno a1 = new Alumno("Adrián", "Hernández", "Sánchez", 9, 5, 5, 7, 10, 10);
        Alumno.convertirAlumno();
        String archivo = "";
        try {
        archivo = a1.crearArchivoDeAlumno();
        } catch (IOException ioe) {
            escribirErrores("Error en crearArchivoDeAlumno.", Arrays.toString(ioe.getStackTrace()));
        }
        a1.rellenarArchivoDeAlumno(archivo);
    }
    
    public static void leerArchivo() {
        //todo
    }
    
    public static void escribirErrores(String texto, String traza) {
        try (BufferedWriter writerMejorado = new BufferedWriter(new FileWriter("errores3.txt", true))) {
            String pattern = "MM/dd/yyyy HH:mm:ss";
            DateFormat df = new SimpleDateFormat(pattern);
            Date fechahora = Calendar.getInstance().getTime();
            String fechahoraString = df.format(fechahora);
            writerMejorado.write(fechahoraString);
            writerMejorado.write(": " + texto);
            writerMejorado.write("\n" + traza + "\n\n");            
        } catch (IOException eio) {
            System.out.println("IOException. Error al leer el archivo errores3.txt.");         //TODO: lo añado porque me lo exige el IDE; ¿por qué?
        }
    }
}
