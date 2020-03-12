public class MultiplesLoopRange {
    public static void main(String[] args) {
        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);
        int c = Integer.parseInt(args[2]);

        if (a < b){
            for (int i = a; i <= b; i++ ) {
                if (i % c == 0) {
                    System.out.print(i + " ");
                }
            }
            System.out.print("");
        } else if (a > b) {
            for (int i = a; i >= b; i-- ) {
                if (i % c == 0) {
                    System.out.print(i + " ");
                }
            }
            System.out.print("");
        } else {
            if ( a % c == 0 ){
                System.out.print(a);
            }

        }
    }
}
