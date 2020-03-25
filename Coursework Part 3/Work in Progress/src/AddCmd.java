import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * Add command is used to add all the entries from an csv file to our database.
 */
public class AddCmd extends LibraryCommand {

    /**This will hold the file path to the csv file to be read from.*/
    private Path dataPath;

    /**
     * Create an add command.
     *
     * @param argInput is expected to hold the path to the file to be read from.
     */
    public AddCmd(String argInput) {
        super(CommandType.ADD, argInput);
    }

    /**
     * This will
     *
     * @param argumentInput argument input for this command.
     * @return true if the given path is to the correct file format .csv else false.
     */
    @Override
    protected boolean parseArguments(String argumentInput) {
        Objects.requireNonNull(argumentInput, "Error: given argumentInput must not be null.");
        if (!argumentInput.isBlank() && argumentInput.substring(argumentInput.length() - 4).equals(".csv")) {
            dataPath = Paths.get(argumentInput);
            return true;
        } else {
            return false;
        }
    }

    /**
     * This will execute the ADD command and will add all the entries of the csv to our database of books already.
     *
     * @param data book data to be considered for command execution.
     */
    @Override
    public void execute(LibraryData data) {
        Objects.requireNonNull(data, "Error: given data must not be null.");
        data.loadData(dataPath);
    }

}
