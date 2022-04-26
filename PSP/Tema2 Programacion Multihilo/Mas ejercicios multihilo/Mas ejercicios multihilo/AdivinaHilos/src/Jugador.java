public class Jugador extends Thread{
    private String nombre;
    private Arbitro arbitro;
    public Jugador(String nombre,Arbitro a){
        this.nombre=nombre;
        this.arbitro=a;
    }


    @Override
    public void run() {
        while(!arbitro.jugada(this.nombre)){

        }
    }
}
