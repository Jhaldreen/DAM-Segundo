
public class Consumidor extends Thread {

    private Controlador array;
    private int n;
    private int sleep;

    public Consumidor(Controlador c, int n, int s) {
        this.array = c;
        this.n = n;
        this.sleep = s;
    }

    public void run() {

        try {
            char c;
            for (int i = 0; i < n; i++) {
                c = this.array.quitar();
                System.out.println("Consumi: " + c);
                sleep((sleep));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

}
