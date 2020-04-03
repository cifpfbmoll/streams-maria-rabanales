package Practica7;

import java.util.ArrayList;

public class Cartelera {

    //Atributos:
    private String              titulo;
    private int                 estreno;
    private String              director;
    private int                 duracion;
    private String              sinopsis;
    private ArrayList<Actor>    reparto = new ArrayList<>();
    private String              sesion;
    //TODO: no habrá algun tipo de atributo hora:min? Investigar.

    //Constructores:
    public Cartelera() {
    }

    public Cartelera(String titulo, int estreno, String director, int duracion, String sinopsis, String sesion) {
        this.titulo = titulo;
        this.estreno = estreno;
        this.director = director;
        this.duracion = duracion;
        this.sinopsis = sinopsis;
        this.sesion = sesion;
    }

    //Métodos:
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getEstreno() {
        return estreno;
    }

    public void setEstreno(int estreno) {
        this.estreno = estreno;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public ArrayList<Actor> getReparto() {
        return reparto;
    }

    public void setReparto(ArrayList<Actor> reparto) {
        this.reparto = reparto;
    }

    public String getSesion() {
        return sesion;
    }

    public void setSesion(String sesion) {
        this.sesion = sesion;
    }
    
}
