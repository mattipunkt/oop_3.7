public class KeyGenerator {

    private int p;
    private int q;
    private int g;

    public KeyGenerator(int p, int q) {
        if (checkPrime(p)) {
            this.p = p;
        } else if (checkPrime(q)) {
            this.q = q;
        } else {
            System.out.println("Beide Zahlen müssen Primzahl sein!");
        }

    }

    public void generatePrivateKey() {
        g = p * q;
        int phi = (p-1)*(q-1);
        int e = findE(phi);
        int d = findD(e, phi);
        System.out.println("Der Öffentliche Schlüssel ist: {" + e + ", " + g +"}");
        System.out.println("Der Private Schlüssel ist: {" + d + ", " + g +"}");

    }

    public int ggT(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return ggT(b, a % b);
        }
    }


    public int findE(int f) {
        int e = 2;
        while (ggT(e, f) != 1) {
            e++;
        } return e;
    }

    public int findD(int e, int f) {
        int d = 2;
        while ((d*e) % f != 1) {
            d++;
        } return d;
    }

    public boolean checkPrime(int number) {
        for (int i = 2; i <= number; i++) {
            if (number % i == 0) {
                return false;
            } else {
                i++;
            }
        }
        return false;
    }
}

