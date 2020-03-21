import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class RemoveCmd extends LibraryCommand{

    private final String TITLE = "TITLE";
    private final String AUTHOR = "AUTHOR";

    private String type;
    private String removeValue;

    public RemoveCmd(String argInput) {
        super(CommandType.REMOVE, argInput);
    }

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

    public void removeAuthor(Iterator<BookEntry> bookIter) {
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

        if (counter == 0) {
            System.out.println(counter + " books removed for author: " + removeValue);
        } else {
            System.out.println(counter + " books removed for author: " + removeValue);
        }
    }

    public void removeTitle(Iterator<BookEntry> bookIter) {
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

    public boolean isFieldCorrect(String typeInfo) {
        return typeInfo.equals(TITLE) || typeInfo.equals(AUTHOR);
    }

    public boolean isRemoveValueGiven(String[] splitData) {
        return splitData.length == 2 && !splitData[1].isEmpty();
    }
}
