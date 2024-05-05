public class KeyProcessor {

    public int[] textToInt(String text){
        int [] tempNum = new int[text.length()];
        for (int i = 0; i < text.length(); i++) {
            tempNum[i] = Character.getNumericValue(text.charAt(i));
        }
        return tempNum;
    }
    public String intToText(int[] tempNum){
        StringBuilder convert = new StringBuilder();
        for (int i = 0; i < tempNum.length; i++) {
            convert.append(tempNum[i]);
            convert.append(":");
        }
        String converted = convert.toString();
        return converted;
    }
    public void encode(String text) {

    }

    public void decode() {

    }
}
