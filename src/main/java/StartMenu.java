import util.TextUI;
public class StartMenu {
	public static int showOptions() {
		TextUI ui = new TextUI();
		int choice = ui.promptNumeric("1. Login" + "\n2. Create new user");
		if (choice == 1) {
			loginUser();
		} else if (choice == 2) {
			createUser();
		}
		return choice;
	}

	private static void createUser() {
	}

	private static void loginUser() {
	}
}
