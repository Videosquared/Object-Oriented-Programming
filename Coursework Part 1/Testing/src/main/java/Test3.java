import java.util.Objects;
import java.util.Scanner;

public class Test3 {

    public static final Scanner STDIN_SCAN = new Scanner(System.in);

    public static void main(String[] args) {
        String[] test = positionQuery(26, STDIN_SCAN);

        for (int i = 0; i < test.length; i++) {
            System.out.println(test[i]);
        }
    }

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

            if (isCoordValid(input[0], dimension) && isCoordValid(input[1], dimension)) {
                flag = true;
            } else {
                System.err.println("ERROR: Please enter valid coordinate pair separated by space.");
            }
        }
        return input;
    }

    public static boolean isCoordValid(String coord, int dimension) {
        boolean flag = false;
        int x = 0;
        int y = 0;

        String temp = (stringToInt(coord));
        if (temp.length() == 3) {
            x = temp.charAt(0) - '0';
            temp = temp.substring(2);
            y = Integer.parseInt(temp);
        } else {
            x = Integer.parseInt(temp.substring(0, 2));
            temp = temp.substring(2);
            y = Integer.parseInt(temp);
        }

        if (x >= 0 && x < dimension && y >= 0 && y < dimension) {
            flag = true;
        }

        return flag;
    }

    public static String stringToInt(String position) {
        String output = "";

        output += String.valueOf(position.charAt(0) - 'A');
        int temp = Integer.parseInt(position.substring(1));
        output += String.valueOf(temp - 1);

        return output;
    }
}
