import de.oop2024.util.UserInterface;
import javax.swing.JFileChooser;
import java.util.Arrays;

public class Frontend {
    JFileChooser chooser;

    public Frontend() {
        this.chooser = new JFileChooser();
    }

    public void menu() {
        boolean run = true;
        while (run) {
            System.out.println("***** RSA VERSCHLÜSSELUNG ******");
            System.out.println("********** HAUPTMENÜ ***********");
            System.out.println(" - [1] Text verschlüsseln");
            System.out.println(" - [2] Text entschlüsseln");
            System.out.println(" - [3] Schlüsselgenerierung");
            System.out.println(" - [4] Beenden");
            int choice = UserInterface.in.requestInt("Bitte die wählen", 1, 4);
            if (choice == 1) {
                encode();
            } else if (choice == 2) {
                decode();
            } else if (choice == 3) {
                generateKeys();
            } else if (choice == 4) {
                run = false;
            } else {
                throw new RuntimeException("Wahl muss angegeben werden!");
            }
        }
    }


    private void encode() {
        System.out.println("*=* TEXT VERSCHLÜSSELN *=*");
        String path = "";
        System.out.println("Bitte den öffentlichen Schlüssel eingeben: ");
        int e = UserInterface.in.requestInt("Erste Zahl: ");
        int g = UserInterface.in.requestInt("Zweite Zahl: ");
        //KeyGenerator x = new KeyGenerator(e,g);
        int[] key = {e,g};
        KeyProcessor k = new KeyProcessor();
        int returnValue = chooser.showOpenDialog(null);
        if(returnValue == JFileChooser.APPROVE_OPTION)
        {
            path = chooser.getSelectedFile().getAbsolutePath();
            System.out.println("Die zu öffnende Datei ist: " +
                    chooser.getSelectedFile().getName());
        }
        k.encode(key, path);
    }

    private void decode() {
        System.out.println("*=* TEXT ENTSCHLÜSSELN *=*");
        String path = "";
        System.out.println("Bitte den privaten Schlüssel eingeben: ");
        int d = UserInterface.in.requestInt("Zahl 1: ");
        int g = UserInterface.in.requestInt("Zahl 2: ");
        //KeyGenerator x = new KeyGenerator(d,g);
        int[] key = {d,g};
        KeyProcessor k = new KeyProcessor();
        int returnValue = chooser.showOpenDialog(null);
        if(returnValue == JFileChooser.APPROVE_OPTION)
        {
            path = chooser.getSelectedFile().getAbsolutePath();
            System.out.println("Die zu öffnende Datei ist: " +
                    chooser.getSelectedFile().getName());
        }
        k.decode(key, path);
    }

    private void generateKeys() {
        int p = UserInterface.in.requestInt("Bitte Primzahl 1 eingeben: ");
        int q = UserInterface.in.requestInt("Bitte Primzahl 2 eingeben: ");
        KeyGenerator k = new KeyGenerator(p, q);
        System.out.println("Der öffentliche Schlüssel ist: " + Arrays.toString(k.generatePublicKey()));
        System.out.println("Der private Schlüssel ist: " + Arrays.toString(k.generatePrivateKey()));

    }

}
