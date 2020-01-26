public class ImQuadraticSolver {
    public static void main(String[] args) {
        double a = Double.parseDouble(args[0]);
        double b = Double.parseDouble(args[1]);
        double c = Double.parseDouble(args[2]);

        if ( (b*b)-(4*a*c) >= 0 && a != 0 ) {
            System.out.println( ((-b)+(Math.sqrt((b*b)-(4*a*c))))/(2*a) );
            System.out.println( ((-b)-(Math.sqrt((b*b)-(4*a*c))))/(2*a) );
        } else if ((b*b)-(4*a*c) < 0 && a != 0 ) {
            System.out.print( (((-b)/(2*a)) + " + "));
            System.out.println( (( (Math.sqrt(Math.abs((b*b) - (4*a*c) )))) / (2*a) + "i"));
            System.out.print( (((-b)/(2*a)) + " - "));
            System.out.println( (( (Math.sqrt(Math.abs((b*b) - (4*a*c))))) / (2*a) + "i"));
        } else if (a == 0) {
            System.out.println( -c/b );
        } else {
            System.out.println("Equation is not solvable for real x.");
        }
    }
}
