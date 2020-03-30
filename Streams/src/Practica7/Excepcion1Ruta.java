package Practica7;

public class Excepcion1Ruta extends Exception {
    
    @Override
    public String getMessage() {
        return "La ruta del fichero de origen no es válida.";
    }
    
}
