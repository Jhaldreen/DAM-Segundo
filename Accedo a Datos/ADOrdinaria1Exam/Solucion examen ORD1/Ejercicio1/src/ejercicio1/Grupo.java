/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio1;

/* Voy añadiendo los import que necesito */
import java.util.ArrayList;

/**
 *
 * @author DIEGO
 */
public class Grupo {
    /* Creo los atributos de la clase */
    String nombre;
    String estilo;
    ArrayList<Componente> componentes;
    ArrayList<Disco> discos;
    
    /* Añado un constructor vacio */
    public Grupo(){}
    
    /* Añado el constructor con todos los datos */
    public Grupo(String nom, String est, ArrayList<Componente> com, ArrayList<Disco> dis){
        this.nombre = nom;
        this.estilo = est;
        this.componentes = com;
        this.discos = dis;
    }

    /* Añado los getter y setter */
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    public ArrayList<Componente> getComponentes() {
        return componentes;
    }

    public void setComponentes(ArrayList<Componente> componentes) {
        this.componentes = componentes;
    }

    public ArrayList<Disco> getDiscos() {
        return discos;
    }

    public void setDiscos(ArrayList<Disco> discos) {
        this.discos = discos;
    }
    
    /* Creo un metodo que me permita generar todos los datos que hay que meter en el XML */
    public ArrayList<Grupo> cargarDatos(){
        /* Creo el ArryList con todos los datos para el documento */
        ArrayList<Grupo> datos = new ArrayList();
        
        /* Cargo los componentes del primer grupo */
        ArrayList<Componente> componentes1 = new ArrayList();
        componentes1.add(new Componente("Voz y Guitarra", "James Hetfield"));
        componentes1.add(new Componente("Bateria", "Lars Ulrich"));
        componentes1.add(new Componente("Bajo", "Robert Trujillo"));
        componentes1.add(new Componente("Guitarra", "Kirk Hammett"));
        ArrayList<Disco> discos1 = new ArrayList();
        /* Cargo los disco del primer grupo */
        discos1.add(new Disco("Kill'Em All", 1));
        discos1.add(new Disco("Ride The Lightning", 2));
        discos1.add(new Disco("Master of Puppets", 3));
        discos1.add(new Disco("...And Justice For All", 4));
        discos1.add(new Disco("Metallica", 5));
        /* Cargo los datos completos del primer grupo */
        datos.add(new Grupo("Metallica", "Trash Metal", componentes1, discos1));
        
        /* Cargo los componentes del segundo grupo */
        ArrayList<Componente> componentes2 = new ArrayList();
        componentes2.add(new Componente("Voz", "Eddie Vedder"));
        componentes2.add(new Componente("Guitarra", "Mike McCread"));
        componentes2.add(new Componente("Guitarra", "Stone Gossard"));
        componentes2.add(new Componente("Bateria", "Matt Cameron"));
        componentes2.add(new Componente("Bajo", "Jeff Ament"));
        componentes2.add(new Componente("Teclado", "Kenneth Gaspar"));        
        ArrayList<Disco> discos2 = new ArrayList();
        /* Cargo los disco del segundo grupo */
        discos2.add(new Disco("Ten", 1));
        discos2.add(new Disco("Vs.", 2));
        discos2.add(new Disco("Vitalogy", 3));
        /* Cargo los datos completos del segundo grupo */
        datos.add(new Grupo("Pearl Jam", "RocK'n'Roll", componentes2, discos2));        
        
        /* Cargo los componentes del tercer grupo */
        ArrayList<Componente> componentes3 = new ArrayList();
        componentes3.add(new Componente("Voz", "Andreas Lutz"));
        componentes3.add(new Componente("Bajo", "Pepe Bao"));
        componentes3.add(new Componente("Guitarra", "Javi Lynch Marssiano"));
        ArrayList<Disco> discos3 = new ArrayList();
        /* Cargo los disco del tercer grupo */
        discos3.add(new Disco("O'Funk'illo", 1));
        discos3.add(new Disco("En el Planeta Aseituna", 2));
        discos3.add(new Disco("No te cabe na'", 3));
        discos3.add(new Disco("Sesion Golfa", 4));
        discos3.add(new Disco("5mentario", 5));
        /* Cargo los datos completos del tercer grupo */
        datos.add(new Grupo("O'Funk'illo", "Funk Rock", componentes3, discos3));
        
        /* Devuelvo el ArrayList con todos los datos para el documento */
        return datos;
    }
    
}
