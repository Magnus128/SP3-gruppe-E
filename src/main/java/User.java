import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class User {

    private String username;
    private String password;
    private ArrayList<Media> watchedList;
    private ArrayList<Media> watchLaterList;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

	public void showMenu() {
		if (this instanceof Admin) {
			var adminMenu = new AdminMenu();
			adminMenu.showOptions();
		} else {
			var userMenu = new UserMenu();
			userMenu.showOptions();
		}
	}


    public void saveWatchedToFile() {
        try (FileWriter writer = new FileWriter("src/main/resources/watchlater.csv")) {

            for (Media media : watchedList) {
                // Converts Category[] → String
                StringBuilder categoryString = new StringBuilder();
                Category[] categories = media.getCategories();

                for (int i = 0; i < categories.length; i++) {
                    categoryString.append(categories[i].name());
                    if (i < categories.length - 1) {
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
                Category[] categories = media.getCategories();

                for (int i = 0; i < categories.length; i++) {
                    categoryString.append(categories[i].name());
                    if (i < categories.length - 1) {
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
        currentUser.getWatchLaterList().add(selectedMedia);
        // Save updated list to file
        currentUser.saveWatchLaterToFile();
        System.out.println(selectedMedia.getName() + " has been added to Watch Later list.");
    }

    public void removeFromWatchLater(Media selectedMedia) {
        // Remove the selected media from the watch later list
        currentUser.getWatchLaterList().remove(selectedMedia);
        // Save updated list to file
        currentUser.saveWatchLaterToFile();
        System.out.println(selectedMedia.getName() + " has been removed from Watch Later list.");
    }

    public void addToWatched(Media selectedMedia) {
        // Removes media from Watch Later list first
        currentUser.getWatchLaterList().remove(selectedMedia);
        // Adds media to Watched list
        currentUser.getWatchedList().add(selectedMedia);
        // Save updated lists to file
        currentUser.saveWatchLaterToFile();
        currentUser.saveWatchedToFile();
        System.out.println(selectedMedia.getName() + " has been added to Watched list.");
        System.out.println("Now playing: " + selectedMedia.getName());
    }
}
