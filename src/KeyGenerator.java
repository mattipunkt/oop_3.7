public class KeyGenerator {

    private int p;
    private int q;
    private int g;

    public KeyGenerator(int p, int q) {
        this.p = p;
        this.q = q;
    }

    public void generatePrivateKey() {
        g = p * q;
        int e = ggT(phi(g));
    }

    public int phi(int q) {
        return (p-1)*(q-1);
    }

    public int ggT(int phi) {
        int e = 0;

        return e;
    }
}
