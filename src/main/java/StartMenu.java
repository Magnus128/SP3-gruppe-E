import util.FileIO;
import util.TextUI;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class StartMenu implements Menu {
	TextUI ui = new TextUI();
	private StreamingService service;
	ArrayList<String> loginsInfo = FileIO.readData("src/main/resources/login.csv");

	public StartMenu(StreamingService service) {
		this.service = service;
	}

	@Override
	public void showOptions() {
		TextUI ui = new TextUI();
		int choice = ui.promptNumeric("1. Login" + "\n2. Create new user");
		if (choice == 1) {
			loginUser();
		} else if (choice == 2) {
			createUser();
		} else {
			System.out.println("Please enter a valid choice");
			showOptions();
		}
	}

	private void createUser() {

		// Brugen indtaster sin information
		ui.displayMsg(" --- Create User --- ");
		String username = ui.promptText("Username : ");
		String password = ui.promptText("Password : ");
		boolean isAdmin = ui.promptBinary("Are you an Admin? (y/n)");

		// Programmet skal kontrollere om, der findes samme navn i login.csv
		// Hvis der er et match, kan man ikke oprette ny bruger og sættes flag til false

		boolean flag = true;
		for (String info : loginsInfo) {
			String[] infoValues = info.trim().split(",");
			String loginCsvName = infoValues[0].trim();

			if (loginCsvName.equalsIgnoreCase(username)) {
				flag = false;
			}
		}

		// Hvis flaget er true, kan oprettes ny bruger
		if (flag) {

			// tilføjes til en Arraylist
			String newUser = username + ", " + password + ", " + isAdmin;
			loginsInfo.add(newUser);

			// gemmes i login.csv
			String header = "Username, Password, isAdmin";
			FileIO.saveData(loginsInfo, "src/main/resources/login.csv", header);

			if (isAdmin) {
				service.setCurrentUser(new Admin(service, username, password));
			} else {
				service.setCurrentUser(new User(service, username, password));
			}
			ui.displayMsg("A new user has been created.");

		} else {
			ui.displayMsg("There is a user with the same name. Please enter a different user.");
			createUser();
		}
	}

	private void loginUser() {

		// Brugen indtaster sin information
		ui.displayMsg(" --- Login --- ");
		String username = ui.promptText("Username : ");
		String password = ui.promptText("Password : ");

		// Programmet skal kontrollere om, der findes samme navn og adgangskode i login.csv
		// Hvis der findes et match, kan man logge ind. flaget sættes til true for at udkrive en besked

		boolean flag_login = false;
		for (String info : loginsInfo) {
			String[] infoValues = info.trim().split(",");
			String loginCsvName = infoValues[0].trim();
			String loginCsvPassword = infoValues[1].trim();
			boolean loginCsvAdminCheck = Boolean.parseBoolean(infoValues[2].trim());

			if (loginCsvName.equalsIgnoreCase(username) && loginCsvPassword.equalsIgnoreCase(password)) {
				if (loginCsvAdminCheck) {
					service.setCurrentUser(new Admin(service, username, password));
				} else {
					service.setCurrentUser(new User(service, username, password));
				}
				flag_login = true;
				break;
			}

		}


		if (flag_login) {
			ui.displayMsg("The user is logged in.");
			ArrayList<String> saveData = FileIO.readData("src/main/resources/userSaveData/" + username + ".csv");
			if (!saveData.isEmpty()) {
				for (String data : saveData) {
					String[] dataValues = data.trim().split(",");
					String watchedListEntry = dataValues[0].trim();
					String watchLaterListEntry = dataValues[1].trim();
					try {
						if (!watchedListEntry.equalsIgnoreCase("0")) {
							service.getCurrentUser().addToWatched(service.findMediaFromName(watchedListEntry));
						}
						if (!watchLaterListEntry.equalsIgnoreCase("0")) {
							service.getCurrentUser().addToWatchLater(service.findMediaFromName(watchLaterListEntry));
						}
					} catch(NullPointerException e) {
						System.out.println(e.getMessage());
					}
				}
			}
		} else {
			ui.displayMsg("User information could not be found.");
			loginUser();
		}

	}

}
