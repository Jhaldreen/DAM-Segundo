
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author Antonio
 */
public class BaseDatos {

    Connection con;
    String url = "jdbc:mysql://localhost:3306/aerolinea";//indica la direccion del servidor
    ResultSet resul;// crear cursor para manjer salidas de las consultas

    public void mostrarInformacionGeneral() {

        try {
             //esta conexion no hace falta hacerla todo el rato con que la haga 
             //una vez al principio vale pero me he dado cuenta tarde 
            con = DriverManager.getConnection(url, "root", "");//establezco la conexion
            //DatabaseMetaData datos = con.getMetaData(); obtener informacion de la BD
            //System.out.println(datos.getUserName());//sacar el servidor
            Statement sentence = con.createStatement();
            resul = sentence.executeQuery("Select * from vuelos");
            // resul = datos.getTables(null,"Iberia", null, null);

            while (resul.next()) {//recorre las tablas y me dice las que hay
                System.out.println("\nCodigo: " + resul.getString(1) + " Fecha: " + resul.getString(2)
                        + " Procedencia: " + resul.getString(3) + " Destino: " + resul.getString(4));

            }

            con.close();// cerrar la operacion

        } catch (SQLException ex) {
            System.out.println("error" + ex);
        }

    }

    public void mostrarInformacionPasajeros() {

        try {

            con = DriverManager.getConnection(url, "root", "");//establezco la conexion
            Statement sentence = con.createStatement();
            // si pongo , vuelos me da la info de todos los vuelos también
            resul = sentence.executeQuery("Select * from pasajeros");

            // resul = datos.getTables(null,"Iberia", null, null);
            while (resul.next()) {//recorre las tablas y me dice las que hay
                System.out.println("Numero: "
                        + resul.getString(1) + " Vuelo: " + resul.getString(2)
                        + " Plaza: " + resul.getString(3) + " Fumador: " + resul.getString(4));

            }

            con.close();// cerrar la operacion

        } catch (SQLException ex) {
            System.out.println("error" + ex);
        }

    }

    public void verPasajerosVuelo() {

        try {

            con = DriverManager.getConnection(url, "root", "");//establezco la conexion
            Statement sentence = con.createStatement();
            Scanner usuario = new Scanner(System.in);
            System.out.println("¿De qué vuelo quieres ver los pasajeros? ");
            String vuelo = usuario.nextLine();
            String sql = "Select * from pasajeros WHERE COD_VUELO = '" + vuelo + "'";
            //System.out.println(sql);
            resul = sentence.executeQuery(sql);

            // resul = datos.getTables(null,"Iberia", null, null);
            while (resul.next()) {//recorre las tablas y me dice las que hay
                System.out.println("Numero: "
                        + resul.getString(1) + " Vuelo: " + resul.getString(2)
                        + " Plaza: " + resul.getString(3) + " Fumador: " + resul.getString(4));

            }
            con.close();// cerrar la operacion

        } catch (SQLException ex) {
            System.out.println("error" + ex);
        }

    }

    public void insertarDatos(String codVuelo, String hora, String destino,
            String procedencia, int fumador, int noFumador, int turista,
            int primera) {// variables para pasar despues
        // esta parte se pueden meter los datos del tiron sin tener que hacer tantos scanner 
        //en la clase principal solo hay que que poner las variables en INSERT
        // INSERT INTO vuelos VALUES ("'"+codVuelos+"'" etc etc )
        try {
            con = DriverManager.getConnection(url, "root", "");//establezco la conexion
            // creamos una variuable para meter el INSERT y se la pasamos al prepared statemen 
            String sql = " INSERT INTO vuelos VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement sentencia = con.prepareStatement(sql);
            //asignamos cada variable siendo la primera cifra el numero de columna
            //y despues la variable
            sentencia.setString(1, codVuelo);
            sentencia.setString(2, hora);
            sentencia.setString(3, destino);
            sentencia.setString(4, procedencia);
            sentencia.setInt(5, fumador);
            sentencia.setInt(6, noFumador);
            sentencia.setInt(7, turista);
            sentencia.setInt(8, primera);
            int filasDeVueltas = sentencia.executeUpdate();//ejecutamos las sentencias

            if (filasDeVueltas != 1) {// condicion para saber si se introduce o no el vuelo

                System.out.println("vuelo no introducido");

            } else {

                System.out.println("El vuelo se ha introducido");
            }
            sentencia.close();//cerrrar la sentencia
            con.close();//cerrar conexion

        } catch (SQLException ex) {
            System.out.println("Error" + ex);
        }

    }

    public void borrarVuelo(String vuelo) {

        try {
            // igual que el anterior pero borrando datos
            con = DriverManager.getConnection(url, "root", "");//establezco la conexion           
            String sql = "DELETE FROM Vuelos WHERE COD_VUELO = ?";
            PreparedStatement sentencia = con.prepareStatement(sql);
            sentencia.setString(1, vuelo);
            int filasDeVueltas = sentencia.executeUpdate();
            sentencia.close();
            if (filasDeVueltas != 1) {
                System.out.println("vuelo no borrado");
            } else {
                System.out.println("vuelo borrado");
            }

            con.close();// cerrar la operacion

        } catch (SQLException ex) {
            System.out.println("error" + ex);
        }

    }

    public void convertir() throws SQLException {

        try {
            //aqui hay que realizar dos actualizaciones se suman los no fumadores
            //a fumadores y luego se deja a 0 fumadores
            con = DriverManager.getConnection(url, "root", "");//establezco la conexion
            Statement sentence = con.createStatement();
            sentence.execute("update vuelos set PLAZAS_NO_FUMADOR=(PLAZAS_NO_FUMADOR+PLAZAS_FUMADOR)");
            sentence.execute("update vuelos set PLAZAS_FUMADOR=0");

        } catch (SQLException ex) {
            System.out.println("Error al modificar los fumadores " + ex.getMessage());
        } finally {
            con.close();
        }

    }
}
