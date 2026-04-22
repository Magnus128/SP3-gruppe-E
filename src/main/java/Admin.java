public class Admin extends User {

    static final boolean isAdmin = true;

    public Admin(String userName, String password){
        super(userName, password);
    }

    public void showMenu(){
        AdminMenu adminMenu = new AdminMenu(service);
        adminMenu.showOptions();
    }

}
