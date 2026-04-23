import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class User {

    protected StreamingService service;
    private String username;
    private String password;
    private ArrayList<Media> watchedList;
    private ArrayList<Media> watchLaterList;

    public User(StreamingService service, String username, String password) {
        this.service = service;
        this.username = username;
        this.password = password;
        watchedList = new ArrayList<>();
        watchLaterList = new ArrayList<>();
    }

    public ArrayList<Media> getWatchedList() {
        return watchedList;
    }

    public ArrayList<Media> getWatchLaterList() {
        return watchLaterList;
    }

    public void showMenu() {
			var userMenu = new UserMenu(service);
			userMenu.showOptions();
	}

    public void saveListsToFile() {
        try (FileWriter writer = new FileWriter("src/main/resources/userSaveData/" + username + ".csv")) {

            String[] lines = null;
            if (watchedList.size() > watchLaterList.size()) {
                lines = new String[watchedList.size()];
            } else {
                lines = new String[watchLaterList.size()];
            }

            for (int i = 0; i < lines.length; i++) {
                if (!watchedList.isEmpty()) {
                    lines[i] = watchedList.get(i).getName();
                } else {
                    lines[i] = "0";
                }
            }
            for (int i = 0; i < lines.length; i++) {
                if (!watchLaterList.isEmpty()) {
                    lines[i] += ", " + watchLaterList.get(i).getName();
                } else {
                    lines[i] = ", 0";
                }
            }
            String header = "watchedList, watchLaterList";
            writer.write(header + "\n");

            for (String line : lines) {
                writer.write(line + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addToWatchLater(Media selectedMedia) {
        // Add the selected media to the watch later list
        watchLaterList.add(selectedMedia);
        // Save updated list to file
        saveListsToFile();
        System.out.println(selectedMedia.getName() + " has been added to Watch Later list.");
    }

    public void removeFromWatchLater(Media selectedMedia) {
        // Remove the selected media from the watch later list
        watchLaterList.remove(selectedMedia);
        // Save updated list to file
        saveListsToFile();
        System.out.println(selectedMedia.getName() + " has been removed from Watch Later list.");
    }

    public void addToWatched(Media selectedMedia) {
        // Removes media from Watch Later list first
        watchLaterList.remove(selectedMedia);
        // Adds media to Watched list
        watchedList.add(selectedMedia);
        // Save updated lists to file
        saveListsToFile();
        saveListsToFile();
        System.out.println(selectedMedia.getName() + " has been added to Watched list.");
        System.out.println("Now playing: " + selectedMedia.getName());
    }
}
