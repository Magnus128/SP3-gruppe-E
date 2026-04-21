import util.TextUI;

public class MediaMenu implements Menu{
    private User currentUser;
    private Media selectedMedia;

    public MediaMenu(User user, Media media){
        this.currentUser = user;
        this.selectedMedia = media;
    }



    @Override
    public void showOptions(){
        TextUI ui = new TextUI();
        int choice = ui.promptNumeric("1. Play Media" +
                "\n2. Add media to Watch Later list" +
                "\n3. Remove media from Watch Later list");
        switch(choice){
            case 1:
                playMedia();
                break;
            case 2:
                addWatchLater();
                break;
            case 3:
                removeWatchLater();
                break;
            default:
                System.out.println("Try again");
                showOptions();
        }
    }

    public void playMedia(){
        // Afspiller media og tilføjer til Watched List
        currentUser.addToWatched(selectedMedia);
    }

    public void addWatchLater(){
        // Tilføjer media til WatchLater liste
        currentUser.addToWatchLater(selectedMedia);
    }

    public void removeWatchLater(){
        // Fjerne media fra WatchLater liste
        currentUser.removeFromWatchLater(selectedMedia);
    }

}
