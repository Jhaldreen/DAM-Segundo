/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PaquetePrincipal;

/**
 *
 * @author Antonio
 */
public class MainEjercicio1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
               Db4o db4o = null;
        try {
            db4o = new Db4o();
            System.out.println("1. Visualizar los jefes que tengan más de 55 años.\n");
            db4o.jefesMayores55();//llamamos al metodo
            System.out.println("----------------------------------------------------------------------------------------------\n");
            
            String nombre = "Miguel";// puedo poner cualquier otro de la base de datos
            System.out.println("2. Actualizando edad de "+nombre+"\n");    
            db4o.actualizarEdad(nombre);//llamamos al metodo con el nombre pasado como parametro
            System.out.println("----------------------------------------------------------------------------------------------\n");

            System.out.println("3. Borrar los jefes que llevan más de 6 años en la empresa.\n");
            db4o.borrarJefesAntiguos();//llamamos al metodo
            System.out.println("----------------------------------------------------------------------------------------------\n");

            System.out.println("4. Visualizar todos los jefes que quedan, "
                    + "incluidos sus hijos, que no han sido borrados anteriormente.\n");
            db4o.buscarTodos();//llamamos al metodo
            System.out.println("----------------------------------------------------------------------------------------------\n");
            
        } catch (Exception e) {
            System.err.print("Ha ocurrido un error al realizar la consulta");
            e.printStackTrace();
        } finally {
            if (db4o != null) {
                db4o.cerrarConexion();//se cierra la conexion
            }
        }
    }
    
}
