/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psp07;

import java.io.File;
import java.io.*;
import java.io.IOException;
import java.security.*;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author rakel
 */
public class Psp07 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {

            // ====================================             ENCRIPTAR MENSAJE
            // 1.- creo un texto que es el que voy a encriptar
            String mensajeOriginal = "Mensaje que es muy secreto";

            // 2.- crear una password propioa que sera la base de la encriptacion del exto original
            String miContrasenaParaCrearKeys = "mi clave maravillosa";

            // 3.- Creo un "generador de claves" de tipo AES
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            //      le doy un tamaño a la clave (en nuestro caso un clave de 128 bits)
            keyGenerator.init(128);
            //      paso mi clave a un array de bytres...
            byte[] miContrasenaArrayBytes = miContrasenaParaCrearKeys.getBytes();

            // 4.-  y creo una key AES con mi claveoriginal (cojo solo los bytes del 0 al 16)
            Key keyMaestra = new SecretKeySpec(miContrasenaArrayBytes, 0, 16, "AES");

            // 5.- Creo una maquina de cifrar, y le digo que es maquina de tipo  AES/ECB/PKCS5Padding  o similar
            Cipher maquina = Cipher.getInstance("Rijndael/ECB/PKCS5Padding");

            //     decimos que la maquina debe arrancarse usando nueestra key y que se va a usar para ENCRIPTAR 
            maquina.init(Cipher.ENCRYPT_MODE, keyMaestra);

            // 6.- Encriptar con la maquina
            byte[] mensajeCifrado = new byte[128];
                    // por fin, ENCRIPTA el mensaje original y lo devuelve como mensajeCifrado
            mensajeCifrado = maquina.doFinal(mensajeOriginal.getBytes());
          
            //     lo pasamois a un String para verlo por consola
            String mensajeCifradoEnString = new String(mensajeCifrado);
            System.out.println(mensajeCifradoEnString);

            
                 
            // ========================================             GUARDAR EN FICHERO 
            // Declarar el objeto de PrintWriter, la clase que usamos para escribir, e iniciallizarla a null
            FileOutputStream fos = null;
            try {
                // Instanciar los objetos con el nombre del fichero donde vamos a escribir
                fos = new FileOutputStream("fichero.cifrado");

                // usar los métodos de FileOutputStream para escribir. Puede escribir un byte, o array de bytes
                fos.write(mensajeCifrado);

            } catch (IOException e) {
                // Se necesita un catch que controle errores, con IOEXception vale para todos los errores
                System.out.println(e.getMessage());
            } finally {
                // Es FUNDAMENTAL CERRAR el objeto de la clase FileOutputStream, y lo hacemos en el FINALLY
                // Por si acaso, preguntamos si el objeto de FileOutputStream no es null...
                if (fos != null) {
                    // desgraciadamente, el propio close exige tener su propio try-catch
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
                       
            
            //  SEGUNDO ORDENADOR
            

            // ============================================  LEER EL FICHERO NUEVAMENTE 
            // preparamos un array vacio donde meteremos lo que lea el fichero
            File file = new File("fichero.cifrado");
            int tamanoclave = (int) file.length();     
            byte[] mensajeCifradoLeidoDelFichero = new byte[tamanoclave];

            // Declarar los objetos de FileInputStream
            // las clases que usamos para escribir, e inicializarlas a null
            FileInputStream fis = null;
            try {
                // Instanciar los objetos con el nombre del fichero donde vamos a leer
                fis = new FileInputStream(file);

                // leemos bytes o arrays de bytes
                fis.read(mensajeCifradoLeidoDelFichero);
                //   System.out.println(new String(mensajeCifradoLeidoDelFichero).trim());

            } catch (IOException e) {
                // Se necesita un catch que controle errores, con IOEXception vale para todos los errores
                System.out.println(e.getMessage());
            } finally {
                // Es FUNDAMENTAL CERRAR el objeto de la clase FileInputStream, y lo hacemos en el FINALLY
                // Por si acaso, preguntamos si el objeto de FileInputStream no es null...
                if (fis != null) {
                    // desgraciadamente, el propio close exige tener su propio try-catch
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            // ========================================  DESENCRIPTAR EL CONTENIDO DEL FICHERO
            // decimoa a la maquina que, usando la misma clave anterior, ahora debe DECRYPT (DESENCRIPTAR)
            maquina.init(Cipher.DECRYPT_MODE, keyMaestra);

            // le digo a la maquina quye desencripte el mensaje leido del fichero
            byte[] mensajeDesencripado = maquina.doFinal(mensajeCifradoLeidoDelFichero);

            //     lo pasamos a un String para verlo por consola
            String mensajeDescifrado = new String(mensajeDesencripado);
            System.out.println(mensajeDescifrado);

            
            
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Psp07.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(Psp07.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(Psp07.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(Psp07.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(Psp07.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
