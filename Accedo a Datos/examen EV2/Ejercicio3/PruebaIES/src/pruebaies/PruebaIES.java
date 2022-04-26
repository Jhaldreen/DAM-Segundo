/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebaies;

/**
 *
 * @author DIEGO
 */
public class PruebaIES {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /* Creo el objeto que maneja toda la ejecución */
        AccedeBD bean = new AccedeBD();
        /* Listo toda la informacion (Apartado 1) */
        bean.listadoProfesores();
        bean.listadoAsignaturas();
        bean.listadoMatriculas();
        /* Añado una nueva asignatura (Apartado 2) */
        bean.anadeAsignatura(10, "Asignatura extra", 3, 500);
        /* Modifico el nombre y los apellidos de un profesor (Apartado 3) */
        bean.modificaProfesor("Mi nombre", "Mis apellidos", "11111111-A");
        /* Modifico el profesor asignado a una asignatura (Apartado 4)*/
        bean.modificaMatricula("55555555-E", 7, "11111111-A");
        /* Borro una matricula (Apartado 5) */
        bean.borraMatricula("33333333-C", 9);
        /* Vuelvo a mostrar toda la informacion */
        bean.listadoProfesores();
        bean.listadoAsignaturas();
        bean.listadoMatriculas();
    }
    
}
