import util.TextUI;

public class MediaMenu implements Menu{


    @Override
    public int showOptions(){
        TextUI ui = new TextUI();
        int choice = ui.promptNumeric("1. Play Media" +
                "\n2. Add media to Watch Later list" +
                "\n3. Remove media from Watch Later list");
        if(choice == 1){
            playMedia();

        } else if (choice == 2) {
            addWatchLater();

        } else if (choice == 3) {
            removeWatchLater();

        }
        return choice;
    }

    public void playMedia(){
        // Afspiller media
        System.out.println("Now playing: " + media.getName());
        // Tilføjer mediet til Watched list

    }

    public void addWatchLater(){
        // Tilføjer media til WatchLater liste
        System.out.println(media.getName() + " has been added to Watch Later list");

    }

    public void removeWatchLater(){
        // Fjerne media fra WatchLater liste
        System.out.println(media.getName() + " has been remove from Watch Later lis");
    }


}
