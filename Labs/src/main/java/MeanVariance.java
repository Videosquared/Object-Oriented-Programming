public class MeanVariance {
    public static void main(String[] args){
        int n = args.length;
        double[] a = new double[n];
        double x = 0;
        double o = 0;

        for (int i = 0; i < n; i++){
            a[i] = Double.parseDouble(args[i]);
        }

        for (int i = 0; i < n; i++){
            x += a[i];
        }
        x = x / n;

        for (int i = 0; i < n; i++){
            o += (a[i]-x)*(a[i]-x);
        }
        o = o / n;

        System.out.println(x);
        System.out.println(o);


    }
}
