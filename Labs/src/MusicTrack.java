public class MusicTrack {

    private String artist;
    private String title;

    public MusicTrack(String artist, String title) {
        this.artist = artist;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String toString() {
        return String.format("%s | %s", title, artist);
    }

}
