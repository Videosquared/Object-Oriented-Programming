public class ListNumbersBetweenTwoNumbers {
    public
    static void main(String[] args) {
        int numA = Integer.parseInt(args[0]);
        int numB = Integer.parseInt(args[1]);
        if (numA <= numB) {
            for (int index = numA; index <= numB; index++) {
                if (index % 2 == 0) {
                    System.out.print(index + " ");
                }
            }
        } else {
            System.out.println(numA + " cannot be greater than " + numB);
        }
    }
}