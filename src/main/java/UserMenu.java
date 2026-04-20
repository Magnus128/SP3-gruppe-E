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
}
