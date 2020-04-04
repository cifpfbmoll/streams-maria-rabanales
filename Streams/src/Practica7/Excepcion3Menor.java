package Practica7;

public class Excepcion3Menor extends Exception{
    
    //Esto no es un override sino un overload:
    public String getMessage(String nombre, int edad) {
        String mensaje = "El alumno " + nombre + " tiene " + edad + " años.";
        return mensaje;
    }
    
}
