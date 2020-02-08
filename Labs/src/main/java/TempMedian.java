import java.util.Arrays;

public class TempMedian {
    public static void main(String[] args){
        int n = args.length;
        int[] a = new int[n];
        double x = 0;
        int mid = 0;

        a[0] = Integer.parseInt(args[0]);
        for (int i = 1; i < n; i++){
            if (args[i].equals(".")){
                a[i] = a[i - 1];
            } else if (args[i].equals("+")){
                a[i] = a[i - 1] + 1;
            } else {
                a[i] = a[i-1] - 1;
            }
        }
        for (int i = 0; i < n; i++){
            System.out.print(a[i]);
            System.out.print(" ");
        }
        System.out.println(" ");

        Arrays.sort(a);

        for (int i = 0; i < n; i++){
            System.out.print(a[i]);
            System.out.print(" ");
        }
        System.out.println(" ");

        //mid = a[0] + (a[n-1]-a[0])/2;
        if (n % 2 != 0){
           x = (a[n/2])/1.0;
        } else {
            x = (a[n/2]+a[(n/2)-1])/2.0;
        }
        System.out.println(x);
    }
}
