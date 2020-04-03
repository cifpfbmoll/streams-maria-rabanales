package Practica7;

public class Actor {
    
    //Atributos:
    private String      nombre;
    private String      apellido;
    
    //Constructores:
    public Actor() {
    }

    public Actor(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }
    
    //Métodos:

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }    
}
