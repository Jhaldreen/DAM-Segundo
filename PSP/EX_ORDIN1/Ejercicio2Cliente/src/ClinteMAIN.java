
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Antonio
 */
public class ClinteMAIN {

    /**
     * @param args the command line arguments
     */
    public static final String HOST = "localhost";
    static final int Puerto = 6060;

    public static void main(String[] args) {
        
        //politica de solo escritura
        //         System.setProperty("java.security.policy", 
//                "./server.policy");
//        System.setSecurityManager(new SecurityManager());
      
        int estado = 1;
        Scanner sc = new Scanner(System.in);
        try {
            //creamos un socket con la direccion IP del servidor y el puerto al que queremos conectarnos
            Socket skCliente = new Socket(HOST, Puerto);
            // Creo los flujos de entrada y salida
            DataInputStream flujo_entrada = new DataInputStream(skCliente.getInputStream());
            DataOutputStream flujo_salida = new DataOutputStream(skCliente.getOutputStream());
            // TAREAS QUE REALIZA EL CLIENTE
            String mensaje = "";

            System.out.println(flujo_entrada.readUTF());//recibo del servidor
            flujo_salida.writeUTF("Hola soy el cliente");//envio al servidor
            //pedimos el mensaje
            System.out.println("Escriba el mensaje: Hola soy el cliente");
            mensaje = sc.nextLine();
            flujo_salida.writeUTF(mensaje);
            GenerarFirma();//generamos la firma
            System.out.println(mensaje);
            //recibo gracias por su visita
            System.out.println(flujo_entrada.readUTF());
            
            // Se cierra la conexiones
            flujo_entrada.close();
            flujo_salida.close();
            skCliente.close();//cerramos el socket

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static void GenerarFirma() {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA");
            //SE INICIALIZA EL GENERADOR DE CLAVES
            SecureRandom numero = SecureRandom.getInstance("SHA1PRNG");
            keyGen.initialize(1024, numero);//tamaño d ela clave 1024

            //SE CREA EL PAR DE CLAVES PRIVADA Y PÚBLICA
            KeyPair par = keyGen.generateKeyPair();
            PrivateKey clavepriv = par.getPrivate();
            PublicKey clavepub = par.getPublic();

            System.out.println("CLAVE PRIVADA: " + clavepriv + "\r\n - CLAVE PÚBLICA: " + clavepub);

            //FIRMA CON CLAVE PRIVADA EL MENSAJE
            //AL OBJETO Signature SE LE SUMINISTRAN LOS DATOS A FIRMAR
            Signature dsa = Signature.getInstance("SHA1withDSA");
            dsa.initSign(clavepriv);
            String mensaje = "Este mensaje va a ser firmado";
            dsa.update(mensaje.getBytes());

            byte[] firma = dsa.sign(); //MENSAJE FIRMADO

            //EL RECEPTOR DEL MENSAJE
            //VERIFICA CON LA CLAVE PÚBLICA EL MENSAJE FIRMADO
            //AL OBJETO signature SE LE SUMINIST. LOS DATOS A VERIFICAR
            Signature verificadsa = Signature.getInstance("SHA1withDSA");
            verificadsa.initVerify(clavepub);
            verificadsa.update(mensaje.getBytes());
            boolean check = verificadsa.verify(firma);
            if (check) {
                System.out.println("FIRMA VERIFICADA CON CLAVE PÚBLICA");
            } else {
                System.out.println("FIRMA NO VERIFICADA");
            }

        } catch (NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        }

    }

}
