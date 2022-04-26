package BuzonCorreoEjercicio;

/**
 *
 * @author jhald
 */
public class ProgramaPrincipal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        BuzonCorreo bc = new BuzonCorreo();
        LectorHilo hilo1 = new LectorHilo(bc, "Adrian");
        LectorHilo hilo2 = new LectorHilo(bc, "Sara");
       // LectorHilo hilo3 = new LectorHilo(bc, "Elena");
       // LectorHilo hilo4 = new LectorHilo(bc, "Ana");
        EscritorHilo escribe1 = new EscritorHilo("Charo", bc);
        EscritorHilo escribe2 = new EscritorHilo("Ramiro", bc);
       // EscritorHilo escribe3 = new EscritorHilo("Pepe", bc);
       // EscritorHilo escribe4 = new EscritorHilo("Maria", bc);

        hilo1.start();
        hilo2.start();
       // hilo3.start();
       // hilo4.start();
        escribe1.start();
        escribe2.start();
       // escribe3.start();
       // escribe4.start();

        try {
            hilo1.join();
            hilo2.join();
           // hilo3.join();
           // hilo4.join();
            escribe1.join();
            escribe2.join();
            //escribe3.join();
           // escribe4.join();

        } catch (InterruptedException e) {
        }

        System.out.println("\n\tFIN DE LA EJECUCIÓN");
    }

}
