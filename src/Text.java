import java.lang.String;
import java.lang.StringBuilder;
import java.io.*;
import java.io.FileNotFoundException;

public class Text {


    public Text() {

    }

    public String readTextFromFile(String pfad) throws FileNotFoundException {
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


    public void writeTextToFile(String text, String pfad) {
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

    private void writeText(String text, String pfad) throws FileNotFoundException {
        try (PrintWriter pw = new PrintWriter(pfad)) {
            pw.println(text);
        }
    }
}
