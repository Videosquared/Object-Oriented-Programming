import java.nio.file.Path;
import java.util.Scanner;

/** 
 * The Main class of the fox hound program.
 * 
 * It contains the main game loop where main menu interactions
 * are processed and handler functions are called.
  */
public class FoxHoundGame {

    /** 
     * This scanner can be used by the program to read from
     * the standard input. 
     * 
     * Every scanner should be closed after its use, however, if you do
     * that for StdIn, it will close the underlying input stream as well
     * which makes it difficult to read from StdIn again later during the
     * program.
     * 
     * Therefore, it is advisable to create only one Scanner for StdIn 
     * over the course of a program and only close it when the program
     * exits. Additionally, it reduces complexity. 
     */
    private static final Scanner STDIN_SCAN = new Scanner(System.in);

    /**
     * Swap between fox and hounds to determine the next
     * figure to move.
     * 
     * @param currentTurn last figure to be moved
     * @return next figure to be moved
     */
    private static char swapPlayers(char currentTurn) {
        if (currentTurn == FoxHoundUtils.FOX_FIELD) {
            return FoxHoundUtils.HOUND_FIELD;
        } else {
            return FoxHoundUtils.FOX_FIELD;
        }
    }

    /**
     * The main loop of the game. Interactions with the main
     * menu are interpreted and executed here.
     * 
     * @param dim the dimension of the game board
     * @param players current position of all figures on the board in board coordinates
     */
    private static void gameLoop(int dim, String[] players) {

        // start each game with the Fox
        char turn = FoxHoundUtils.FOX_FIELD;
        Path path;
        boolean exit = false;
        while(!exit) {
            System.out.println("\n#################################");
            FoxHoundUI.displayBoard(players, dim);

            int choice = FoxHoundUI.mainMenuQuery(turn, STDIN_SCAN);
            
            // handle menu choice
            switch(choice) {
                case FoxHoundUI.MENU_MOVE:
                    boolean flag = false;
                    while (!flag) {
                        String[] input = FoxHoundUI.positionQuery(dim, STDIN_SCAN);
                        if ((FoxHoundUtils.isValidMove(dim, players, turn, input[0], input[1]))) {
                            for (int i = 0; i < players.length; i++) {
                                if (input[0].equals(players[i])) {
                                    players[i] = input[1];
                                }
                            }
                            flag = true;
                            if (FoxHoundUtils.isFoxWin(players[players.length-1])) {
                                System.out.println("The Fox wins!");
                                exit = true;
                            } else if (FoxHoundUtils.isHoundWin(players, dim)) {
                                System.out.println("The Hounds wins!");
                                exit = true;
                            }
                        } else {
                            System.err.println("Invalid move, please try another set of coordinates.");
                        }
                    }
                    turn = swapPlayers(turn);
                    break;
                case FoxHoundUI.MENU_SAVE:
                    path = FoxHoundUI.fileQuery(STDIN_SCAN);

                    if (FoxHoundIO.saveGame(players, turn, path)){
                        System.out.println("Save file successful.");
                    } else {
                        System.err.println("ERROR: Saving file failed");
                    }
                    break;

                case FoxHoundUI.MENU_LOAD:
                    path = FoxHoundUI.fileQuery(STDIN_SCAN);
                    turn = FoxHoundIO.loadGame(players, path);
                    break;

                case FoxHoundUI.MENU_EXIT:
                    exit = true;
                    break;
                default:
                    System.err.println("ERROR: invalid menu choice: " + choice);
            }
        }
    }

    /**
     * Entry method for the Fox and Hound game. 
     * 
     * The dimensions of the game board can be passed in as
     * optional command line argument.
     * 
     * If no argument is passed, a default dimension of 
     * {@value FoxHoundUtils#DEFAULT_DIM} is used. 
     * 
     * Dimensions must be between {@value FoxHoundUtils#MIN_DIM} and 
     * {@value FoxHoundUtils#MAX_DIM}.
     * 
     * @param args contain the command line arguments where the first can be
     * board dimensions.
     */
    public static void main(String[] args) {

        int dimension = FoxHoundUtils.DEFAULT_DIM;

        String[] players = FoxHoundUtils.initialisePositions(dimension);
        gameLoop(dimension, players);

        // Close the scanner reading the standard input stream       
        STDIN_SCAN.close();
    }
}
