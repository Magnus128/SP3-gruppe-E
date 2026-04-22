import util.TextUI;
import java.util.ArrayList;
import java.util.Arrays;

public class UserMenu implements Menu {
	private StreamingService service;
	private TextUI ui;

	public UserMenu(StreamingService service) {
		this.service = service;
		ui = new TextUI();

	}

	public void showOptions() {
		int choice = ui.promptNumeric("1. Search for media \n2. " +
				"Search media in category \n3. Previously watched \n4. Watch later");
		switch (choice) {
			case 1:
				// Search
				searchMedia();
				break;
			case 2:
				// All in specific category
				searchAllinCategory();
				break;
			case 3:
				// Previously watched
				showWatched();
				break;
			case 4:
				// Watch later list
				showWatchLater();
				break;
			default:
				System.out.println("Try again:");
				showOptions();
		}
	}

	protected void searchMedia() {
		String input = ui.promptText("Write the name of a movie of series: ");
		Media media = findMediaFromName(input);
		if (!(media == null)) {
			var mediaMenu = new MediaMenu(service, media);
			mediaMenu.showOptions();
		} else {
			System.out.println("Media not found, try again: ");
			showOptions();
		}
	}

	protected void searchAllinCategory() {
		System.out.println("""
						1. Action	2. Adventure	3. Biography\s
						4. Comedy	5. Crime	6. Drama\s
						7. Family	8. Fantasy	9. Film-Noir\s
						10. History	11. Horror	12. Music\s
						13. Musical	14. Mystery	15. Romance\s
						16. Sci-fi	17. Sport	18. Thriller\s
						19. War	20. Western
						""");
		int categoryChoice = ui.promptNumeric("Choose a category: ");
		Category chosenCategory = chooseCategory(categoryChoice);

		ArrayList<Media> allInCategory = listAllInCategory(chosenCategory);
		try {
			var mediaMenu = new MediaMenu(service, chooseMedia(allInCategory));
			mediaMenu.showOptions();
		} catch (IndexOutOfBoundsException e) {
			System.out.println(e.getMessage());
			System.out.println("Try again: ");
			showOptions();
		}
	}

	protected void showWatched() {
		int counter = 1;
		for (Media watchedMedia : service.getCurrentUser().getWatchedList()) {
			System.out.println(counter + ". " + watchedMedia.getName());
			counter++;
		}
		try {
			var mediaMenu = new MediaMenu(service, chooseMedia(service.getCurrentUser().getWatchedList()));
			mediaMenu.showOptions();
		} catch (IndexOutOfBoundsException e) {
			System.out.println(e.getMessage());
			System.out.println("Try again: ");
			showOptions();
		}
	}

	protected void showWatchLater() {
		int counter = 1;
		for (Media watchLaterMedia : service.getCurrentUser().getWatchLaterList()) {
			System.out.println(counter + ". " + watchLaterMedia.getName());
			counter++;
		}
		try {
			var mediaMenu = new MediaMenu(service, chooseMedia(service.getCurrentUser().getWatchLaterList()));
			mediaMenu.showOptions();
		} catch (IndexOutOfBoundsException e) {
			System.out.println(e.getMessage());
			System.out.println("Try again: ");
			showOptions();
		}
	}

	private Media chooseMedia(ArrayList<Media> mediaList) throws IndexOutOfBoundsException {
		var ui = new TextUI();
		int choice = ui.promptNumeric("Choose a movie or series from the list: ");
		return mediaList.get(choice - 1);
	}

	private ArrayList<Media> listAllInCategory(Category chosenCategory) {
		int counter = 1;
		ArrayList<Media> mediaList = new ArrayList<>();
		for (Movie movie : service.getMovies()) {
			if (Arrays.asList(movie.getCategories()).contains(chosenCategory)) {
				System.out.println(counter + ". " + movie.getName());
				mediaList.add(movie);
				counter++;
			}
		}
		for (Series series : service.getSeries()) {
			if (Arrays.asList(series.getCategories()).contains(chosenCategory)) {
				System.out.println(counter + ". " + series.getName());
				mediaList.add(series);
				counter++;
			}
		}
		return mediaList;
	}

	private Category chooseCategory(int choice) {
		switch (choice) {
			case 1:
				return Category.ACTION;
			case 2:
				return Category.ADVENTURE;
			case 3:
				return Category.BIOGRAPHY;
			case 4:
				return Category.COMEDY;
			case 5:
				return Category.CRIME;
			case 6:
				return Category.DRAMA;
			case 7:
				return Category.FAMILY;
			case 8:
				return Category.FANTASY;
			case 9:
				return Category.FILMNOIR;
			case 10:
				return Category.HISTORY;
			case 11:
				return Category.HORROR;
			case 12:
				return Category.MUSIC;
			case 13:
				return Category.MUSICAL;
			case 14:
				return Category.MYSTERY;
			case 15:
				return Category.ROMANCE;
			case 16:
				return Category.SCIFI;
			case 17:
				return Category.SPORT;
			case 18:
				return Category.THRILLER;
			case 19:
				return Category.WAR;
			case 20:
				return Category.WESTERN;
			case 21:
				return Category.ANIMATION;
			case 22:
				return Category.DOCUMENTARY;
			case 23:
				return Category.TALKSHOW;
			default:
				return null;
		}
	}

	private Media findMediaFromName(String mediaName) {
		for (Movie movie : service.getMovies()) {
			if (mediaName.equalsIgnoreCase(movie.getName())) {
				return movie;
			}
		}
		for (Series series : service.getSeries()) {
			if (mediaName.equalsIgnoreCase(series.getName())) {
				return series;
			}
		}
		return null;
	}
}