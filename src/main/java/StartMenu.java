import util.FileIO;
import util.TextUI;

import java.util.ArrayList;

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
			String newUser = username + ", " + password;
			loginsInfo.add(newUser);

			// gemmes i login.csv
			String header = "Username, Password";
			FileIO.saveData(loginsInfo, "src/main/resources/login.csv", header);

			service.setCurrentUser(new User(service, username, password));
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

			if (loginCsvName.equalsIgnoreCase(username) && loginCsvPassword.equalsIgnoreCase(password)) {
				service.setCurrentUser(new User(service, username, password));
				flag_login = true;
				break;
			}
		}

		if (flag_login) {
			ui.displayMsg("The user is logged in.");
		} else {
			ui.displayMsg("User information could not be found.");
		}

	}

}
