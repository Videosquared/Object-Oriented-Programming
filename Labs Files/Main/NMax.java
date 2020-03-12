import java.util.Scanner;

public class NMax {
    public static int max(int a, int b, int c){
       return Math.max(Math.max(a,b),c);
    }

    public static double max(double a, double b, double c){
        return Math.max(Math.max(a,b),c);
    }

    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        double a = stdIn.nextDouble();
        double b = stdIn.nextDouble();
        double c = stdIn.nextDouble();

        System.out.println(max(a,b,c));

        /*if (isTri(a, b, c)) {
            System.out.printf("%s, %s and %s could be the lengths of a triangle\n", a, b, c);
        }
        else {
            System.out.println("Not a triangle.");
        }*/

        stdIn.close();
    }
}
