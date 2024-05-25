public class KeyGenerator {

    private int p;
    private int q;
    private int g;

    public KeyGenerator(int p, int q) {
        if (isPrime(p) && isPrime(q)) {
            if (p >= 11 && q >= 13) {
                this.p = p;
                this.q = q;
                this.g = p * q;
            } else if (p >= 13 && q >= 11) {
                this.p = p;
                this.q = q;
                this.g = p * q;
            } else {
                throw new RuntimeException("p und q müssen größer gleich 11 sein.");
            }
            // Test
        } else {
            throw new RuntimeException("Keine Primzahl gegeben!");
        }

    }

    public int[] generatePrivateKey() {
        int phi = (p-1)*(q-1);
        int e = findE(phi);
        int d = findD(e, phi);
        int[] privateKey = {d, g};
        // System.out.println("Der Private Schlüssel ist: {" + d + ", " + g +"}");
        return privateKey;
    }

    public int[] generatePublicKey() {
        int phi = (p-1)*(q-1);
        int e = findE(phi);
        int[] pubKey = {e, g};
        // System.out.println("Der Öffentliche Schlüssel ist: {" + e + ", " + g +"}");
        return pubKey;
    }

    private int ggT(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return ggT(b, a % b);
        }
    }

    private boolean isPrime(int n) {
        if (n <= 1)
            return false;
        for (int i = 2; i < n; i++)
            if (n % i == 0)
                return false;
        return true;

    }

    /**
     * Diese Methode setzt die Formel zur Ermittlung von e um
     * @param f Wert für phi aus generatePublicKey() oder generatePrivateKey()
     * @return errechneter Wert für e
     */
    private int findE(int f) {
        int e = 4;
        while (ggT(e, f) != 1) {
            e++;
        } return e;
    }

    /**
     * Diese Methode setzt die Formel zur Ermittlung von d um
     * @param e Wert für e aus generatePrivateKey()
     * @param phi Wert für phi aus generatePrivateKey()
     * @return errechneter Wert für d
     */
    private int findD(int e, int phi) {
        int d = 2;
        while ((d*e) % phi != 1) {
            d++;
        } return d;
    }

}


