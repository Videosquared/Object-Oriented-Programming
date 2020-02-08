import java.awt.*;

/**
 * A utility class for the fox hound program.
 * 
 * It contains helper functions to check the state of the game
 * board and validate board coordinates and figure positions.
 */
public class FoxHoundUtils {

    // ATTENTION: Changing the following given constants can 
    // negatively affect the outcome of the auto grading!

    /** Default dimension of the game board in case none is specified. */
    public static final int DEFAULT_DIM = 8;
    /** Minimum possible dimension of the game board. */
    public static final int MIN_DIM = 4;
    /** Maximum possible dimension of the game board. */
    public static final int MAX_DIM = 26;

    /** Symbol to represent a hound figure. */
    public static final char HOUND_FIELD = 'H';
    /** Symbol to represent the fox figure. */
    public static final char FOX_FIELD = 'F';

    // HINT Write your own constants here to improve code readability ...

    /** Most left co-ordinates to start placing the hound figures */
    public static final char H_LEFT_COORD = 'B';


    public static String[] initialisePositions(int dimension) {
        if (dimension >= 4 && dimension <= 26) {
            int num_of_hounds = (dimension / 2);
            String[] players = new String[num_of_hounds + 1];

            players[0] = String.valueOf(H_LEFT_COORD);
            players[0] += 1;

            for (int i = 1; i < players.length - 1; i++) {
                players[i] = String.valueOf((char) (players[i - 1].charAt(0) + 2));
                players[i] += 1;
            }

            if (dimension % 2 == 0) {
                players[players.length - 1] = String.valueOf((char) ('A' + (int) (Math.ceil(dimension / 2.0))));
            } else {
                players[players.length - 1] = String.valueOf((char) ('A' + (int) (Math.floor(dimension / 2.0))));
            }
            players[players.length - 1] += dimension;

            return players;
        } else {
            throw new IllegalArgumentException(String.format("Invalid Game dimension %d", dimension));
        }
    }

    public static boolean isHoundWin() {
        return false;
    }


    public static boolean isFoxWin() {
        return false;
    }

    public static boolean isValidMove() {
        return false;
    }

}
