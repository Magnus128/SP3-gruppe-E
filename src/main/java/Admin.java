public class Admin extends User {
	public Admin(StreamingService service, String userName, String password) {
		super(service, userName, password);
	}

	@Override
	public void showMenu() {
		var adminMenu = new AdminMenu(super.service);
		adminMenu.showOptions();
	}
}
