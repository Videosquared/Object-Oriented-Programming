import java.awt.print.Book;
import java.util.Arrays;
import java.util.Objects;

/**
 * Immutable class encapsulating data for a single book entry.
 */
public class BookEntry {
    private final String title;
    private final String[] authors;
    private final float rating;
    private final String ISBN;
    private final int pages;

    public BookEntry(String title, String[] authors, float rating, String isbn, int pages) {
        isBookEntryValid(title, authors, rating, isbn, pages);

        this.title = title;
        this.authors = authors;
        this.rating = rating;
        this.ISBN = isbn;
        this.pages = pages;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append(title).append("\nby ");

        for (String author : authors) {
            output.append(author).append(", ");
        }
        output.deleteCharAt(output.length() - 1).deleteCharAt(output.length() - 1).append("\n");

        output.append("Rating: ").append(String.format("%.2f",rating)).append("\n");
        output.append("ISBN: ").append(ISBN).append("\n");
        output.append(pages).append(" pages");

        return output.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) return true;
        if (that == null || getClass() != that.getClass()) return false;
        BookEntry thatBookEntry = (BookEntry) that;
        return Float.compare(thatBookEntry.rating, rating) == 0 &&
                pages == thatBookEntry.pages &&
                title.equals(thatBookEntry.title) &&
                Arrays.equals(authors, thatBookEntry.authors) &&
                ISBN.equals(thatBookEntry.ISBN);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(title, rating, ISBN, pages);
        result = 31 * result + Arrays.hashCode(authors);
        return result;
    }

    public float getRating() {
        return rating;
    }

    public String getISBN() {
        return ISBN;
    }

    public int getPages() {
        return pages;
    }

    public String getTitle() {
        return title;
    }

    public String[] getAuthors() {
        return authors;
    }

    public static void isBookEntryValid(String title, String[] authors, float rating, String isbn, int pages) {
        Objects.requireNonNull(title, "Given title type must not be null.");
        Objects.requireNonNull(authors, "Given authors type must not be null.");
        Objects.requireNonNull(isbn, "Given isbn type must not be null.");

        if (!(rating >= 0 && rating <= 5)) {
            throw new IllegalArgumentException("Rating of the book is not between 0-5.");
        }
        if (pages < 0) {
            throw new IllegalArgumentException("Pages cannot be negative");
        }
    }
}
