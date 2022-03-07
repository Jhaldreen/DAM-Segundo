
import java.sql.Date;
import java.util.Scanner;

/**
 *
 * @author Antonio
 */
public class Aerolinea {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in); //Sirve para recoger texto por consola
        int select = -1;
        boolean salir = false;
        BaseDatos bc1 = new BaseDatos();
        while (select != 0) {//mientras no sea 0

            try {// creamos el menu
                System.out.println("----------------------------------------------------\n"
                        + "Escoja una opción: \n"
                        + "0. Salir del programa\n"
                        + "1. Mostrar información general \n"
                        + "2. Mostrar información de los pasajeros \n"
                        + "3. Ver los pasajeros de un vuelo \n"
                        + "4. Insertar nuevo vuelo\n"
                        + "5. Borrar vuelo introducido previamente\n"
                        + "6. Convertir vuelos de fumadores a no fumadores \n"
                        + "----------------------------------------------------");
                //Recoger una variable por consola
                select = Integer.parseInt(sc.nextLine());

                //switch para el menu de opciones
                switch (select) {

                    case 0:
                        System.out.println("\nGracias por utilizar el programa. Que tenga un buen día");
                        break;
                    case 1:
                        System.out.println("Se estan consultando los vuelos");
                        bc1.mostrarInformacionGeneral();// nostraemos de la clase
                        //BaseDatos nuestros métodos
                        break;
                    case 2:
                        System.out.println("\nSe están consultando los Pasajeros\n");
                        bc1.mostrarInformacionPasajeros();
                        break;
                    case 3:
                        bc1.verPasajerosVuelo();
                        break;

                    case 4:
                        System.out.println("Inserte el codigo del vuelo");
                        String vuelo = sc.nextLine();
                        System.out.println("Inserte el destino");
                        String destino = sc.nextLine();
                        System.out.println("Inserte procedencia");
                        String procedencia = sc.nextLine();
                        System.out.println("Inserte la hora con este formato DD/MM/AAAA  1700");
                        String hora = sc.nextLine();//Se debría hacer con Date 
                        System.out.println("Inserte numero de pasajeros fumadores");
                        int fumador = sc.nextInt();
                        System.out.println("Inserte numero de pasajeros no fumadores");
                        int noFumador = sc.nextInt();
                        System.out.println("Inserte numero de pasajeros en clase turista");
                        int turista = sc.nextInt();
                        System.out.println("Inserte el numero de pasajeros en primera clase");
                        int primera = sc.nextInt();

                        bc1.insertarDatos(vuelo, hora, destino, procedencia,
                                 fumador, noFumador, turista, primera);
                        break;

                    case 5:
                        System.out.println("Introduzca numero de vuelo a borrar");
                        String borrar = sc.nextLine();
                        bc1.borrarVuelo(borrar);
                        break;

                    case 6:
                        bc1.convertir();
                        break;

                }

            } catch (Exception e) {
                System.out.println("error" + e);
            }

        }

    }

}
