import util.TextUI;

public class MediaMenu implements Menu{


    @Override
    public void showOptions(){
        TextUI ui = new TextUI();
        int choice = ui.promptNumeric("1. Play Media" +
                "\n2. Add media to Watch Later list" +
                "\n3. Remove media from Watch Later list");
        switch(choice){
            case 1:
                playMedia();
            case 2:
                addWatchLater();
            case 3:
                removeWatchLater();
            default:
                System.out.println("Try again");
                showOptions();
        }
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
