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
     * Extra klasse, die modulo und pow operationen vereinigt, um die Zahlen bei der Umwandlung kleiner zu halten
     * @param base Basis für die Exponentialrechnung
     * @param exp Exponent der Exponentialrechnung
     * @param mod Zahl, mit der die Modulo rechnung durchgeführt wird
     * @return Ergebnis der Rechnung
     */
    public static int modPow(int base, int exp, int mod) {
        int result = 1;
        for (int i = 0; i < exp; i++) {
            result = (result * base) % mod;
        }
        return result;
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
                tempNum[i] = (int) (text.charAt(i));

        }
        return tempNum;
    }

    /**
     * Diese Methode konvertiert einen integer Wert zu einem String, welcher von der encode Methode genutzt wird
     * @param tempNum der zu konvertierende integer
     * @return kovertierten integer als String
     */
    private char intToText(int tempNum){
        return (char) tempNum;
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
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < message.length; i++ ) {
            message[i] = ((int) modPow((message[i]),e, g));

            sb.append(Integer.valueOf(message[i]));
            sb.append("\n");
        }
        texter.writeTextToFile(sb.toString(), outputPath);
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
        int c = 0;
        while (c < inputText.length()) {
            while (inputText.charAt(c) != '\n') {
                ++c;
            }
        }

        for (int i = 0; i < inputText.length(); i++) {
            int temp = Integer.valueOf(inputText.charAt(i));
            temp = ((int) Math.pow(temp,d)) % g;
            convert.append(Character.toString((char) temp));
        }
        String outputPath = pfad.replaceAll(".txt", "_decode.txt");
        reader.writeTextToFile(convert.toString(), outputPath);
    }
}
