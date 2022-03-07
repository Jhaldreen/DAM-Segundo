/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio2;

/* Añado los import necesarios */
import java.util.Scanner;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultSequence;
import net.xqj.exist.ExistXQDataSource;

/**
 *
 * @author DIEGO
 */
public class Ejercicio2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws XQException {
        /* Establezco la conexion con la BD */
        /* Primero creo el objeto que establece los parametros de conexion */
        XQDataSource exqds = new ExistXQDataSource();
        /* Cargo los valores de mi BD para poder establecer la conexion, hay que cargarlos de uno en uno */
        /* Primero el nombre del servidor */
        exqds.setProperty("serverName", "localhost");
        /* Segundo el puerto */
        exqds.setProperty("port", "8080");
        /* Tercero establezco la conexion pasandole el usuario y la contraseña */
        XQConnection con = exqds.getConnection("admin", "admin");
        /* Creo un Scanner para capturar el teclado */
        Scanner entrada = new Scanner(System.in);
        /* Aquí guardare la opción elegida, lo inicializo a "" para que entre en el bucle */
        String opcion = "";
        String consulta = "";
        /* Creo un menú para que el usuario elija lo que quiere hacer */
        /* Muestro el menú en bucle hasta que le den a salir */
        while(!opcion.equals("0")){
            /* Llamo al método que crea el menú */
            pintarMenu();
            opcion = entrada.next();
            switch(opcion){
                case "1":
                    /* Muestra todos los nombres de la colección */
                    consulta = "collection(/IESBDXML)//nombre";
                    muestraDatos(consulta, con);
                    break;
                case "2":
                    /* Muestra los nombres de todos los profesores que imparten 3 asignaturas o menos */
                    consulta = "collection(/IESBDXML)//profesor[count(./asignaturas/asignatura)<=3]/nombre";
                    muestraDatos(consulta, con);
                    break;
                case "3":
                    /* Muestra los nombres de todas las asignaturas que se imparten en primero */
                    consulta = "collection(/IESBDXML)/asignaturas/asignatura[curso=1]/nombre";
                    muestraDatos(consulta, con);
                    break;
                case "4":
                    /* Los nombres de todas las asignaturas que tengan entre 40 y 70 horas lectivas */
                    consulta = "collection(/IESBDXML)/asignaturas/asignatura[horas>=40 and horas<=70]/nombre";
                    muestraDatos(consulta, con);
                    break;
                case "5":
                    /* Muestra todos los datos de la profesora con dni 44444444-D */
                    consulta = "collection(/IESBDXML)//profesor[@dni='44444444-D']";
                    muestraDatos(consulta, con);
                    break;
                case "6":
                    /* Muestra el nombre y los apellidos de los profesores que tienen una ‘S’ en sus apellidos */
                    consulta =  "for $pro in collection(/IESBDXML)//profesor\n" +
                                "where contains($pro/apellidos, 'S')\n" +
                                "return concat(data($pro/nombre), ' ', data($pro/apellidos))";
                    muestraDatos(consulta, con);
                    break;
                case "7":
                    /* Muestra el dni de todos los profesores que imparten clases de “Programacion” */
                    consulta =  "for $pro in collection(/IESBDXML)//profesor[.//asignatura=collection(/IESBDXML)/asignaturas/asignatura[nombre='Programacion']/@id]\n" +
                                "return data($pro/@dni)";
                    muestraDatos(consulta, con);
                    break;
                case "8":
                    /* Muestra el nombre y los apellidos de todos los profesores que imparten clases en alguna asignatura de segundo */
                    consulta =  "for $pro in collection(/IESBDXML)//profesor[.//asignatura=collection(/IESBDXML)/asignaturas/asignatura[curso=2]/@id]\n" +
                                "return \n" +
                                "    <profesor>\n" +
                                "        {$pro/nombre}\n" +
                                "        {$pro/apellidos} \n" +
                                "    </profesor>";
                    muestraDatos(consulta, con);
                    break;
                case "9":
                    /* Muestra el nombre de todas las asignaturas que imparte la profesora “Cristina” */
                    consulta =  "for $pro in collection(/IESBDXML)//profesor[nombre='Cristina']\n" +
                                "return \n" +
                                "    <Cristina>\n" +
                                "        {\n" +
                                "            for $asi in collection(/IESBDXML)/asignaturas/asignatura[@id=$pro//asignatura]\n" +
                                "            return $asi/nombre\n" +
                                "        }\n" +
                                "    </Cristina>";
                    muestraDatos(consulta, con);
                    break;
                case "10":
                    /* Muestra el nombre de cada profesor y el total de horas que tiene asignadas de clase */
                    consulta =  "for $pro in collection(/IESBDXML)//profesor\n" +
                                "return \n" +
                                "    <profesor>\n" +
                                "        {$pro/nombre}\n" +
                                "        {\n" +
                                "            let $asi := collection(/IESBDXML)/asignaturas/asignatura[@id=$pro//asignatura]\n" +
                                "            return <total_horas> {sum($asi/horas)} </total_horas> \n" +
                                "        } \n" +
                                "    </profesor>";
                    muestraDatos(consulta, con);
                    break;
                case "0":
                    /* Salir */
                    System.out.println("Hasta otra");
                    break;
                default:
                    System.out.println("Opción no valida");
            }
            /* Llamo al método que espera un INTRO en todas las opciones salvo en la de salir */
            if (!opcion.equals("0")) espera();
        }
      
