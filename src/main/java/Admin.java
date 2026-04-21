import java.util.ArrayList;

public class Admin extends User {
	public Admin(String userName, String password, ArrayList<Media> watchedList, ArrayList<Media> watchLaterList) {
		super(userName, password, watchedList, watchLaterList);
	}
}
