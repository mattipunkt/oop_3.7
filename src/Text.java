import java.lang.String;
import java.lang.StringBuilder;
import java.io.*;
import java.io.FileNotFoundException;

public class Text {

    private String pfad;

    public Text(String pfad) {
        this.pfad = pfad;
    }

    private String readTextFromFile() throws FileNotFoundException {
        try {BufferedReader reader = new BufferedReader(new FileReader(pfad));
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
        return pfad;
    }
}
