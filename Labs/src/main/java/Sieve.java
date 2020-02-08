public class Sieve {
    public static void main(String[] args){
        int[] a = new int[19];
        int p = 2;
        int n = 20;
        boolean flag = true;

        for (int i = 0; i < 19; i++){
            a[i] = i + 2;
        }

        while (p < n){
            System.out.print(p + " ");
            for (int i = 0; i < 19; i++) {
                if (a[i] % p == 0){
                    a[i] = 0;
                }
            }
            for (int i = 0; i < 19; i++){
                flag = true;
                if (a[i] != 0 ){
                    p = a[i];
                    flag = false;
                    break;
                }}
            if (flag == true){
                p = n+1;
            }

        }
    }
}
