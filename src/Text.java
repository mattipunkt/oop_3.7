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
}
