import java.io.*;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * A utility class for the fox hound program.
 *
 * It contains helper functions for all file input / output
 * related operations such as saving and loading a game.
 */

public class FoxHoundIO {

    /**
     * This will load the game from the file name supplied by the user
     *
     * @param players this contains the current coordinates of the players on the game board
     * @param path this is the path that contains the file name
     * @return
     */
    public static char loadGame(String[] players, Path path) {
        if (players == null) {
            throw new NullPointerException("ERROR: players array is null.");
        }  else if (players.length != 5) {
            throw new IllegalArgumentException("ERROR: game dimensions have to be 8x8 to save game.");
        } else if (path == null) {
            throw new NullPointerException("ERROR: file path is null");
        }

        char nextTurn = '#';
        char tempTurn;
        String[] tempPlayers = new String[5];
        String[] temp = new String[6];
        String tempInput = "";
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader(path.toString()));
            tempInput = reader.readLine();
            reader.close();
        } catch (IOException e){
            throw new IllegalArgumentException("ERROR: File read error.");
        }

        tempTurn = tempInput.charAt(0);
        temp = tempInput.split(" ");
        System.arraycopy(temp, 1, tempPlayers, 0, 5);

        if (tempInput.charAt(1) == ' ') {
            if (tempTurn == FoxHoundUtils.FOX_FIELD || tempTurn == FoxHoundUtils.HOUND_FIELD) {
                nextTurn = tempTurn;
                System.arraycopy(tempPlayers, 0, players, 0, 5);
            } else {
                System.err.println("ERROR: Wrong file format.");
            }
        } else {
            throw new IllegalArgumentException("ERROR: Wrong file format.");
        }

        return nextTurn;
    }


    /**
     * This will save who's turn it is and all the current positions on the game board into a text file.
     *
     * @param players array containing all the current player coordinates
     * @param figure who's turn is it to move  (F or H)
     * @param path file name
     * @return true if saved successfully else false
     */
    public static boolean saveGame(String[] players, char figure, Path path) {
        if (players == null) {
            throw new NullPointerException("ERROR: players array is null.");
        } else if (figure != FoxHoundUtils.FOX_FIELD && figure != FoxHoundUtils.HOUND_FIELD) {
            throw new IllegalArgumentException("ERROR: figure is not H or F.");
        } else if (players.length != 5) {
            throw new IllegalArgumentException("ERROR: game dimensions have to be 8x8 to save game.");
        } else if (path == null) {
            throw new NullPointerException("ERROR: file path is null");
        }

        boolean saveFlag = false;
        int counter = 1;
        String fileName = path.toString();
        File file = new File(fileName);

        try {
            FileWriter myWriter = new FileWriter(file);
            myWriter.write(figure + " ");
            for (int i = 0; i < players.length; i++) {
                myWriter.write(players[i] + " ");
                counter++;
            }
            myWriter.close();
        } catch (IOException e) {
            System.err.println("An error occurred.");
        }

        if (counter == 6) {
            saveFlag = true;
        }

        return saveFlag;
    }
}