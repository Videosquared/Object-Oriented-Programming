public class OneTimePadEncipher {

    public static int charToInt(char l) {
        // Should convert a character to an integer, for example 'a' -> 0, 'b' -> 1
        int a = (int)(Character.toLowerCase(l) - 'a');
        return a;
    }

    public static char intToChar(int i) {
        // Should convert an integer to a character, for example 0 -> 'a', 1 -> 'b'
        // it should always return lower case char
        //char a = (char)('a' + ((i + 26 ) % 26));
        //return a;
        return (char) (((i + 26) % 26) + 'a');
    }

    public static boolean isAlpha(char c) {
        int t = charToInt(c);
        if (t >= 0 && t < 26) {
            return true;
        }
        return false;
    }

    public static String encipher(String original, String onetimepad) {
        if (original.length() > onetimepad.length()) {
            System.out.print("The length of the OTP is too short");
            return "";
        }

        String lcPlaintext = original.toLowerCase();
        String lcOnetimepad = onetimepad.toLowerCase();

        String newStr = "";
        for (int i = 0; i < lcPlaintext.length(); i++) {
            char o = lcPlaintext.charAt(i);
            char k = lcOnetimepad.charAt(i);

            if (isAlpha(o)) {
                newStr += intToChar(charToInt(o) + charToInt(k));
            }
            else {
                newStr += o;
            }
        }
        return newStr;
    }


    public static void main(String[] args) {
        String ciphertext = encipher("HELLO EVERYBODY", "MYSECRETKETMYSECRETKEY");
        System.out.print(ciphertext);
    }

}