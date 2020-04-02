import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Group command used to group all book's titles by title or author
 */
public class GroupCmd extends LibraryCommand{

    /** This holds the format for TITLE to be compared to*/
    private final String TITLE = "TITLE";
    /** This holds the format for AUTHOR to be compared to*/
    private final String AUTHOR = "AUTHOR";
    /** Standard value for the all books with numerical titles*/
    private final char NUMERICALTITLE = '0';


    /** Chosen type from the user*/
    private String type;


    /**
     * Create an group command
     *
     * @param argInput is expected to hold TITLE or AUTHOR chosen by the user
     */
    public GroupCmd(String argInput) {
        super(CommandType.GROUP, argInput);
    }

    /**
     * This will check the user input to see if it is TITLE or AUTHOR and
     * then parse the information to be used later
     *
     * @param argumentInput argument input for this command
     * @return true if it's TITLE or AUTHOR else false
     */
    @Override
    protected boolean parseArguments(String argumentInput) {
        Objects.requireNonNull(argumentInput, "Given argument must not be null.");

        if (argumentInput.equals(TITLE)) {
            type = TITLE;
            return true;
        } else if (argumentInput.equals(AUTHOR)) {
            type = AUTHOR;
            return true;
        } else {
            return false;
        }
    }

    /**
     * This will check the selected display type and then print the corresponding
     * output
     *
     * @param data book data to be considered for command execution.
     */
    @Override
    public void execute(LibraryData data) {
        Objects.requireNonNull(data, "Given data must not be null.");
        List<BookEntry> books = data.getBookData();

        if (!isDataEmpty(books)) {
            System.out.println("Grouped data by " + type);
            if (type.equals(TITLE)) {
                groupByTitle(books);
            } else {
                groupByAuthor(books);
            }
        } else {
            System.out.println("The library has no book entries.");
        }
    }

    /**
     * This will check if there is book entries already or not
     * @param books this is a list of all book entries
     * @return true if the there is no book entries else false
     */
    private boolean isDataEmpty(List<BookEntry> books) {
        return books.isEmpty();
    }

    /**
     * This will use an hashmap to map all the titles to the
     * corresponding start letter of the title (case
     * insensitive)
     *
     * @param books contains all the book entries in a List
     */
    private void groupByTitle(List<BookEntry> books) {
        Objects.requireNonNull(books, "Given books must not be null.");
        HashMap<Character, List<String>> bookMap = initHashMapTitle();

        for (BookEntry book : books) {
            char tempChar = book.getTitle().toUpperCase().charAt(0);
            if (Character.isLetter(tempChar)) {
                List<String> tempValues = bookMap.get(tempChar);
                tempValues.add(book.getTitle());
                bookMap.replace(tempChar, tempValues);
            } else {
                List<String> tempValues = bookMap.get(NUMERICALTITLE);
                tempValues.add(book.getTitle());
                bookMap.replace(NUMERICALTITLE, tempValues);
            }
        }

        if (bookMap.get(NUMERICALTITLE).isEmpty()) {
            bookMap.remove(NUMERICALTITLE);
        }

        printTitle(bookMap);
    }

    /**
     * This will print the book's titles grouped alphabetically.
     *
     * @param bookMap hashmap that holds all the books corresponding
     *                to the characters
     */
    private void printTitle(HashMap<Character, List<String>> bookMap) {
        Objects.requireNonNull(bookMap, "Given bookMap must not be null.");
        for (char key : bookMap.keySet()) {
            List<String> values = bookMap.get(key);
            if (!values.isEmpty() && key != NUMERICALTITLE) {
                System.out.println("## " + key);
                for (String title : values) {
                    System.out.println(title);
                }
            } else if (key == NUMERICALTITLE) {
                System.out.println("## [0-9]");
                for (String title : values) {
                    System.out.println(title);
                }
            }
        }
    }

    /**
     * This will create the hashmap to be used when grouping
     * by title. This will create an key for each of the letters
     * in the alphabet and one more for numerical values.
     *
     * @return hashmap with 26 characters and 1 numerical as keys
     */
    private HashMap<Character, List<String>> initHashMapTitle() {
        HashMap<Character, List<String>> booksMap = new HashMap<>();
        for (int i = 0; i < 26; i++) {
            List<String> values = new ArrayList<>();
            booksMap.put((char)('A' + i), values);
        }
        List<String> values = new ArrayList<>();
        booksMap.put(NUMERICALTITLE, values);
        return booksMap;
    }

    /**
     * This will use an hashmap to group the books by their
     * authors. An author will only appear once but a book
     * may appear multiple times due to multiple authors
     *
     * @param books contains all the book entries in a List
     */
    private void groupByAuthor(List<BookEntry> books) {
        Objects.requireNonNull(books, "Given books must not be null.");
        HashMap<String, List<String>> authorMap = initHashMapAuthor(books);

        for (BookEntry book : books) {
            for (String author : book.getAuthors()) {
                List<String> values = authorMap.get(author);
                values.add(book.getTitle());
                authorMap.replace(author, values);
            }
        }

        printAuthor(authorMap);
    }

    /**
     * This will print the book's titles grouped by authors
     *
     * @param authorMap hashmap that holds all the books titles
     *                  corresponding to their authors
     */
    private void printAuthor(HashMap<String, List<String>> authorMap) {
        List<String> authors = new ArrayList<>(authorMap.keySet());
        authors.sort(String.CASE_INSENSITIVE_ORDER);

        for (String key : authors) {
            List<String> values = authorMap.get(key);
            if (!values.isEmpty()) {
                System.out.println("## " + key);
                for (String value : values) {
                    System.out.println(value);
                }
            }
        }
    }

    /**
     * This will create an hashmap with authors only
     * appearing once as keys.
     *
     * @param books list of all book entries
     * @return hashmap with all authors only appearing once as keys
     */
    private HashMap<String, List<String>> initHashMapAuthor(List<BookEntry> books) {
        List<String> authors = new ArrayList<>();
        for (BookEntry book : books) {
            authors.addAll(Arrays.asList(book.getAuthors()));
        }
        List<String> authorSorted = authors.stream().distinct().sorted(String.CASE_INSENSITIVE_ORDER).collect(Collectors.toList());

        HashMap<String, List<String>> authorMap = new HashMap<>();
        for (String author : authorSorted) {
            List<String> values = new ArrayList<>();
            authorMap.put(author, values);
        }
        return authorMap;
    }

}
