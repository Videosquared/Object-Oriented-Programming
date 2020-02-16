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
    /** Most left co-ordinates to start placing the hound figures */
    public static final char H_LEFT_COORD = 'B';

    /** This is used to help space out the array when printing the board*/
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
        output += (y + 1);
        //System.out.println(output);
        return output;
    }

    public static String stringToInt(String position) {
        String output = "";

        output += String.valueOf(position.charAt(0) - 'A');
        int temp = Integer.parseInt(position.substring(1));
        output += String.valueOf(temp - 1);

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
        if (!(dimension <= 26 && dimension >= 4)) {
            throw new IllegalArgumentException();
        } else if (players == null) {
            throw new NullPointerException();
        }

        boolean flag = false;

        if (isFoxSurrounded(players, dimension)) {
            flag = true;
        }

        return flag;
    }

    public static boolean isFoxSurrounded(String[] players, int dim) {

        boolean flag = false;
        int counter = 0;

        String temp = (stringToInt(players[players.length - 1]));
        int x = temp.charAt(0) - '0';
        temp = temp.substring(1);
        int y = Integer.parseInt(temp);

        String temp1 = intToString(x + 1, y + 1);
        String temp2 = intToString(x + 1, y - 1);
        String temp3 = intToString(x - 1, y + 1);
        String temp4 = intToString(x - 1, y - 1);

        String validCoord1 = "";
        String validCoord2 = "";
        String validCoord3 = "";
        String validCoord4 = "";

        if (isCoordValid(temp1, dim)) {
            validCoord1 = temp1;
            counter++;
        }
        if (isCoordValid(temp2, dim)) {
            validCoord2 = temp2;
            counter++;
        }
        if (isCoordValid(temp3, dim)) {
            validCoord3 = temp3;
            counter++;
        }
        if (isCoordValid(temp4, dim)) {
            validCoord4 = temp4;
            counter++;
        }

        switch (counter) {
            case 2:
                if (isCoordOccupied(validCoord1, players) && isCoordOccupied(validCoord2, players)) {
                    flag = true;
                }
                break;
            case 4:
                if ((isCoordOccupied(validCoord1, players)) && isCoordOccupied(validCoord2, players) && isCoordOccupied(validCoord3, players) && isCoordOccupied(validCoord4, players)) {
                    flag = true;
                }
                break;
        }
        return flag;
    }

    public static boolean isFoxWin(String foxPos) {
        boolean flag = false;
        int x,y;

        String temp = (stringToInt(foxPos));
        if (temp.length() == 3) {
            x = temp.charAt(0) - '0';
            temp = temp.substring(1);
            y = Integer.parseInt(temp);
        } else {
            x = Integer.parseInt(temp.substring(0, 2));
            temp = temp.substring(2);
            y = Integer.parseInt(temp);
        }

        if (y == 0) {
            flag = true;
        }

        return flag;
    }

    public static boolean isValidMove(int dim,String[] players,char figure,String origin,String destination) {
        if ((figure != 'H' && figure != 'F') || !(dim <= 26 && dim >= 4)) {
            throw new IllegalArgumentException();
        } else if (players == null || origin == null || destination == null) {
            throw new NullPointerException();
        }

        boolean flag = false;

        if (isOriginTheFigureSupplied(origin, figure, players) && !isCoordOccupied(destination,  players) && isCoordValid(destination, dim) && isOneDiagonalMove(origin, destination, figure)) {
            flag = true;
        }

        return flag;
    }


    public static boolean isOriginTheFigureSupplied(String origin, char figure, String[] players) {
        boolean flag = false;
        if (figure == HOUND_FIELD) {
            for (int i = 0; i < players.length -1; i++) {
                if (origin.equals(players[i])) {
                    flag = true;
                    break;
                }
            }
            return flag;
        } else if (figure == FOX_FIELD) {
            if (origin.equals(players[players.length - 1])) {
                flag = true;
            }
            return flag;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static boolean isCoordOccupied(String coord, String[] players) {
        boolean flag = false;
        for (int i = 0; i < players.length; i++) {
            if (coord.equals(players[i])) {
                flag = true;
                break;
            }
        }
        return flag;
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

    public static boolean isOneDiagonalMove(String origin, String destination, char figure) {
        boolean flag = false;
        int xOrigin;
        int yOrigin;
        String tempOrigin = (stringToInt(origin));

        if (tempOrigin.length() == 3) {
            xOrigin = tempOrigin.charAt(0) - '0';
            tempOrigin = tempOrigin.substring(1);
            yOrigin = Integer.parseInt(tempOrigin);
        } else {
            xOrigin = Integer.parseInt(tempOrigin.substring(0, 2));
            tempOrigin = tempOrigin.substring(2);
            yOrigin = Integer.parseInt(tempOrigin);
        }

        if (figure == FOX_FIELD) {
            String temp1 = intToString(xOrigin + 1, yOrigin + 1);
            String temp2 = intToString(xOrigin + 1, yOrigin - 1);
            String temp3 = intToString(xOrigin - 1, yOrigin + 1);
            String temp4 = intToString(xOrigin - 1, yOrigin - 1);

            if (temp1.equals(destination) || temp2.equals(destination) || temp3.equals(destination) || temp4.equals(destination)) {
                flag = true;
            }
        } else {
            String temp1 = intToString(xOrigin + 1, yOrigin + 1);
            String temp2 = intToString(xOrigin - 1, yOrigin + 1);

            if (temp1.equals(destination) || temp2.equals(destination)) {
                flag = true;
            }
        }

        return flag;
    }

}
