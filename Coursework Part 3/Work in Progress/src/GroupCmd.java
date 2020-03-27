import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class GroupCmd extends LibraryCommand{

    private final String TITLE = "TITLE";
    private final String AUTHOR = "AUTHOR";

    private static final char NUMERICALTITLE = '0';

    private String type;


    public GroupCmd(String argInput) {
        super(CommandType.GROUP, argInput);
    }

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

    private static boolean isDataEmpty(List<BookEntry> books) {
        return books.isEmpty();
    }

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

    private static void printTitle(HashMap<Character, List<String>> bookMap) {
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

    private static HashMap<Character, List<String>> initHashMapTitle() {
        HashMap<Character, List<String>> booksMap = new HashMap<>();
        for (int i = 0; i < 26; i++) {
            List<String> values = new ArrayList<>();
            booksMap.put((char)('A' + i), values);
        }
        List<String> values = new ArrayList<>();
        booksMap.put(NUMERICALTITLE, values);
        return booksMap;
    }

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

    private static void printAuthor(HashMap<String, List<String>> authorMap) {
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

    private static HashMap<String, List<String>> initHashMapAuthor(List<BookEntry> books) {
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
