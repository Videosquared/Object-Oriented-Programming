import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * Remove command removes books by title or author and value specified by the user
 */
public class RemoveCmd extends LibraryCommand{

    /** This holds the format for TITLE to be compared to*/
    private final String TITLE = "TITLE";
    /** This holds the format for AUTHOR to be compared to*/
    private final String AUTHOR = "AUTHOR";

    /** This holds the type to be searched for Author or title */
    private String type;
    /** This is the value to be searched for when removing data*/
    private String removeValue;

    /**
     * Create an remove command
     *
     * @param argInput is expected to hold the values to be remove
     * @throws IllegalArgumentException if given arguments are invalid
     * @throws NullPointerException if the given argumentInput is null.
     */
    public RemoveCmd(String argInput) {
        super(CommandType.REMOVE, argInput);
    }

    /**
     * This will split the data and check if the first value split is
     * TITLE or AUTHOR and if so then it is saved for later use
     *
     * @param argumentInput argument input for this command
     * @return true if the first arugment is TITLE or AUTHOR and second argument is not empty else false
     * @throws NullPointerException if given parameter is null
     */
    @Override
    protected boolean parseArguments(String argumentInput) {
        Objects.requireNonNull(argumentInput, "Given argumentInput must not be null.");
        String[] splitInfo = argumentInput.split(" ", 2);

        if (isFieldCorrect(splitInfo[0]) && isRemoveValueGiven(splitInfo)) {
            type = splitInfo[0];
            removeValue = splitInfo[1];
            return true;
        } else {
            return false;
        }
    }

    /**
     * This will check the user selection and then remove books by either
     * TITLE or AUTHOR depending on the user's choice
     *
     * @param data book data to be considered for command execution.
     * @throws NullPointerException if given parameter is null
     */
    @Override
    public void execute(LibraryData data) {
        Objects.requireNonNull(data, "Given data must not be null.");
        List<BookEntry> books = data.getBookData();
        Iterator<BookEntry> bookIter = books.iterator();

        if (type.equals(TITLE)) {
            removeTitle(bookIter);
        } else {
            removeAuthor(bookIter);
        }
    }

    /**
     * This will remove all the books that have the author name
     * specified by the user
     *
     * @param bookIter BookEntry iterator object
     * @throws NullPointerException if given parameter is null
     */
    private void removeAuthor(Iterator<BookEntry> bookIter) {
        Objects.requireNonNull(bookIter, "Given books must not be null.");
        int counter = 0;

        while (bookIter.hasNext()) {
            String[] authors = bookIter.next().getAuthors();
            for (String author : authors) {
                if (author.equals(removeValue)) {
                    bookIter.remove();
                    counter++;
                    break;
                }
            }
        }

        System.out.println(counter + " books removed for author: " + removeValue);

    }

    /**
     * This will remove the single book with the exact title
     * entered by the user
     *
     * @param bookIter BookEntry iterator object
     * @throws NullPointerException if given parameter is null
     */
    private void removeTitle(Iterator<BookEntry> bookIter) {
        Objects.requireNonNull(bookIter, "Given books must not be null.");
        int counter = 0;

            while (bookIter.hasNext()) {
                String title = bookIter.next().getTitle();
                if (title.equals(removeValue)) {
                    bookIter.remove();
                    counter++;
                }
            }

        if (counter > 0) {
            System.out.println(removeValue + ": removed successfully.");
        } else {
            System.out.println(removeValue + ": not found.");
        }
    }

    /**
     * This checks if the input is either TITLE or AUTHOR
     *
     * @param typeInfo user input to be checked
     * @return true if it is either TITLE or AUTHOR
     */
    private boolean isFieldCorrect(String typeInfo) {
        return typeInfo.equals(TITLE) || typeInfo.equals(AUTHOR);
    }

    /**
     * Checks if the user argument for which book to remove is
     * empty or not
     *
     * @param splitData original command line argument split into 2
     * @return true if its not empty and it exists
     */
    private boolean isRemoveValueGiven(String[] splitData) {
        return splitData.length == 2 && !splitData[1].isEmpty();
    }
}
