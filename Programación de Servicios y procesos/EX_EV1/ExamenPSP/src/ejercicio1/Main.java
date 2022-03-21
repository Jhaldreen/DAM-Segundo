package ejercicio1;

public class Main {
    public static void main(String[] args) {
      
        SalidaCarrera sc1 = new SalidaCarrera();    //Instanciamos un objeto SalidaCarrera que compartirán todos los hilos
        
        Corredor[] cc = new Corredor[15];       //array de corredores, dimensionado para 15
        
        for (int i=0;i<15;i++){
            cc[i] = new Corredor(i+1,sc1);      //creamos 15 corredores, almacenados en las 15 posiciones del array de corredores, de 0 a 14
        }                                       //el número identificador debe ser i+1 ya que comienza en 1
        
        Arbitro a=new Arbitro("Joaquín",sc1);// creamos arbitro, damos nombre y carrera compartida
        
        Thread ta= new Thread(a);           //hilo de arbitro
        Thread[] th = new Thread[16];  //Array de hilos para los corredores y arbitro
        
        for (int i=0;i<15;i++){
            th[i] = new Thread(cc[i]);      //cada elemento del array de corredores da lugar a un thread, que se almacena en la posición correspondiente en su array
        }      
        
        th[15]=ta; //almacenamos el hilo arbitro en el array
        
        for (int i=0;i<16;i++){
            th[i].start();      //arrancamos cada hilo
        }  

            for (int i=0;i<15;i++){
                try {
                    th[i].join();      //realizamos el join de todos los hilos de corredor para que se siga ejecutando el main cuando todos hayan terminado
                } catch (InterruptedException ex) {
                    java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }
            }
            
        System.out.println("FIN DE LA CARRERA");
    }
}
 
