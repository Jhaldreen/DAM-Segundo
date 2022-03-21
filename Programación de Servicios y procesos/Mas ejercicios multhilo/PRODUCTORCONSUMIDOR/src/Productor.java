
/**
 *
 * @author Antonio
 */
public class Productor extends Thread {

    private Controlador array;
    private int n;
    private int sleep;

    public Productor(Controlador c, int n, int s) {
        this.array = c;
        this.n = n;
        this.sleep = s;
    }

    public void run() {
        try {
            char c;
            for (int i = 0; i < n; i++) {
                c = (char) ('A' + i);
                array.anadir(c);
                System.out.println("Produje: " + c);
                sleep((sleep));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }
}
