public class SplitPathName {
    public static String[] splitPath(String s) {
        String[] output = new String[4];

        if (s.lastIndexOf("/") == -1 && s.lastIndexOf(".")== -1) {
            output[0] = "";
            output[1] = s;
            output[2] = s;
            output[3] = "";
        } else if (s.lastIndexOf("/") == -1 && s.lastIndexOf(".") != -1) {
            output[0] = "";
            output[1] = s;
            output[2] = s.substring(0, s.lastIndexOf("."));
            output[3] = s.substring(s.lastIndexOf("."));
        } else {
            output[0] = s.substring(0, s.lastIndexOf("/")+1);
            output[1] = s.substring(s.lastIndexOf("/")+1);
            output[2] = s.substring(s.lastIndexOf("/")+1, s.lastIndexOf("."));
            output[3] = s.substring(s.lastIndexOf("."));
        }

        /*for (int i = 0; i < 4; i++) {
            output[i] = "";
        }*/

        /*if (s.lastIndexOf("/") != -1 ) {
            output[1] = s.substring(s.lastIndexOf("/")+1);
            output[0] = s.substring(0, s.lastIndexOf("/")+1);
            output[2] = s.substring(s.lastIndexOf("/")+1, s.lastIndexOf("."));
        } else {
            output[0] = "";
            output[1] = s;
            output[2] = s.substring(0, s.lastIndexOf("."));
        }
        if (s.lastIndexOf(".") != -1 ) {
            output[3] = s.substring(s.lastIndexOf("."));
        } else {
            output[3] = "";
        }*/
        return output;
    }

    public static void main(String[] args) {

        for (int i = 0; i < args.length; i++) {
            String[] components = splitPath(args[i]);
            System.out.println("File: " + components[1] + " Type: " + components[3] + " [" + components[0] + "]");
        }

    }

}
