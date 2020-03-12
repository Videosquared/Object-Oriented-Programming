//import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
//import com.sun.org.apache.xpath.internal.objects.XNull;

public class ErrorHandlingReturn {
    public static boolean birthdayGreetings(String name, int age) {
        boolean success;

        if (age > 0) {
            System.out.println("All the best to your " + age + ". birthday " + name);
            success = true;
        } else {
            System.err.println("ERROR: The given age must be larger zero but is: " + age);
            success = false;
        }

        return success;
    }

    public static void spam(String[] a, int[] b) {
        boolean x;
        for (int i = 0; i < a.length; i++) {
            x = birthdayGreetings(a[i], b[i]);
            if (!x) {
                birthdayGreetings(a[i], 20);
            }
        }
    }


}
