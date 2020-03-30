//Entiendo que esto es una excepción informativa...
package Practica7;

public class Excepcion1Fichero extends Exception {

    @Override
    public String getMessage() {
        return "No has introducido una ruta.";
    }
}
