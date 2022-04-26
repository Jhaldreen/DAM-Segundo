/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebaies;

/* Voy añadiendo los import que necesito */
import ProfesoresBean.ProfesoresBean;
import AsignaturasBean.AsignaturasBean;
import MatriculasBean.MatriculasBean;

/**
 *
 * @author DIEGO
 */
public class AccedeBD {
    /* Creo los atributos para poder manejar los componentes que he creado */
    ProfesoresBean profesores;
    AsignaturasBean asignaturas;
    MatriculasBean matriculas;
    
    /* Creo el constructor */
    public AccedeBD(){
        profesores = new ProfesoresBean();
        asignaturas = new AsignaturasBean();
        matriculas = new MatriculasBean();
    }
    
    /* Creo el metodo que muestra todos los profesores (Apartado 1) */
    public void listadoProfesores(){
        /* Cargo todos los registros de la BD */
        profesores.recargarDatos();
        System.out.println("********** Inicio de la consulta de profesores **********" + "\n");
        /* Recorro el vector y voy mostrando los datos */
        for(int i = 0; i < profesores.numProfesores(); i++){
            /* Me voy posicionando en cada registro */
            profesores.seleccionarFila(i);
            System.out.println("Nombre: " + profesores.getNombre());
            System.out.println("Apellidos: " + profesores.getApellidos());
            System.out.println("DNI: " + profesores.getDni());
            System.out.println("Telefono : " + profesores.getTelefono());
            System.out.println("Número de asignaturas: " + profesores.getNumAsignaturas() + "\n");
        }
        System.out.println("**********  Fin de la consulta de profesores  **********" + "\n");
    }
    
    /* Creo el metodo que muestra todos los asignaturas (Apartado 1) */
    public void listadoAsignaturas(){
        /* Cargo todos los registros de la BD */
        asignaturas.recargarDatos();
        System.out.println("********** Inicio de la consulta de asignaturas **********" + "\n");
        /* Recorro el vector y voy mostrando los datos */
        for(int i = 0; i < asignaturas.numAsignaturas(); i++){
            /* Me voy posicionando en cada registro */
            asignaturas.seleccionarFila(i);
            System.out.println("ID: " + asignaturas.getId());
            System.out.println("Nombre: " + asignaturas.getNombre());
            System.out.println("Curso: " + asignaturas.getCurso());
            System.out.println("Horas: " + asignaturas.getHoras() + "\n");
        }
        System.out.println("**********  Fin de la consulta de asignaturas  **********" + "\n");
    }
    
    /* Creo el metodo que muestra todos los matriculas (Apartado 1) */
    public void listadoMatriculas(){
        /* Cargo todos los registros de la BD */
        matriculas.recargarDatos();
        System.out.println("********** Inicio de la consulta de matriculas **********" + "\n");
        /* Recorro el vector y voy mostrando los datos */
        for(int i = 0; i < matriculas.numMatriculas(); i++){
            /* Me voy posicionando en cada registro */
            matriculas.seleccionarFila(i);
            System.out.println("Profesor: " + matriculas.getProfesor());
            System.out.println("Asignatura: " + matriculas.getAsignatura() + "\n");
        }
        System.out.println("**********  Fin de la consulta de matriculas  **********" + "\n");
    }
    
    /* Creo el metodo que me permite añadir registros de asignatura nuevos (Apartado 2) */
    public void anadeAsignatura(int idA, String noA, int cuA, int hoA){
        /* Cargo los nuevos datos */
        asignaturas.setId(idA);
        asignaturas.setNombre(noA);
        asignaturas.setCurso(cuA);
        asignaturas.setHoras(hoA);
        /* Los añado en la BD */
        asignaturas.anadeAsignatura();
    }
    
    /* Creo el metodo que me permite modificar un registro de profesor y cambiarle el nombre y los apellidos (Apartado 3) */
    public void modificaProfesor(String noP, String apP, String dniP){
        /* Cargo los nuevos datos */
        profesores.setDni(dniP);
        /* Los añado en la BD */
        profesores.modificaProfesor(noP, apP);
    }
    
    /* Creo el metodo que me permite modificar un registro de matricula y asignarle otro profesor (Apartado 4) */
    public void modificaMatricula(String dniPA, int idA, String dniPN){
        /* Cargo los nuevos datos */
        matriculas.setProfesor(dniPA);
        matriculas.setAsignatura(idA);
        /* Los añado en la BD */
        matriculas.modificaMatricula(dniPN);
    }
    
    /* Creo el metodo que me permite borrar un registro de matricula (Apartado 5) */
    public void borraMatricula(String dniP, int idA){
        /* Cargo los nuevos datos */
        matriculas.setProfesor(dniP);
        matriculas.setAsignatura(idA);
        /* Los añado en la BD */
        matriculas.borraMatricula();
    }
    
}
