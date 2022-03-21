
public class Controlador {

    private char[] rell = null;
    private int posicion = 0;

    private boolean lleno = false;
    private boolean vacio = true;

    public Controlador(int l) {
        if (l > 1) {
            this.rell = new char[l];
        } else {
            System.out.println("Introducca numeros mas altos");
        }
    }

    public synchronized void anadir(char c) throws Exception {
        while (lleno) {
            wait();
        }
        vacio = false;
        lleno = this.posicion >= (this.rell.length - 1);
        if (!lleno) {
            this.rell[++this.posicion] = c;
        }
        notifyAll();
    }

    public synchronized char quitar() throws Exception {
        while (vacio) {
            wait();
        }
        lleno = false;
        vacio = this.posicion <= 1;
        char c = this.rell[this.posicion];
        this.posicion--;
        notifyAll();
        return c;
    }
}
