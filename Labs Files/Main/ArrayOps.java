public class ArrayOps {

    public static double findMax(double[] data) {
        double output = data[0];

        for (int i = 0; i < data.length; i++){
            if (output < data[i]) {
                output = data[i];
            }
        }
        return output;
    }

    public static double[] normalise(double data[]) {
        double[] output = new double[data.length];
        double a = 0;

        for (int i = 0; i < data.length; i++) {
            a += data[i];
        }

        for (int i = 0; i < data.length; i++) {
            output[i] = data[i] / a;
        }

        return output;
    }

    public static void normaliseInPlace(double data[]) {
        double a = 0;
        for (int i = 0; i < data.length; i++) {
            a += data[i];
        }
        for (int i = 0; i < data.length; i++) {
            data[i] = data[i] / a;
        }
    }

    public static double[] reverse(double[] data) {
        double[] output = new double[data.length];
        int n = data.length;

        for (int i = 0; i < data.length; i++) {
            output[n-1] = data[i];
            n -= 1;
        }

        return output;
    }

    public static void reverseInPlace(double[] data) {
        int n = data.length;
        double[] a = new double[n];

        for (int i = 0; i < data.length; i++) {
            a[n-1] = data[i];
            n -= 1;
        }

        for (int i = 0; i < data.length; i++) {
            data[i] = a[i];
        }
    }

    public static void swap(double[] data1, double[] data2) {
        double a = 0;
        for (int i = 0; i < data1.length; i++) {
            a = data1[i];
            data1[i] = data2[i];
            data2[i] = a;
        }
    }

}