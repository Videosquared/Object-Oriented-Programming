import java.util.Objects;
import java.util.Scanner;

public class TestJake {
    public static final Scanner STDIN_SCAN = new Scanner(System.in);
    public static final char[] alphabet = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    public static void main(String[] args) {
        String[] test = positionQuery(8, STDIN_SCAN);

        for (int i = 0; i < test.length; i++) {
            System.out.println(test[i]);
        }

        System.out.println(isCoordValid("124", 15));
    }

    /*
    public static String[] positionQuery (int dim, Scanner stdin) {
        Objects.requireNonNull(stdin, "Given Scanner must not be null");
        String[] output = new String[2];
        String start = String.valueOf('A');
        String end = String.valueOf((char)('A' + (dim - 1)));
        System.out.println("Provide origin and destination coordinates.");
        System.out.println(String.format("Enter two positions between %s%d-%s%d:", start, 1, end, dim));
        String origin = stdin.next();
        String destination = stdin.next();

        while (!(FoxHoundUtils.isCoordValid(origin, dim) && FoxHoundUtils.isCoordValid(destination, dim))) {
            System.out.println("");
            System.err.println("ERROR: Please enter valid coordinate pair separated by space.");
            System.out.println("Provide origin and destination coordinates.");
            System.out.println(String.format("Enter two positions between %s%d-%s%d:", start, 1, end, dim));
            stdin.nextLine();
            if (!stdin.hasNext()) {
                break;
            }
            origin = stdin.next();
            destination = stdin.next();
        }
        output[0] = (origin);
        output[1] = (destination);
        return output;
    }*/

    public static String[] positionQuery(int dimension, Scanner stdin) {
        Objects.requireNonNull(stdin, "Given Scanner must not be null");
        boolean flag = false;

        String[] input = new String[2];
        String start = String.valueOf('A');
        String end = String.valueOf((char)('A' + (dimension - 1)));

        while (!flag) {
            System.out.println("Provide origin and destination coordinates.");
            System.out.println(String.format("Enter two positions between %s%d-%s%d:", start, 1, end, dimension));
            System.out.println();

            input[0] = stdin.next();
            input[1] = stdin.next();

            if (FoxHoundUtils.isCoordValid(input[0], dimension) && FoxHoundUtils.isCoordValid(input[1], dimension)) {
                flag = true;
            } else if (input[1].equals(null)){
                System.err.println("ERROR: Please enter valid coordinate pair separated by space.");
            } else {
                System.err.println("ERROR: Please enter valid coordinate pair separated by space.");
            }
        }
        return input;
    }

    public static boolean isCoordValid(String input, int dimension) {
        boolean flag = false;
        for (int i = 0; i < dimension; i++) {
            if (input.charAt(0) == alphabet[i]) {
                flag = true;
                break;
            } else {
                flag = false;
            }
        } if (flag == true) {
            if (dimension < 10) {
                if (input.charAt(1) < 1 ||  input.charAt(1) > dimension) {
                    flag = false;
                } else {
                    flag = true;
                }
            } else if (input.charAt(1) == 1) {
                if (10 + input.charAt(2) < 10 || 10 + input.charAt(2) > dimension) {
                    flag = false;
                } else {
                    flag = true;
                }
            } else if (input.charAt(1) == 2) {
                if (20 + input.charAt(2) < 20 || 20 + input.charAt(2) > dimension) {
                    flag = false;
                } else {
                    flag = true;
                }
            }
        }
        return flag;
    }
}
