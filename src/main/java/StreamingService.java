import java.util.ArrayList;

public class StreamingService {
	private String name;
	private User currentUser;
	private ArrayList<Movie> movies;
	private ArrayList<Series> series;

	public void startSession() {
		// Indlæser media
		loadMedia();

		// Viser StartMenu
		loadStartMenu();
	}

	public void runSession() {
		// Viser UserMenu
		loadUserMenu();
	}

	private void loadUserMenu() {
	}

	public void endSession() {
		// Gemmer data
		saveData();
	}

	private void saveData() {
	}

	private void loadMedia() {

	}

	private void loadStartMenu() {

	}
}
