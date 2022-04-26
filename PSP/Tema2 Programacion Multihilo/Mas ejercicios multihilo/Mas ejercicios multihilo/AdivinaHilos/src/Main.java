public class Main {


    /*
    El juego consiste en multiples jugadores que por turno van intentado adivinar unn numero
    Tendremos un arbitro que controla que nadie se salte su turno e intente adivinar el número
    antes de que todos sus compañeros lo hayan intentado.
    Al final se mmostrará el ganador, el número que acertó y el numero total de intentos hasta acertar
    */
    public static void main(String[] args) {
         final int cantidadJugadores=2;
        Arbitro a=new Arbitro(5000,cantidadJugadores);
        Jugador[] jugadores=new Jugador[cantidadJugadores];

        for(int i=0;i<cantidadJugadores;i++){
            jugadores[i]=new Jugador("Jugador "+i,a);
            jugadores[i].start();
        }

    }
}
