package Practica7;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Alumno {

    //Atributos:
    private String      nombre;
    private String      apellido1;
    private String      apellido2;
    private int         notaLM;
    private int         notaProg;
    private int         notaED;
    private int         notaBD;
    private int         notaSI;
    private int         notaFOL;
    //Guardo las convalidaciones en array por el orden de aparición.
    private boolean[]   convalidadas = {false, false, false, false, false, false};
    //todo añadir a constructores, getters y setters

    //Constructores:
    public Alumno() {
    }

    public Alumno(String nombre, String apellido1, String apellido2, int notaLM, int notaProg, int notaED, int notaBD, int notaSI, int notaFOL) {
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.notaLM = notaLM;
        this.notaProg = notaProg;
        this.notaED = notaED;
        this.notaBD = notaBD;
        this.notaSI = notaSI;
        this.notaFOL = notaFOL;
    }

    //Métodos:
    public static void convertirAlumno() {
        //todo
    }
    
    public String crearArchivoDeAlumno() throws IOException {
        String ruta = this.getNombre() + this.getApellido1() + this.getApellido2() + ".txt";
        File archivo = new File(ruta);
        if (archivo.createNewFile()) {
            System.out.println(ruta+ " - archivo creado.");
        }else System.out.println(ruta + ": el archivo ya existe.");
        return ruta;
    }
    
    public void rellenarArchivoDeAlumno(String ruta) {
        try (BufferedWriter writerMejorado = new BufferedWriter(new FileWriter(ruta))) {
            boolean eof = false;
            //TODO seguro que lo quiero en buffer?
            writerMejorado.write("---------------------------------------------\nBoletín de notas CIFP FBMOLL\n" +
                    "---------------------------------------------\nAlumno: " + this.getNombre() + " " + 
                    this.getApellido1() + " " + this.getApellido2() + "\n---------------------------------------------" +
                    "\nMódulo                            Nota\n------------------------------   -------\n");
            writerMejorado.write("Lenguaje de marcas                  " + this.getNotaLM() + "\n");
            writerMejorado.write("Programación                        " + this.getNotaProg()+ "\n");
            writerMejorado.write("Entornos de desarrollo              " + this.getNotaED() + "\n");
            writerMejorado.write("Base de datos                       " + this.getNotaBD() + "\n");
            writerMejorado.write("Sistemas informáticos               " + this.getNotaSI() + "\n");
            writerMejorado.write("FOL                                 " + this.getNotaFOL() + "\n");
            writerMejorado.write("---------------------------------------------\n");
            //TODO acabar
        } catch (IOException eio) {
            System.out.println("IOException. Error al leer el archivo.");         
            //TODO: al main
        }
        //TODO
    }

    //Getters y setters:
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public int getNotaLM() {
        return notaLM;
    }

    public void setNotaLM(int notaLM) {
        this.notaLM = notaLM;
    }

    public int getNotaProg() {
        return notaProg;
    }

    public void setNotaProg(int notaProg) {
        this.notaProg = notaProg;
    }

    public int getNotaED() {
        return notaED;
    }

    public void setNotaED(int notaED) {
        this.notaED = notaED;
    }

    public int getNotaBD() {
        return notaBD;
    }

    public void setNotaBD(int notaBD) {
        this.notaBD = notaBD;
    }

    public int getNotaSI() {
        return notaSI;
    }

    public void setNotaSI(int notaSI) {
        this.notaSI = notaSI;
    }

    public int getNotaFOL() {
        return notaFOL;
    }

    public void setNotaFOL(int notaFOL) {
        this.notaFOL = notaFOL;
    }

}
