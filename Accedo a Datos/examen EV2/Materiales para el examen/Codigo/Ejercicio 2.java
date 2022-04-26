/* ESTO LO TENÉIS QUE PEGAR EN EL MAIN PARA ACCEDER AL MENÚ */
/* Creo un menú para que el usuario elija lo que quiere hacer */
/* Muestro el menú en bucle hasta que le den a salir */
while(!opcion.equals("0")){
    /* Llamo al método que crea el menú */
    pintarMenu();
    opcion = entrada.next();
    switch(opcion){
        case "1":
            /* Muestra todos los nombres de la colección */

            break;
        case "2":
            /* Muestra los nombres de todos los profesores que imparten 3 asignaturas o menos */

            break;
        case "3":
            /* Muestra los nombres de todas las asignaturas que se imparten en primero */

            break;
        case "4":
            /* Los nombres de todas las asignaturas que tengan entre 40 y 70 horas lectivas */

            break;
        case "5":
            /* Muestra todos los datos de la profesora con dni 44444444-D */

            break;
        case "6":
            /* Muestra el nombre y los apellidos de los profesores que tienen una 'S' en sus apellidos */

            break;
        case "7":
            /* Muestra el dni de todos los profesores que imparten clases de 'Programacion' */

            break;
        case "8":
            /* Muestra el nombre y los apellidos de todos los profesores que imparten clases en alguna asignatura de segundo */

            break;
        case "9":
            /* Muestra el nombre de todas las asignaturas que imparte la profesora 'Cristina' */

            break;
        case "10":
            /* Muestra el nombre de cada profesor y el total de horas que tiene asignadas de clase */

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

/* METODO USADO EN EL MENÚ */
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

/* METODO USADO EN EL MENÚ */
/* Este metodo detiene la ejecución hasta que se pulse INTRO para que se vea bien la salida */
public static void espera(){
    /* Creo un nuevo Scanner limpio */
    Scanner ent = new Scanner(System.in);
    System.out.println("Pulsa INTRO para continuar");
    /* Detengo la ejecución hasta que pulse INTRO */
    ent.nextLine(); 
}