        /* Cierro la conexion antes de salir */
        con.close();
    }
    
    /* Creo el metodo que me pinta el menú con las opciones */
    public static void pintarMenu(){
        System.out.println("*****************************************************************************************************************");
        System.out.println("1. Muestra todos los nombres de la colección.");
        System.out.println("2. Muestra los nombres de todos los profesores que imparten 3 asignaturas o menos.");
        System.out.println("3. Muestra los nombres de todas las asignaturas que se imparten en primero.");
        System.out.println("4. Los nombres de todas las asignaturas que tengan entre 40 y 70 horas lectivas.");
        System.out.println("5. Muestra todos los datos de la profesora con dni 44444444-D.");
        System.out.println("6. Muestra el nombre y los apellidos de los profesores que tienen una ‘S’ en sus apellidos.");
        System.out.println("7. Muestra el dni de todos los profesores que imparten clases de 'Programacion'.");
        System.out.println("8. Muestra el nombre y los apellidos de todos los profesores que imparten clases en alguna asignatura de segundo.");
        System.out.println("9. Muestra el nombre de todas las asignaturas que imparte la profesora 'Cristina'.");
        System.out.println("10. Muestra el nombre de cada profesor y el total de horas que tiene asignadas de clase.");
        System.out.println("0. Salir");
        System.out.println("*****************************************************************************************************************");
        System.out.println("Seleccione un opción: ");
    }
    
    /* Este metodo detiene la ejecución hasta que se pulse INTRO para que se vea bien la salida */
    public static void espera(){
        /* Creo un nuevo Scanner limpio */
        Scanner ent = new Scanner(System.in);
        System.out.println("Pulsa INTRO para continuar");
        /* Detengo la ejecución hasta que pulse INTRO */
        ent.nextLine(); 
    }
    
    /* Creo el metodo que voy a invocar para mostrar los resultados de cada consulta */
    public static void muestraDatos(String consulta, XQConnection con) throws XQException {
        /* Preparo el objeto pasandole la consulta creada previamente */
        XQPreparedExpression precon = con.prepareExpression(consulta);
        /* La ejecuto y guardo los resultados de la misma */
        XQResultSequence salida = precon.executeQuery();
        /* Cojo los datos extraidos en la consulta los recorro y los voy mostrando */
        while(salida.next()){
            /* Saco la información de cada item por pantalla */
            System.out.println(salida.getItemAsString(null));
        }
    }
    
}
