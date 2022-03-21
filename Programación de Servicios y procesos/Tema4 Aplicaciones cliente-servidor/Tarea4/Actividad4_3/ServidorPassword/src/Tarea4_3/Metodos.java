/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tarea4_3;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Antonio
 */
public class Metodos {
    
    public static boolean ValidarUsuario(ArrayList<Usuarios> lista, String usuario, String contraseña) {
        boolean valido = false;
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getUsuario().equals(usuario) && lista.get(i).getContraseña().equals(contraseña)) {
                valido = true;
                break;
            }
        }
        return valido;
    }

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
