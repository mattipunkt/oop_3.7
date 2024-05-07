import de.oop2024.util.UserInterface;
import javax.swing.JFileChooser;

public class Frontend {
    JFileChooser chooser;

    public Frontend() {
        this.chooser = new JFileChooser();
    }

    public void menu() {
        System.out.println("***** RSA VERSCHLÜSSELUNG ******");
        System.out.println("********** HAUPTMENÜ ***********");
        System.out.println(" - [1] Text verschlüsseln");
        System.out.println(" - [2] Text entschlüsseln");
        System.out.println(" - [3] Schlüsselgenerierung");
        int choice = UserInterface.in.requestChoice("Bitte auswählen:", "1", "2", "3");
        if (choice == 1) {
            encode();
        } else if (choice == 2) {
            decode();
        } else if (choice == 3) {
            generateKeys();
        } else {
            throw new RuntimeException("Wahl muss angegeben werden!");
        }
    }


    private void encode() {
        String path = "";
        int d = UserInterface.in.requestInt("Bitte gib den ersten Teil des Schlüssels ein: ");
        int e = UserInterface.in.requestInt("Bitte gib den zweiten Teil des Schlüssels ein: ");
        int[] key = {d, e};
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
        String path = "";
        int d = UserInterface.in.requestInt("Bitte gib den ersten Teil des Schlüssels ein: ");
        int e = UserInterface.in.requestInt("Bitte gib den zweiten Teil des Schlüssels ein: ");
        int[] key = {d, e};
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

    }

}
