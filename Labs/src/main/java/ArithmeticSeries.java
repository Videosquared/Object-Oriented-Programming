public class ArithmeticSeries {
    public static void main(String[] args) {
        int a = Integer.parseInt(args[0]);
        int b = 0;

        for (int i = 1; i <= a; i++){
            b += i;
        }
        System.out.println(b);
    }
}
