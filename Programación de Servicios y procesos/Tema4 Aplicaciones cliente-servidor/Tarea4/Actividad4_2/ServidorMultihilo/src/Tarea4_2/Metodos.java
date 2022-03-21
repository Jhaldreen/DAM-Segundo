/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tarea4_2;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Antonio
 */
public class Metodos {
    //Dado un directorio almacena que una tabla el nombre
    // de todos los archivos

    public static String[] cargarDirectorio(String ruta) {
        //Le pongo una variable ruta para especificar el fichero a abrir
        File fichero = new File(ruta);
        File[] ficheros = null;
        String[] tablaNombres = null;

        ficheros = fichero.listFiles();
        tablaNombres = new String[ficheros.length];
        for (int i = 0; i < ficheros.length; i++) {
            tablaNombres[i] = ficheros[i].getName();

        }
        return tablaNombres;

    }

    public static String [] nombreArchivo(String ruta) {
        //Le pongo una variable ruta para especificar el fichero a abrir
        File fichero = new File(ruta);//le paso la ruta
        File[] ficheros = null;
        String[] tablaNombres = null;//para sacar el nombre del a solicitar
        
        ficheros = fichero.listFiles();
        tablaNombres = new String [ficheros.length];//listo los archivos
        for (int i = 0; i < ficheros.length; i++) {
            tablaNombres[i] =ficheros[i].getName();
            
        }   
        return tablaNombres;
        
    }

}
