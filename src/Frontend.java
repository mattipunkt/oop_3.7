import de.oop2024.util.UserInterface;

public class Frontend {

    public Frontend() {

    }

    public void menu() {
        System.out.println("***** RSA VERSCHLÜSSELUNG ******");
        System.out.println("********** HAUPTMENÜ ***********");
        System.out.println(" - [1] Text entschlüsseln");
        System.out.println(" - [2] Text verschlüsseln");
        System.out.println(" - [3] Schlüsselgenerierung");
        int r = UserInterface.in.requestChoice("Bitte eine Option wählen: ", "1", "2", "3");
        switch (r) {
            case 1:
            case 2:
            case 3:
        }
    }

}
