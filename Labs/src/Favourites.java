public class Favourites {

    private MusicTrack[] favSongs = new MusicTrack[5];

    public Favourites() { }



    public void addTrack(String artist, String title){
        boolean flag= false;

        for (int i = 0; i < 5; i++){
            if (favSongs[i] == null) {
                favSongs[i] = new MusicTrack(artist, title);
                flag = true;
                break;
            }
        }

        if (!flag) {
            MusicTrack temp = new MusicTrack(artist, title);
            System.out.println("Sorry, can't add: " + temp.toString());
            System.out.println();
        }
    }

    public void showFavourites() {
        for (int i = 0; i < 5; i++) {
            if (favSongs[i] == null) {
                continue;
            } else {
                System.out.println(favSongs[i].toString());
            }
        }
    }

    public static void main(String[] args) {
        Favourites favourites = new Favourites();
        favourites.addTrack("Fun", "Some Nights");
        favourites.addTrack("Oliver Tank", "Help You Breathe");
        favourites.addTrack("Horse Feathers", "Fit Against the Country");
        favourites.addTrack("Emile Sande", "Country House");
        favourites.addTrack("Fun", "Walking the Dog");
        favourites.addTrack("Porcelain Raft", "Put Me To Sleep");
        favourites.showFavourites();
    }
}
