import java.util.Arrays;
import java.util.Objects;

/**
 * Immutable class encapsulating data for a single book entry.
 */
public class BookEntry {

    /** This holds the title of the book */
    private final String title;
    /** This holds all the authors of the book */
    private final String[] authors;
    /** This holds the rating of the book */
    private final float rating;
    /** This holds the ISBN of the book */
    private final String ISBN;
    /** This holds the number of pages */
    private final int pages;

    /**
     * This will create an BookEntry
     *
     * @param title this is the title of the book
     * @param authors this is a list of the authors of the books
     * @param rating rating of the book
     * @param isbn ISBN of the book
     * @param pages number of pages of the book
     */
    public BookEntry(String title, String[] authors, float rating, String isbn, int pages) {
        isBookEntryValid(title, authors, rating, isbn, pages);

        this.title = title;
        this.authors = authors;
        this.rating = rating;
        this.ISBN = isbn;
        this.pages = pages;
    }

    /**
     * This will print all the information about the book in the format specified
     *
     * @return the formatted string of the book details
     */
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append(title).append("\nby ");

        for (String author : authors) {
            output.append(author).append(", ");
        }
        output.deleteCharAt(output.length() - 1).deleteCharAt(output.length() - 1).append("\n");

        output.append("Rating: ").append(String.format("%.2f", rating)).append("\n");
        output.append("ISBN: ").append(ISBN).append("\n");
        output.append(pages).append(" pages");

        return output.toString();
    }

    /**
     * This will compare 2 BookEntry instances and determine if they are the same
     *
     * @param that the book entry to be compared
     * @return true if book details are the same or the same instance else false
     */
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

    /**
     * Calculates the hashcode of the given BookEntry instance
     *
     * @return the hashcode of the BookEntry instance
     */
    @Override
    public int hashCode() {
        int result = Objects.hash(title, rating, ISBN, pages);
        result = 31 * result + Arrays.hashCode(authors);
        return result;
    }

    /**
     * Returns the rating of the book
     *
     * @return rating of the book
     */
    public float getRating() {
        return rating;
    }

    /**
     * Returns the ISBN of the book
     *
     * @return ISBN of the book
     */
    public String getISBN() {
        return ISBN;
    }

    /**
     * Returns the pages of the book
     *
     * @return pages of the book
     */
    public int getPages() {
        return pages;
    }

    /**
     * Return the title of the book
     *
     * @return title of the book
     */
    public String getTitle() {
        return title;
    }

    /**
     * Return all the authors of the book
     *
     * @return all the authors of the book
     */
    public String[] getAuthors() {
        return authors;
    }

    /**
     * This checks if the all the parameters provided are non null and make sure they are within range of the program
     *
     * @param title title of the book (must not be null)
     * @param authors authors of the book (must not be null)
     * @param rating rating of the book (must be between 0 - 5)
     * @param isbn ISBN of the book (must not be null)
     * @param pages number of pages of the book (must not be negative)
     * @throws NullPointerException if given parameters is null
     * @throws IllegalArgumentException if rating is not between 0 and 5 OR pages are negative
     */
    private void isBookEntryValid(String title, String[] authors, float rating, String isbn, int pages) {
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