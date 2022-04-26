import java.util.HashMap;
import java.util.Random;
import java.util.Stack;

public class Arbitro {
    private int numero; //numero a adivinar
    private int limiteMaximo; //expresa el rango del numero a adivinar. Por ejemplo: 0 a 500
    private int nJugadores=0; //representa el número de jugadores que están jugando

    private HashMap<String,Integer> jugadores; //guarda el numero de juagdas que lleba cada jugador

    private Stack<String> pila=new Stack<String>(); //lleva información del turno. Mientras el jugador está en la pila no llega su turno

    private boolean finPartida=false;  //indica el estado de la partida

    public Arbitro(int limiteMaximo,int nJugadores){
        Random r=new Random();
        this.limiteMaximo=limiteMaximo;
        this.numero=r.nextInt(limiteMaximo);

        this.jugadores=new HashMap<String,Integer>();

    }

    public synchronized boolean jugada(String nombreJugador){

        if(pila.contains("nombreJugador")){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else{
            Random a =new Random();
            if(!jugadores.containsKey(nombreJugador)){
                jugadores.put(nombreJugador,1);
            }else{
                jugadores.put(nombreJugador,jugadores.get(nombreJugador)+1);

            }
            if(this.numero==a.nextInt(this.limiteMaximo)){
                finPartida=true;
                System.out.println(nombreJugador+" acertó el numero "+this.numero+" al "+jugadores.get(nombreJugador)+" intento");

            }

            if(pila.size()==this.nJugadores){// todos han cumplido un turno
                pila.clear(); //vacio la pila
            }


            notifyAll();

        }
        return finPartida;



    }

}
