/*
Ejercicio 2 de Streams por María Rabanales.
Incluye las clases Ej2, Cartelera, Excepcion1Fichero y Excepcion1Ruta.
 */
package Practica7;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Ej2 {

    //Creo los dos elementos que usaré globalmente; en el string juego con los saltos de línea para facilitarme el print
    public static Scanner lector = new Scanner(System.in);
    private static final String[] TEXTOUTPUT = {"-------------------------------\n\n   Cartelera de Cine FBMoll\n\n-------------------------------\n\n----",
        "----\n\nAño: ", "\nDirector: ", "\nDuración: ", "\nSinopsis: ", "\nReparto: ", "\nSesión: ", "\n\n----", "\n\n-------------------------------\n\n"};

    public static void main(String[] args) {
        menuInicial();
    }

    public static void menuInicial() {
        boolean salir = false;
        while (salir == false) {
            System.out.println("");
            System.out.println("* * * * * * * * * * * *");
            System.out.println("MENU PRINCIPAL:");
            System.out.println("* * * * * * * * * * * *");
            System.out.println("  1- Lectura y escritura del fichero de cartelera byte a byte.");
            System.out.println("  2- Lectura y escritura de fichero de cartelera carácter a carácter.");
            System.out.println("  3- Lectura y escritura de fichero línea a línea con buffers.");
            System.out.println("  4- Tratamiento de objetos.");
            System.out.println("  0- Salir.");
            System.out.println("* * * * * * * * * * * *");
            System.out.println("OPCIÓN ELEGIDA:");
            String opcionInicial = lector.nextLine();
            System.out.println("* * * * * * * * * * * *");

            try {
                switch (opcionInicial) {
                    case "1":
                        operarEnBytes();
                        break;
                    case "2":
                        operarEnCaracter();
                        break;
                    case "3":
                        operarConBuffers();
                        break;
                    case "4":
                        tratarObjetos();
                        break;
                    case "0":
                        salir = true;
                        System.out.println("ADIÓS.");
                        System.out.println("* * * * * * * * * * * *");
                        break;
                    default:
                        System.out.println("  Opción imposible.");
                }
            } catch (Excepcion1Ruta er) {
                System.out.println(er.getMessage());
                escribirErrores(er.getMessage());
            }
        }
    }

    public static String[] solicitarRutas() throws Excepcion1Ruta {
        String[] rutas = new String[2];
        System.out.println("                   Recordar: es origen.txt");
        System.out.println("Introducir ruta del archivo de origen:");
        do {
            try {
                rutas[0] = introducirRuta();
            } catch (Excepcion1Fichero ef) {
                System.out.println(ef.getMessage());
                escribirErrores(ef.getMessage());
            }
        } while (rutas[0] == null);
        //Aquí apartado b: excepción por si el fichero de entrada no existe. Lo quiero elevar al main.
        File archivo = new File(rutas[0]);
        if (!archivo.exists()) {
            throw new Excepcion1Ruta();
        }
        System.out.println("                   Recordar: es destino.txt");
        System.out.println("Introducir ruta del archivo de destino:");
        do {
            try {
                rutas[1] = introducirRuta();
            } catch (Excepcion1Fichero ef) {
                System.out.println(ef.getMessage());
                escribirErrores(ef.getMessage());
            }
        } while (rutas[1] == null);
        return rutas;
    }

    //Aquí apartado c: excepción informativa para el caso en el que un fichero no sea informado. 
    public static String introducirRuta() throws Excepcion1Fichero {
        String ruta = lector.nextLine().trim();
        if (ruta.equals("")) {
            throw new Excepcion1Fichero();
        }
        return ruta;
    }

    public static void operarEnBytes() throws Excepcion1Ruta {
        String rutas[] = solicitarRutas();
        int contadorOutput = 0;         //Para recorrer el array superior
        int miByte;
        try (FileInputStream fin = new FileInputStream(rutas[0]);
                FileOutputStream fout = new FileOutputStream(rutas[1])) {
            //Primero añado el header:
            byte[] textToByte = TEXTOUTPUT[contadorOutput].getBytes();
            fout.write(textToByte);
            do {
                //Ahora añado el texto:
                miByte = fin.read();
                if (miByte != -1) {             //Leo hasta el final
                    if ((char) miByte == '#') {
                        contadorOutput++;
                        textToByte = TEXTOUTPUT[contadorOutput].getBytes();
                        fout.write(textToByte);
                        if (contadorOutput == TEXTOUTPUT.length - 3) {
                            contadorOutput = 0;
                        }
                    } else if ((char) miByte == '{') {
                        textToByte = TEXTOUTPUT[TEXTOUTPUT.length - 2].getBytes();
                        fout.write(textToByte);
                    } else if (miByte != -1) {
                        fout.write(miByte);
                    }
                }
            } while (miByte != -1);      //Mientras no alcance EOF
            //Por último añado el footer final:
            textToByte = TEXTOUTPUT[TEXTOUTPUT.length - 1].getBytes();
            fout.write(textToByte);
        } catch (IOException eio) {
            System.out.println("IOException. Error al leer el archivo.");         //TODO: lo añado porque me lo exige el IDE; ¿por qué?
        }
        //Tiene autoclosure; no cierro streams.
    }

    public static void operarEnCaracter() throws Excepcion1Ruta {
        String rutas[] = solicitarRutas();
        try (FileReader reader = new FileReader(rutas[0]);
                FileWriter writer = new FileWriter(rutas[1])) {
            int contadorOutput = 0;         //Para recorrer el array superior
            int caracter;
            //Primero añado el header:
            writer.write(TEXTOUTPUT[contadorOutput]);
            //Ahora añado el texto:
            do {
                caracter = reader.read();
                if ((char) caracter == '#') {
                    contadorOutput++;
                    writer.write(TEXTOUTPUT[contadorOutput]);
                    if (contadorOutput == TEXTOUTPUT.length - 3) {
                        contadorOutput = 0;
                    }
                } else if ((char) caracter == '{') {
                    writer.write(TEXTOUTPUT[TEXTOUTPUT.length - 2]);
                } else if (caracter != -1) {
                    writer.write(caracter);
                }
            } while (caracter != -1);      //Mientras no alcance EOF
            //Por último añado el footer final:      
            writer.write(TEXTOUTPUT[TEXTOUTPUT.length - 1]);
        } catch (IOException eio) {
            System.out.println("IOException. Error al leer el archivo.");         //TODO: lo añado porque me lo exige el IDE; ¿por qué?
        }
    }

    public static void operarConBuffers() throws Excepcion1Ruta {
        String rutas[] = solicitarRutas();
        try (BufferedReader readerMejorado = new BufferedReader(new FileReader(rutas[0]));
                BufferedWriter writerMejorado = new BufferedWriter(new FileWriter(rutas[1]))) {
            boolean eof = false;
            int contadorOutput = 0;         //Para recorrer el array superior
            String lineaLeida;
            String[] lineaPartida = null;
            //Primero añado el header:
            writerMejorado.write(TEXTOUTPUT[contadorOutput]);
            //Ahora añado el texto:
            while (!eof) {
                lineaLeida = readerMejorado.readLine();
                if (lineaLeida != null) {
                    lineaPartida = lineaLeida.split("#|\\{");
                    for (int i = 0; i < lineaPartida.length; i++) {
                        writerMejorado.write(lineaPartida[i]);
                        if (i < lineaPartida.length - 1) {
                            contadorOutput++;
                            writerMejorado.write(TEXTOUTPUT[contadorOutput]);
                            if (contadorOutput == TEXTOUTPUT.length - 2) {
                                contadorOutput = 0;
                            }
                        }
                    }
                } else {
                    eof = true;
                }
            }
            //Por último añado el footer final:      
            writerMejorado.write(TEXTOUTPUT[TEXTOUTPUT.length - 1]);
        } catch (IOException eio) {
            System.out.println("IOException. Error al leer el archivo.");         //TODO: lo añado porque me lo exige el IDE; ¿por qué?
        }
    }

    public static void tratarObjetos() {
        boolean salir2 = false;
        while (salir2 == false) {
            System.out.println("MENU DE TRATAMIENTO DE OBJETOS:");
            System.out.println("* * * * * * * * * * * *");
            System.out.println("  1- Lectura línea a línea y escritura con objetos.");
            System.out.println("  2- Lectura de objetos y escritura de objetos.");
            System.out.println("  3- Lectura de objetos y escritura por consola.");
            System.out.println("  4- Lectura por consola y escritura de objetos.");
            System.out.println("  0- Volver al menú principal.");
            System.out.println("* * * * * * * * * * * *");
            System.out.println("OPCIÓN ELEGIDA:");
            String opcionInicial = lector.nextLine();
            System.out.println("* * * * * * * * * * * *");

            switch (opcionInicial) {
                case "1":
                    //TODO
                    break;
                case "2":
                    //TODO
                    break;
                case "3":
                    //TODO
                    break;
                case "4":
                    //TODO
                    break;
                case "0":
                    salir2 = true;
                    break;
                default:
                    System.out.println("  Opción imposible.");
            }
        }
    }
    
    public static void escribirErrores(String texto) {
        //TODO: investigar lo de la pila ????
        try (BufferedWriter writerMejorado = new BufferedWriter(new FileWriter("errores.txt", true))) {
            //Para trabajar con fechas, ver: https://stackoverflow.com/questions/5683728/convert-java-util-date-to-string
            String pattern = "MM/dd/yyyy HH:mm:ss";
            DateFormat df = new SimpleDateFormat(pattern);
            Date fechahora = Calendar.getInstance().getTime();
            String fechahoraString = df.format(fechahora);
            writerMejorado.write(fechahoraString);
            writerMejorado.write(": ");
            writerMejorado.write(texto);
            writerMejorado.write("\n");
        } catch (IOException eio) {
            System.out.println("IOException. Error al leer el archivo.");         //TODO: lo añado porque me lo exige el IDE; ¿por qué?
        }
    }
}
