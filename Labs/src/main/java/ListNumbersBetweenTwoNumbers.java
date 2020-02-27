public class C {
    public
    static void main(String[] y) {
        int x = Integer.parseInt(y[0]);
        int z = Integer.parseInt(y[1]);
        if (x <= z) {
            for (int index = x; index <= z; index++) {
                if (index % 2 == 0) {
                    System.out.print(index + " ");
                }
            }
        } else {
            System.out.println(x + " cannot be greater than " + z);
        }
    }
}