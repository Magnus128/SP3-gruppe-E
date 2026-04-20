import java.util.ArrayList;

public class StreamingService {
	private String name;
	private User currentUser;
	public static ArrayList<Movie> movies;
	public static ArrayList<Series> series;

	public String getName() {
		return name;
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public ArrayList<Movie> getMovies() {
		return movies;
	}

	public ArrayList<Series> getSeries() {
		return series;
	}

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
