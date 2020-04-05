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
    public static Alumno convertirAlumno(String[] alumnoArray) {
        Alumno a1 = new Alumno();
        a1.setNombre(alumnoArray[0]);
        a1.setApellido1(alumnoArray[1]);
        a1.setApellido2(alumnoArray[2]);
        //Entiendo que todas las convalidaciones son a nota 5.
        for (int i = 3; i < 9; i++) {
            String nota = alumnoArray[i];
            if (nota.equals("c-5")) {
                nota = "5";
                a1.convalidadas[i-3] = true;
            }
            int notaAdaptada = Integer.parseInt(nota);
            switch (i) {
                case 3:
                    a1.setNotaLM(notaAdaptada);
                    break;
                case 4:
                    a1.setNotaProg(notaAdaptada);
                    break;
                case 5:
                    a1.setNotaED(notaAdaptada);
                    break;
                case 6:
                    a1.setNotaBD(notaAdaptada);
                    break;
                case 7:
                    a1.setNotaSI(notaAdaptada);
                    break;
                case 8:
                    a1.setNotaLM(notaAdaptada);
                    break;
                case 9:
                    a1.setNotaFOL(notaAdaptada);
                    break;
            }
        }
        return a1;
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
            int[] notas = {this.getNotaLM(), this.getNotaProg(), this.getNotaED(), this.getNotaBD(), this.getNotaSI(), this.getNotaFOL()};
            int[] estadisticas = getEstadisticas(notas);
            //TODO seguro que lo quiero en buffer?
            writerMejorado.write("---------------------------------------------\nBoletín de notas CIFP FBMOLL\n" +
                    "---------------------------------------------\nAlumno: " + this.getNombre() + " " + 
                    this.getApellido1() + " " + this.getApellido2() + "\n---------------------------------------------" +
                    "\nMódulo                             Nota\n------------------------------   -------\n");
            writerMejorado.write("Lenguaje de marcas                   " + notas[0] + "\n");
            writerMejorado.write("Programación                         " + notas[1] + "\n");
            writerMejorado.write("Entornos de desarrollo               " + notas[2] + "\n");
            writerMejorado.write("Base de datos                        " + notas[3] + "\n");
            writerMejorado.write("Sistemas informáticos                " + notas[4] + "\n");
            writerMejorado.write("FOL                                  " + notas[5] + "\n");
            writerMejorado.write("---------------------------------------------\n");
            writerMejorado.write("Nº de módulos aprobados:             " + estadisticas[0] + "\n");
            writerMejorado.write("Nº de módulos suspendidos:           " + estadisticas[1] + "\n");
            writerMejorado.write("Nº de módulos convalidados:          " + estadisticas[2] + "\n");
            writerMejorado.write("---------------------------------------------\n\n");
            writerMejorado.write("Fecha: " + Ej3.getFecha() + "\nLugar: Palma de Mallorca");
            //TODO acabar
        } catch (IOException eio) {
            System.out.println("IOException. Error al leer el archivo.");         
            //TODO: al main
        }
    }
    
    public int[] getEstadisticas(int[] notas) {
        int[] estadisticas = {0, 0, 0};         //aprobados, suspensos, convalidados
        for (int nota : notas) {
            if (nota < 5) {
                estadisticas[1]++;
            } else {
                estadisticas[0]++;
            }
        }
        for (boolean convalidacion : this.convalidadas) {
            if (convalidacion == true) {
                estadisticas[2]++;
            }
        }
        return estadisticas;
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

    public boolean[] getConvalidadas() {
        return convalidadas;
    }

    public void setConvalidadas(boolean[] convalidadas) {
        this.convalidadas = convalidadas;
    }

}
