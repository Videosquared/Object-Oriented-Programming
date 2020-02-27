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


    /**
     *This method will return all the starting positions of the hounds and fox on the board in an array of Strings.
     *
     * @param dimension This is the board dimension which will determine the positions of the hounds, the number of hounds and the position of the fox.
     * @return This returns the players positions in an array.
     * @throws IllegalArgumentException if the dimension is not within the range of 8 - 26
     */
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

    /**
     * This is to calculate the initial positions of the Hound and returns the position of the hound in the given position.
     * This is aided as the starting position of a Hound always start with the letter B.
     *
     * @param index is the index of the array of which position the Hound should be.
     * @param player is the previous posititon of the array players.
     * @return the position of the Hound on the game board at that position on players array.
     */
    public static String calculateHoundInitPositions(int index, String player) {
        if (index == 0) {
            return String.valueOf(H_LEFT_COORD) + 1;
        } else {
            return String.valueOf((char) (player.charAt(0) + 2)) + 1;
        }
    }

    /**
     * This calculates the initial fox positions
     *
     * @param dimension the game board dimensions
     * @return the fox initial position.
     */
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

    /**
     * This would take an position in number format and then return it in a "B1" game format.
     *
     * @param x this is what the letter would be converted from.
     * @param y this is what the number after the letter be from.
     * @return the coordinate in the game board format.
     */
    public static String intToString(int x, int y) {
        String output = String.valueOf((char) ('A' + x));
        output += (y + 1);
        return output;
    }

    /**
     * This would take an game board coordinate and convert it to an number format String.
     *
     * @param position is the position to be converted
     * @return the converted coord in a number format but type string
     */
    public static String stringToInt(String position) {
        String output = "";
        output += String.valueOf(position.charAt(0) - 'A');
        int temp = Integer.parseInt(position.substring(1));
        output += String.valueOf(temp - 1);
        return output;
    }

    /**
     * This checks if the current position on the board is a fox or not.
     *
     * @param position current position on the board
     * @param players array holding all the positions of the players on the board.
     * @return boolean, if it is a fox position or not
     */
    public static boolean isFox(String position, String[] players) {
        return position.equals(players[players.length - 1]);
    }

    /**
     * This checks if the current position on the board is a Hound or not. This iterates through the array players
     * and determins of the current position matches with any of the hound positions
     *
     * @param position current position on the board
     * @param players array holding all the positions of the players on the board.
     * @return boolean, if it is a hound position or not
     */
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

    /**
     * This checks if the hounds have won the game or not
     *
     * @param players array holding all the positions of the players on the board
     * @param dimension this is game board dimension
     * @return true if the hounds have won, false if they have not won
     */
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

    /**
     * This checks if the hounds have surrounded the fox and the fox no longer have any valid moves
     *
     * @param players array containing all the positions of the players on the board
     * @param dim game board dimensions
     * @return true if the fox is surrounded, false if not
     */
    public static boolean isFoxSurrounded(String[] players, int dim) {
        boolean flag = false;
        int counter = 0;
        int x,y;
        String temp = (stringToInt(players[players.length - 1]));

        if (temp.length() == 2) {
            x = temp.charAt(0) - '0';
            temp = temp.substring(1);
            y = Integer.parseInt(temp);
        } else {
            x = Integer.parseInt(temp.substring(0, 2));
            temp = temp.substring(2);
            y = Integer.parseInt(temp);
        }

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
                } else if ((isCoordOccupied(validCoord1, players) && isCoordOccupied(validCoord3, players))) {
                    flag = true;
                } else if ((isCoordOccupied(validCoord1, players) && isCoordOccupied(validCoord4, players))) {
                    flag = true;
                } else if (isCoordOccupied(validCoord2, players) && isCoordOccupied(validCoord3, players)) {
                    flag = true;
                } else if ((isCoordOccupied(validCoord2, players) && isCoordOccupied(validCoord4, players))) {
                    flag = true;
                } else if (isCoordOccupied(validCoord3, players) && isCoordOccupied(validCoord4, players)) {
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

    /**
     * This determines if the fox have won the game by checking if the current fox positions
     * is on row one of the game board. If so then then fox have won
     *
     * @param foxPos current fox position
     * @return true if fox has won, false if fox has not one
     */
    public static boolean isFoxWin(String foxPos) {
        boolean flag = false;
        int x,y;
        String temp = (stringToInt(foxPos));
        if (temp.length() == 2) {
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

    /**
     * This will check if the coordinates entered by the user is valid, origin to destination coordinate
     *
     * @param dim dimension of game board
     * @param players array holding all the current players positons
     * @param figure to be moved (F or H)
     * @param origin original coordinate that the player is on, to be moved
     * @param destination coordinate that is to be moved to from original
     * @return true if the move is valid, false if move is invalid
     */
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

    /**
     * To check if the origin coordinate matches with the figure they want to move.
     *
     * @param origin coordinate of the figure to be moved
     * @param figure (F or H) to be moved
     * @param players array holding current positions of players on the board
     * @return true if it matches else false
     */
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

    /**
     * This checks if the destination coordinate is occupied by another coordinate.
     *
     * @param coord coordinate to be checked
     * @param players array holding current positions of players on the board
     * @return false if its NOT occupied else ture
     */
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

    /**
     * This check if the given coordinate is on the game board or not
     *
     * @param coord coordinate to be checked
     * @param dimension dimension of the game board
     * @return true if the coordinate is on the game board else false
     */
    public static boolean isCoordValid(String coord, int dimension) {
        boolean flag = false;
        int x = 0;
        int y = 0;
        String temp = (stringToInt(coord));
        if (temp.length() == 2) {
            x = temp.charAt(0) - '0';
            temp = temp.substring(1);
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

    /**
     * This checks if the destination coordinate is one diagonal move away from the original coordinate
     *
     * @param origin original coordinate
     * @param destination destination coordinate
     * @param figure (F or H) to be moved
     * @return true if it is one diagonal move away else false
     */
    public static boolean isOneDiagonalMove(String origin, String destination, char figure) {
        boolean flag = false;
        int xOrigin;
        int yOrigin;
        String tempOrigin = (stringToInt(origin));
        if (tempOrigin.length() == 2) {
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