package Practica7;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Pelicula implements Serializable {

    //Atributos:
    private String titulo;
    private int estreno;
    private String director;
    private int duracion;
    private String sinopsis;
    //private ArrayList<Actor>    reparto = new ArrayList<>();
    //TODO: REPARTO
    private String sesion;
    //TODO: no habrá algun tipo de atributo hora:min? Investigar.

    //Constructores:
    public Pelicula() {
    }

    public Pelicula(String titulo, int estreno, String director, int duracion, String sinopsis, String sesion) {
        this.titulo = titulo;
        this.estreno = estreno;
        this.director = director;
        this.duracion = duracion;
        this.sinopsis = sinopsis;
        this.sesion = sesion;
    }

    //Métodos:
    public void introducirPelicula() {
        System.out.println("NUEVA PELÍCULA:");
        System.out.println("  Título: ");
        this.setTitulo(Ej2.lector.nextLine());
        System.out.println("  Estreno: ");
        this.setEstreno(Integer.parseInt(Ej2.lector.nextLine()));
        System.out.println("  Director: ");
        this.setDirector(Ej2.lector.nextLine());
        System.out.println("  Duracion: ");
        this.setDuracion(Integer.parseInt(Ej2.lector.nextLine()));
        System.out.println("  Sinopsis: ");
        this.setSinopsis(Ej2.lector.nextLine());
        System.out.println("  Sesion: ");
        this.setSesion(Ej2.lector.nextLine());
    }

    public void mostrarPelicula() {
        System.out.println("DATOS DE PELÍCULA:");
        System.out.println("  Título: " + this.getTitulo());
        System.out.println("  Estreno: " + this.getEstreno());
        System.out.println("  Director: " + this.getDirector());
        System.out.println("  Duracion: " + this.getDuracion());
        System.out.println("  Sinopsis: " + this.getSinopsis());
        System.out.println("  Sesion: " + this.getSesion());
    }

    public static void getLectura(String origen) throws ClassNotFoundException, IOException {
        ObjectInputStream ois = null;
        try {
            FileInputStream fis = new FileInputStream(origen);
            ois = new ObjectInputStream(fis);
            while (true) {
                Pelicula p1 = (Pelicula) ois.readObject();
                p1.mostrarPelicula();
            }
        } catch (IOException io) {
            System.out.println(" ");
        } finally {
            ois.close();
            //TODO: plantear con autoclose
        }
    }

    public void setEscritura(String destino) throws IOException {
        ObjectOutputStream oos = null;
        try {
            FileOutputStream fos = new FileOutputStream(destino, true);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(new Pelicula(this.getTitulo(), this.getEstreno(), this.getDirector(), this.getDuracion(), this.getSinopsis(), this.getSesion()));
        } catch (IOException io) {
            System.out.println(" ");
        } finally {
            oos.close();
        }
    }
    
    public static void getLecturaSetEscritura(String origen, String destino) throws ClassNotFoundException, IOException {
        FileInputStream fis = new FileInputStream(origen);
        try (ObjectInputStream ois = new ObjectInputStream(fis)) {
            while (true) {
                Pelicula p1 = (Pelicula) ois.readObject();
                p1.setEscritura(destino);
            }
        } catch (IOException io) {
            System.out.println(" ");
        } 
    }

    //Getters y setters:
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

    public String getSesion() {
        return sesion;
    }

    public void setSesion(String sesion) {
        this.sesion = sesion;
    }

}
