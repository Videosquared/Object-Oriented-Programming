public class ErrorHandlingException {
    public static String lowerAndTrim(String a) {
        String b;
        if (a == null) {
            throw new NullPointerException();
        } else {
            b = (a.trim()).toLowerCase();
        }

        return b;
    }

    public static void formatText(String[] str) {
        int counter = 0;
        for (int i = 0; i < str.length; i++) {
            try {
                // all code within the try block is protected
                // by the corresponding catch
                System.out.println(lowerAndTrim(str[i]));

            } catch (NullPointerException error) {
                // the catch block is able to catch IllegalArgumentExceptions and
                // handles them by printing the provided error message.
                counter++;
            }
        }
        System.out.println(counter);
    }
}
