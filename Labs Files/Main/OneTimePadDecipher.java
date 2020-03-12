public class OneTimePadDecipher {
    public static String decipher(String encipheredText, String onetimepad) {
        String a = encipheredText.toLowerCase();
        String b = onetimepad.toLowerCase();
        String newStr = "";
        String output ="";

        for (int i = 0; i < encipheredText.length(); i++) {
            int x = OneTimePadEncipher.charToInt(a.charAt(i));
            int y = OneTimePadEncipher.charToInt(b.charAt(i));

            int z = (((x - y) + 26) % 26);
            if (OneTimePadEncipher.isAlpha(a.charAt(i))) {
                newStr += OneTimePadEncipher.intToChar(z);
            }
            else {
                newStr += a.charAt(i);
            }

        }
        output = newStr.toUpperCase();
        return output;
    }

    public static void main(String[] args){
        String x = "wconlahzgzgleuai";
        String y = "YOULLNEVERREADMYONETIMEPAD";

        System.out.println(decipher(x, y));
    }
}
