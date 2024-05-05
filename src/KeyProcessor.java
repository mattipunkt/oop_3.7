import java.lang.Math;
import java.lang.System;
public class KeyProcessor {
    private String determineLineSeperator() {
        String lineSeperator = "\n";
        String os = System.getProperty("os.name");
        if (os.contains("Win")) {
            lineSeperator = "\r\n";
        } else {
            lineSeperator = lineSeperator;
        }
        return lineSeperator;
    }

    public int[] textToInt(String text){
        int [] tempNum = new int[text.length()];
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' '){     // Leerzeichen müssen mit beachtet werden (vielleicht for char : String)

            }
            else {
                tempNum[i] = Character.getNumericValue(text.charAt(i));
            }
        }
        return tempNum;
    }
    public String intToText(int tempNum){
        return String.valueOf(tempNum);
    }
    public void encode(int[] key,String text) {
        Text write = new Text();
        int e = key[0];
        int g = key[1];
        int[] message = textToInt(text);
        System.out.println("konvertierter Text");
        for (int i : message) {
            message[i] = ((int) Math.pow((message[i]),e) % (g));
            System.out.println(message[i]);
            write.writeTextToFile(intToText(message[i]),"text.txt");
        }
    }

    public void decode() {
        Text reader = new Text();
        String inputText =  reader.readTextFromFile("text.txt");


    }
}
