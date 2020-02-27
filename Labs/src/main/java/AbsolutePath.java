public class AbsolutePath {
 /*   public static String ensureAbsolute(String path) {
        String myDirectory = System.getProperty("user.dir");
        //System.out.println(myDirectory);
        String output = "";

        if (path.charAt(0) == '/') {
            output = path;
        } else {
            output = myDirectory + "/" + path;
        }
        return output;
    }*/

    public static String ensureAbsolute(String arg) {
        if (arg.startsWith("/")) {
            return arg;
        }

        return System.getProperty("user.dir") + "/" + arg;
    }

    public static String[] absoluteSplitPath(String s) {
        String[] components = SplitPathName.splitPath(s);
        components[0] = ensureAbsolute(components[0]);
        return components;
    }

    /*public static void main(String[] args) {

        for (int i = 0; i < args.length; i++) {
            //String[] components = absoluteSplitPath(ensureAbsolute(args[i]));
            System.out.println("File: " + components[1] + " Type: " + components[3] + " [" + components[0] + "]");
        }

    }*/
}
