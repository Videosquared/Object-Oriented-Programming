import java.awt.*;
import java.util.Arrays;

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
    /** */
    public static final String PRINT_SPACER = " ";


    public static String[] initialisePositions(int dimension) {

        if (dimension >= MIN_DIM && dimension <= MAX_DIM) {
            int num_of_hounds = (dimension / 2);
            String[] players = new String[num_of_hounds + 1];

            players[0] = calculateHoundInitPositions(0, players[0]);
            for (int i = 1; i < players.length - 1; i++) {
                players[i] = calculateHoundInitPositions(i, players[i - 1]);
            }
            players[players.length - 1] = calculateFoxInitPosition(dimension);
            return players;
        } else {
            throw new IllegalArgumentException(String.format("Invalid Game dimension %d", dimension));
        }
    }

    public static String calculateHoundInitPositions(int index, String player) {
        if (index == 0) {
            return String.valueOf(H_LEFT_COORD) + 1;
        } else {
            return String.valueOf((char) (player.charAt(0) + 2)) + 1;
        }
    }

    public static String calculateFoxInitPosition(int dimension) {
        if (dimension % 2 == 0) {
            if ((dimension / 2) % 2 == 0){
                return String.valueOf((char) ('A' + ((dimension / 2)))) + dimension;
            } else {
                return String.valueOf((char) ('A' + ((dimension / 2) - 1))) + dimension;
            }
        } else {
            if ((dimension / 2) % 2 == 0) {
                return String.valueOf((char) ('A' + ((dimension) / 2) + 1)) + dimension;
            } else {
                return String.valueOf((char) ('A' + ((dimension) / 2))) + dimension;
            }
        }
    }

    public static String intToString(int x, int y) {
        String output = String.valueOf((char) ('A' + x));
        output += y;
        //System.out.println(output);
        return output;
    }

    public static boolean isFox(String position, String[] players) {
        return position.equals(players[players.length - 1]);
    }

    public static boolean isHound(String position, String[] players) {
        boolean flag = false;
        for (int i = 0; i < players.length - 1; i++) {
            if (position.equals(players[i])) {
                flag = true;
                break;
            }
        }
        return flag;
    }


    public static boolean isHoundWin(String[] players, int dimension) {
        return false;
    }

    public static boolean isFoxWin(String foxPos) {
        return false;
    }

    public static boolean isValidMove(int dim,String[] players,char figure,String origin,String destination) {
        if ((figure != 'H' && figure != 'F') || !(dim <= 26 && dim >= 4)) {
            throw new IllegalArgumentException();
        } else if (players == null || origin == null || destination == null) {
            throw new NullPointerException();
        }




        return false;
    }

    public static boolean isOriginFigureSupplied(String origin, char figure, String[] players) {
        if (figure == HOUND_FIELD) {

        } else if () {

        }

            return false;
    }

    public static boolean isDestinationOccupied() {

    }

    public static boolean isDestinationValid() {

    }

}
