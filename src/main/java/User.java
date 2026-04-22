import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class User {

    private StreamingService service;
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
		if (this instanceof Admin) {
			var adminMenu = new AdminMenu(service);
			adminMenu.showOptions();
		} else {
			var userMenu = new UserMenu(service);
			userMenu.showOptions();
		}
	}

    public void saveWatchedToFile() {
        try (FileWriter writer = new FileWriter("src/main/resources/watched.csv")) {

            for (Media media : watchedList) {
                // Converts Category[] → String
                StringBuilder categoryString = new StringBuilder();
                ArrayList<Category> categories = media.getCategories();

                for (int i = 0; i < categories.size(); i++) {
                    categoryString.append(categories.get(i).name());
                    if (i < categories.size() - 1) {
                        categoryString.append(", ");
                    }
                }

                writer.write(media.getName() + "; " +
                        media.getReleaseYear() + "; " +
                        categoryString + "; " +
                        media.getRating() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveWatchLaterToFile() {
        try (FileWriter writer = new FileWriter("src/main/resources/watchlater.csv")) {

            for (Media media : watchLaterList) {
                // Converts Category[] → String
                StringBuilder categoryString = new StringBuilder();
                ArrayList<Category> categories = media.getCategories();

                for (int i = 0; i < categories.size(); i++) {
                    categoryString.append(categories.get(i).name());
                    if (i < categories.size() - 1) {
                        categoryString.append(", ");
                    }
                }

                writer.write(media.getName() + "; " +
                                media.getReleaseYear() + "; " +
                                categoryString + "; " +
                                media.getRating() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addToWatchLater(Media selectedMedia) {
        // Add the selected media to the watch later list
        watchLaterList.add(selectedMedia);
        // Save updated list to file
        saveWatchLaterToFile();
        System.out.println(selectedMedia.getName() + " has been added to Watch Later list.");
    }

    public void removeFromWatchLater(Media selectedMedia) {
        // Remove the selected media from the watch later list
        watchLaterList.remove(selectedMedia);
        // Save updated list to file
        saveWatchLaterToFile();
        System.out.println(selectedMedia.getName() + " has been removed from Watch Later list.");
    }

    public void addToWatched(Media selectedMedia) {
        // Removes media from Watch Later list first
        watchLaterList.remove(selectedMedia);
        // Adds media to Watched list
        watchedList.add(selectedMedia);
        // Save updated lists to file
        saveWatchLaterToFile();
        saveWatchedToFile();
        System.out.println(selectedMedia.getName() + " has been added to Watched list.");
        System.out.println("Now playing: " + selectedMedia.getName());
    }
}
