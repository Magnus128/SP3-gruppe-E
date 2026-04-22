import util.TextUI;

public class MediaMenu implements Menu{
    private StreamingService service;
    private Media selectedMedia;

    public MediaMenu(StreamingService service, Media media){
        this.service = service;
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
        service.getCurrentUser().addToWatched(selectedMedia);
    }

    public void addWatchLater(){
        // Tilføjer media til WatchLater liste
        service.getCurrentUser().addToWatchLater(selectedMedia);
    }

    public void removeWatchLater(){
        // Fjerne media fra WatchLater liste
        service.getCurrentUser().removeFromWatchLater(selectedMedia);
    }

}
