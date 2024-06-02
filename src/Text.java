import java.lang.String;
import java.lang.StringBuilder;
import java.io.*;
import java.io.FileNotFoundException;

/**
 * Diese Klasse ist ein Wrapper um Text aus Dateien zu lesen und zu schreiben.
 */
public class Text {


    /**
     * Der Konstruktor initialisiert die Klasse
     */
    public Text() {

    }

    /**
     * Diese Klasse liest einen Text aus einer Datei ein
     * @param pfad Pfad zur Datei
     * @return Text aus der Datei als String
     */
    public static String readTextFromFile(String pfad) {
        try (BufferedReader br = new BufferedReader(new FileReader(pfad))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            return sb.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Diese Methode ist ein Wrapper für die Methode <code>writeText</code> um einen Text
     * in eine Datei zu schreiben..
     * @param text In die Datei zu schreibender Text
     * @param pfad Pfad, an dem die Datei liegt.
     */
    public static void writeTextToFile(String text, String pfad) {
        try {
            File myObj = new File(pfad);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
                writeText(text, pfad);
            } else {
                System.out.println("File already exists.");
                writeText(text, pfad);
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    /**
     * Diese Methode erfüllt den eigentlichen Job des Text schreibens.
     * @param text Text, der geschrieben werden soll.
     * @param pfad Pfad, an dem die Datei liegt
     * @throws FileNotFoundException Datei nicht gefunden
     */
    private static void writeText(String text, String pfad) throws FileNotFoundException {
        try (FileWriter fw = new FileWriter(pfad, true)) {
            fw.write(text);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
