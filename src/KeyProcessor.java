import java.lang.Math;
import java.lang.System;
public class KeyProcessor {

    public KeyProcessor() {
    }

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
                tempNum[i] = Character.getNumericValue(text.charAt(i));

        }
        return tempNum;
    }
    public String intToText(int tempNum){
        return String.valueOf(tempNum);
    }
    public void encode(int[] key, String pfad) {
        Text texter = new Text();
        String text = texter.readTextFromFile(pfad);
        int e = key[0];
        int g = key[1];
        int[] message = textToInt(text);
        String outputPath = pfad.replaceAll(".txt", "_rsa.txt");
        System.out.println("konvertierter Text");
        for (int i : message) {
            message[i] = ((int) Math.pow((message[i]),e) % (g));
            System.out.println(message[i]);
            texter.writeTextToFile(intToText(message[i]), outputPath);
        }
    }

    public void decode(int[] key, String pfad) {
        int g = key[1];
        int d = key[0];
        StringBuilder convert = new StringBuilder();
        Text reader = new Text();
        String inputText =  reader.readTextFromFile(pfad);
        for (int i = 0; i < inputText.length(); i++) {
            int temp = Integer.valueOf(inputText.charAt(i));
            temp = ((int) Math.pow(temp,d)) % g;
            convert.append(Character.toString((char) temp));
        }
        String outputPath = pfad.replaceAll(".txt", "_rsa.txt");
        reader.writeTextToFile(convert.toString(), outputPath);
    }
}
