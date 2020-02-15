import java.util.Arrays;

public class Test {

    public static final char H_LEFT_COORD = 'B';
    public static final String PRINT_SPACER = " ";
    public static final int DEFAULT_DIM = 8;
    /** Minimum possible dimension of the game board. */
    public static final int MIN_DIM = 4;
    /** Maximum possible dimension of the game board. */
    public static final int MAX_DIM = 26;
    /** Symbol to represent a hound figure. */
    public static final char HOUND_FIELD = 'H';
    /** Symbol to represent the fox figure. */
    public static final char FOX_FIELD = 'F';


    public static void main(String[] args) {
        int dimension = 8;
        String[] atest = {"B1","D1","F1","H1","D4"};
        String[] btest = {"B1", "D1", "F1", "H1", "J1", "L1", "N1", "P12", "R1", "T1", "V1", "X1", "Z1", "F26"};

        String[] start_string = initialisePositions(dimension);

        System.out.println(Arrays.toString(start_string));

        /*String test = "H"+"E"+"L"+"L"+"O";
        System.out.println(test);*/
/*
        String test = String.format("%02d", 20);
        System.out.println(test);
*/

        displayBoard(start_string, dimension);
        System.out.println(isValidMove(8, start_string, 'F', start_string[start_string.length-1], "D7"));
        //System.out.println(stringToInt("B12"));
        //System.out.println(intToString(0, 0));
        //System.out.println(isDestinationValid("E1", dimension));
        //displayBoard(atest, dimension);
        //displayBoard(btest, dimension);

    }

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

    public static String stringToInt(String position) {
        String output = "";

        output += String.valueOf(position.charAt(0) - 'A');
        int temp = Integer.parseInt(position.substring(1));
        //int temp = Integer.parseInt(position.replaceAll("[^0-9]", ""));
        output += String.valueOf(temp - 1);

        return output;
    }

    public static String intToString(int x, int y) {
        String output = String.valueOf((char) ('A' + x));
        output += (y + 1);
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




    public static void displayBoard(String[] players, int dimension) {
        int x_dim = dimension + 4;
        int y_dim = dimension + 4;
        String[][] output = new String[x_dim][y_dim];

        for (int i = 0; i < x_dim; i++) {
            for (int j = 0; j < y_dim; j++) {
                output[i][j] = PRINT_SPACER;
            }
        }

        for (int i = 2; i < x_dim - 2; i++) {
            for (int j = 2; j < y_dim - 2; j++) {
                output[i][j] = ".";
            }
        }
        /** PRINTS F*/
        for (int j = 2; j < x_dim - 2; j++) {
            for (int i = 2; i < y_dim - 2; i++) {
                if (isFox((intToString(j - 2, i - 1)), players)) {
                    output[j][i] = "F";
                }
            }
        }

        /** This will be printing the HOUNDS*/
        for (int j = 2; j < x_dim - 2; j++) {
            for (int i = 2; i < y_dim - 2; i++) {
                if (isHound((intToString(j - 2, i - 1)), players)) {
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
            output[0][0] += PRINT_SPACER;
            output[0][y_dim - 1] += PRINT_SPACER;

            for (int j = 2; j < y_dim - 2; j++) {
                output[0][j] = String.format("%02d", (1 + (j - 2)));
                output[x_dim - 1][j] = String.format("%02d", (1 + (j - 2)));
            }
        }

        for (int i = 0; i < y_dim; i++) {
            for (int j = 0; j < x_dim; j++) {
                System.out.print(output[j][i]);
            }
            System.out.println();
        }
    }

    public static boolean isValidMove(int dim,String[] players,char figure,String origin,String destination) {
        if ((figure != 'H' && figure != 'F') || !(dim <= 26 && dim >= 4)) {
            throw new IllegalArgumentException();
        } else if (players == null || origin == null || destination == null) {
            throw new NullPointerException();
        }

        boolean flag = false;

        if (isOriginTheFigureSupplied(origin, figure, players) && !isDestinationOccupied(destination, players) && isDestinationValid(destination, dim) && isOneDiagonalMove(origin, destination, figure)) {
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

    public static boolean isDestinationOccupied(String destination, String[] players) {
        boolean flag = false;
        for (int i = 0; i < players.length; i++) {
            if (destination.equals(players[i])) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    public static boolean isDestinationValid(String destination, int dimension) {
        boolean flag = false;

        String temp = (stringToInt(destination));
        int x = temp.charAt(0) - '0';
        temp = temp.substring(1);
        int y = Integer.parseInt(temp);

        if (x >= 0 && x < dimension && y >= 0 && y < dimension) {
            flag = true;
        }

        return flag;
    }

    public static boolean isOneDiagonalMove(String origin, String destination, char figure) {
        boolean flag = false;
        String tempOrigin = (stringToInt(origin));
        int xOrigin = tempOrigin.charAt(0) - '0';
        tempOrigin = tempOrigin.substring(1);
        int yOrigin = Integer.parseInt(tempOrigin);

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


