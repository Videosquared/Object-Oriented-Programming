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

    public static char loadGame(String[] players, Path path) {
        if (players == null) {
            throw new NullPointerException("ERROR: players array is null.");
        }  else if (players.length != 5) {
            throw new IllegalArgumentException("ERROR: game dimensions have to be 8x8 to save game.");
        } else if (path == null) {
            throw new NullPointerException("ERROR: file path is null");
        }

        String fileName = path.toString();
        char nextTurn = '#';
        char tempTurn;
        String[] tempPlayers = new String[5];
        String[] temp = new String[6];
        String tempInput = "";
        File file = new File(fileName);

        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.err.println("ERROR: file not found. ");
        }
        assert scanner != null;
        while (scanner.hasNextLine()) {
            tempInput = scanner.nextLine();
        }
        scanner.close();

        tempTurn = tempInput.charAt(1);
        temp = tempInput.split(" ");
        System.arraycopy(temp, 1, tempPlayers, 0, 5);

        if (tempTurn == FoxHoundUtils.FOX_FIELD || tempTurn == FoxHoundUtils.HOUND_FIELD) {
            nextTurn = tempTurn;
            System.arraycopy(tempPlayers, 0, players, 0, 5);
        }

        return nextTurn;
}

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