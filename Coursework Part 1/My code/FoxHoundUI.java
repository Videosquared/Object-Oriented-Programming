import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Objects;

/**
 * A utility class for the fox hound program.
 * 
 * It contains helper functions for all user interface related
 * functionality such as printing menus and displaying the game board.
 */
public class FoxHoundUI {

    /** Number of main menu entries. */
    private static final int MENU_ENTRIES = 4;
    /** Main menu display string. */
    private static final String MAIN_MENU =
        "\n1. Move\n2. Save Game\n3. Load Game\n4. Exit\n\nEnter 1 - 4:";

    /** Menu entry to select a move action. */
    public static final int MENU_MOVE = 1;
    /** Menu entry to select a move action. */
    public static final int MENU_SAVE = 2;
    /** Menu entry to select a move action. */
    public static final int MENU_LOAD = 3;
    /** Menu entry to terminate the program. */
    public static final int MENU_EXIT = 4;

    /**
     * This will display the game board and its axis
     *
     * @param players array of current players to be displayed
     * @param dimension size of the game board
     */
    public static void displayBoard(String[] players, int dimension) {
        int x_dim = dimension + 4;
        int y_dim = dimension + 4;
        String[][] output = new String[x_dim][y_dim];

        for (int i = 0; i < x_dim; i++) {
            for (int j = 0; j < y_dim; j++) {
                output[i][j] = FoxHoundUtils.PRINT_SPACER;
            }
        }

        for (int i = 2; i < x_dim - 2; i++) {
            for (int j = 2; j < y_dim - 2; j++) {
                output[i][j] = ".";
            }
        }
        // adds the F to the correct position
        for (int j = 2; j < x_dim - 2; j++) {
            for (int i = 2; i < y_dim - 2; i++) {
                if (FoxHoundUtils.isFox((FoxHoundUtils.intToString(j - 2, i - 2)), players)) {
                    output[j][i] = "F";
                }
            }
        }

        // adds the H to the correct position in the array
        for (int j = 2; j < x_dim - 2; j++) {
            for (int i = 2; i < y_dim - 2; i++) {
                if (FoxHoundUtils.isHound((FoxHoundUtils.intToString(j - 2, i - 2)), players)) {
                    output[j][i] = "H";
                }
            }
        }

        for (int j = 2; j < x_dim - 2; j++) {
            output[j][0] = String.valueOf((char) ('A' + (j - 2)));
            output[j][y_dim - 1] = String.valueOf((char) ('A' + (j - 2)));
        }

        if (dimension < 10) {
            for (int j = 2; j < y_dim - 2; j++) {
                output[0][j] = String.valueOf(1 + (j - 2));
                output[x_dim - 1][j] = String.valueOf(1 + (j - 2));
            }
        } else {
            output[0][0] += FoxHoundUtils.PRINT_SPACER;
            output[0][y_dim - 1] += FoxHoundUtils.PRINT_SPACER;

            for (int j = 2; j < y_dim - 2; j++) {
                output[0][j] = String.format("%02d", (1 + (j - 2)));
                output[x_dim - 1][j] = String.format("%02d", (1 + (j - 2)));
            }
        }

       for (int i = 0; i < x_dim; i++) {
            System.out.print(output[i][0]);
        }
        System.out.println();
        System.out.println();

        for (int i = 2; i < y_dim - 2; i++) {
            for (int j = 0; j < x_dim; j++) {
                System.out.print(output[j][i]);
            }
            System.out.println();
        }
        System.out.println();

        for (int i = 0; i < x_dim; i++) {
            System.out.print(output[i][y_dim - 1]);
        }
    }

    /**
     * Print the main menu and query the user for an entry selection.
     * 
     * @param figureToMove the figure type that has the next move
     * @param stdin a Scanner object to read user input from
     * @return a number representing the menu entry selected by the user
     * @throws IllegalArgumentException if the given figure type is invalid
     * @throws NullPointerException if the given Scanner is null
     */
    public static int mainMenuQuery(char figureToMove, Scanner stdin) {
        Objects.requireNonNull(stdin, "Given Scanner must not be null");
        if (figureToMove != FoxHoundUtils.FOX_FIELD 
         && figureToMove != FoxHoundUtils.HOUND_FIELD) {
            throw new IllegalArgumentException("Given figure field invalid: " + figureToMove);
        }

        String nextFigure = 
            figureToMove == FoxHoundUtils.FOX_FIELD ? "Fox" : "Hounds";

        int input = -1;
        while (input == -1) {
            System.out.println(nextFigure + " to move");
            System.out.println(MAIN_MENU);

            boolean validInput = false;
            if (stdin.hasNextInt()) {
                input = stdin.nextInt();
                validInput = input > 0 && input <= MENU_ENTRIES;
            }

            if (!validInput) {
                System.out.println("Please enter valid number.");
                input = -1; // reset input variable
            }

            stdin.nextLine(); // throw away the rest of the line
        }

        return input;
    }

    /**
     * This asks the user to enter the original coordinates and the final coordinates they wish to move to.
     * Also checks if the supplied coordinates are on the game board
     *
     * @param dimension size of game board
     * @param stdin a Scanner object to read user input from
     * @return array of size two holding the original and destination coordinates
     */
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
            } else {
                System.err.println("ERROR: Please enter valid coordinate pair separated by space.");
            }
        }
        return input;
    }

    /**
     * Use the scanner to ask the user to input the file name they wish to read from/write to
     *
     * @param stdin scanner input from the user
     * @return file name, entered by the user
     */
    public static Path fileQuery(Scanner stdin) {
        if (stdin == null) {
            throw new NullPointerException("Scanner is null");
        }
        System.out.println("Enter file path:");
        String input = stdin.next();

        Path path = Paths.get(input);

        return path;
    }

}