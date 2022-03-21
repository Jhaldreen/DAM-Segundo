/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio2;

/* Añado los import necesarios */
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
        /* Creo la variable que voy a usar para ir almancenando las consultas */
        String consulta = "";
        /* Apartado 1 */
        System.out.println("********** Apartado 1 **********");
        /* Muestra el nombre de todos los postres que no tengan huevo */
        consulta = "collection('/MENUBDXML')//postre[not(.//ingrediente/@nombre='huevo')]/nombre";
        muestraDatos(consulta, con);
        /* Apartado 2 */
        System.out.println("\n********** Apartado 2 **********");
        /* Muestra el nombre de todos los ingredientes del arroz tres delicias */
        consulta = "collection('/MENUBDXML')//primero[nombre='arroz tres delicias']//ingrediente/@nombre/data()";
        muestraDatos(consulta, con);
        /* Apartado 3 */
        System.out.println("\n********** Apartado 3 **********");
        /* Muestra todos los datos del segundo plato del menú 1 */
        consulta = "collection('/MENUBDXML')//segundo[@id=collection('/MENUBDXML')//menu[@id=1]/plato[@tipo='segundo']/@id]";
        muestraDatos(consulta, con);
        /* Apartado 4 */
        System.out.println("\n********** Apartado 4 **********");
        /* Muestra el nombre y el total de la cantidad de todos los ingredientes de los primeros platos,
           con un total de cantidad entre 100 y 200. Ordenados de mayor a menor */
        consulta =  "for $pri in collection('/MENUBDXML')/primeros/primero\n" +
                    "where sum($pri//cantidad) > 100 and sum($pri//cantidad) < 200\n" +
                    "order by sum($pri//cantidad) descending\n" +
                    "return \n" +
                    "    <plato>\n" +
                    "        {$pri/nombre}\n" +
                    "        <total>{sum($pri//cantidad)}</total>\n" +
                    "    </plato>";
        muestraDatos(consulta, con);
        /* Apartado 5 */
        System.out.println("\n********** Apartado 5 **********");
        /* Muestra todos los menús disponibles, pero mostrando los nombres de cada plato */
        consulta =  "for $menu in collection('/MENUBDXML')//menu\n" +
                    "return\n" +
                    "    <menu>\n" +
                    "        <primero>{collection('/MENUBDXML')//primero[@id=$menu/plato[@tipo='primero']/@id]/nombre/data()}</primero>\n" +
                    "        <segundo>{collection('/MENUBDXML')//segundo[@id=$menu/plato[@tipo='segundo']/@id]/nombre/data()}</segundo>\n" +
                    "        <postre>{collection('/MENUBDXML')//postre[@id=$menu/plato[@tipo='postre']/@id]/nombre/data()}</postre>\n" +
                    "    </menu>";
        muestraDatos(consulta, con);
        System.out.println("\n********** FIN **********");
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
