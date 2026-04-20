import util.TextUI;
public class UserMenu implements Menu {
	@Override
	public void showOptions() {
		var ui = new TextUI();
		int choice = ui.promptNumeric("1. Search for media \n2. " +
				"Search media in category \n3. Previously watched \n4. Watch later");
		switch (choice) {
			case 1:
				// Search
				String input = ui.promptText("Write the name of a movie of series: ");
				Media media = findMedia(input);
				if (!(media == null)) {
					var mediaMenu = new MediaMenu(media);
					// mediaMenu.showOptions();
				} else {
					System.out.println("Media not found, try again: ");
					showOptions();
				}
				break;
			case 2:
				// All in specific category
				break;
			case 3:
				// Previously watched
				break;
			case 4:
				// Watch later list
				break;
			default:
				System.out.println("\nTry again:");
				showOptions();
		}
	}

	private Media findMedia (String mediaName) {
		for (Movie movie : StreamingService.movies) {
			if (mediaName.equalsIgnoreCase(movie.getName())) {
				return movie;
			}
		}
		for (Series series : StreamingService.series) {
			if (mediaName.equalsIgnoreCase(series.getName())) {
				return series;
			}
		}
		return null;
	}
}
