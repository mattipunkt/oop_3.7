import java.lang.Math;
import java.lang.System;

/**
 * Diese Klasse nutzt die von @see Keygenerator generierten Schlüssel, um Nachrichten nach dem RSA Verfahren zu de-/encoden
 *
 * @author Florian Haßelbacher
 * @version 1.0
 * @since JDK 1.21
 */
public class KeyProcessor {

    /**
     * Konstruktor der Klasse. Wichtig um auf die decode/encode Methoden von außerhalb zuzugreifen
     */
    public KeyProcessor() {
    }

    /**
     * Diese Methode ermittelt das Zeilentrennzeichen, da sich dieses zwischen Linux und windows unterscheidet
     *
     * @return : Zeilentrennzeichen als String
     */
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

    /**
     * Diese Methode konvertiert einen String zu einem int Array, welches von der encode Methode verarbeitet wird
     *
     * @param text Der zu konvertierende text
     * @return konvertierter Text als int Array
     */
    private int[] textToInt(String text){
        int [] tempNum = new int[text.length()];
        for (int i = 0; i < text.length(); i++) {
                tempNum[i] = Character.getNumericValue(text.charAt(i));

        }
        return tempNum;
    }

    /**
     * Diese Methode konvertiert einen integer Wert zu einem String, welcher von der encode Methode genutzt wird
     * @param tempNum der zu konvertierende integer
     * @return kovertierten integer als String
     */
    private String intToText(int tempNum){
        return String.valueOf(tempNum);
    }

    /**
     * Diese Methode kovertiert einen Text mithilfe des privaten Schlüssels zu einer Reihen an Zahlen
     *
     * @param key der Schlüssel zur encodierung
     * @param pfad der Dateipfad, wo die Textdatei liegt
     */
    public void encode(int[] key, String pfad) {
        Text texter = new Text();
        String text = texter.readTextFromFile(pfad);
        int e = key[0];
        int g = key[1];
        int[] message = textToInt(text);
        String outputPath = pfad.replaceAll(".txt", "_rsa.txt");
        System.out.println("konvertierter Text");
        for (int i = 0; i < message.length; i++ ) {
            message[i] = ((int) Math.pow((message[i]),e) % (g));
            System.out.println(message[i]);
            texter.writeTextToFile(intToText(message[i]), outputPath);
        }
    }

    /**
     * Diese Methode nutzt den öffentlichen Schlüssel, um eine Menge an Zahlen zurück zu Text zu konvertieren
     *
     * @param key der Schlüssel zur decodierung
     * @param pfad der Pfad, an dem die Textdatei liegt
     */
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
        String outputPath = pfad.replaceAll(".txt", "_decode.txt");
        reader.writeTextToFile(convert.toString(), outputPath);
    }
}
