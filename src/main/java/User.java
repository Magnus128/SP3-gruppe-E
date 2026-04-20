import java.util.ArrayList;

public class User {
	private String userName;
	private String password;
	private ArrayList<Media> watchedList;
	private ArrayList<Media> watchLaterList;

	public User(String userName, String password, ArrayList<Media> watchedList, ArrayList<Media> watchLaterList) {
		this.userName = userName;
		this.password = password;
		this.watchedList = watchedList;
		this.watchLaterList = watchLaterList;
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

    public void removeFromWatchLater(Media selectedMedia) {
    }

    public void addToWatchLater(Media selectedMedia) {
    }

    public void addToWatched(Media selectedMedia) {
    }
}
