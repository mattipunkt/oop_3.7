public class KeyGenerator {

    private int p;
    private int q;
    private int g;

    public KeyGenerator(int p, int q) {
        if (isPrime(p) && isPrime(q)) {
            this.p = p;
            this.q = q;
            this.g = p * q;
        } else {
            throw new RuntimeException("Keine Primzahl gegeben!");
        }

    }

    public int[] generatePrivateKey() {
        int phi = (p-1)*(q-1);
        int e = findE(phi);
        int d = findD(e, phi);
        int[] privateKey = {d, g};
        System.out.println("Der Private Schlüssel ist: {" + d + ", " + g +"}");
        return privateKey;
    }
    public int[] generatePublicKey() {
        int phi = (p-1)*(q-1);
        int e = findE(phi);
        int[] pubKey = new int[]{e, g};
        System.out.println("Der Öffentliche Schlüssel ist: {" + e + ", " + g +"}");
        return pubKey;
    }

    public int ggT(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return ggT(b, a % b);
        }
    }

    public boolean isPrime(int n) {
        if (n <= 1)
            return false;
        for (int i = 2; i < n; i++)
            if (n % i == 0)
                return false;
        return true;

    }


    public int findE(int f) {
        int e = 4;
        while (ggT(e, f) != 1) {
            e++;
        } return e;
    }

    public int findD(int e, int phi) {
        int d = 2;
        while ((d*e) % phi != 1) {
            d++;
        } return d;
    }

}


