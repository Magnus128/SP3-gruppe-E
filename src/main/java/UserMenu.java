import util.TextUI;
import java.util.ArrayList;
import java.util.Arrays;

public class UserMenu implements Menu {
	private ArrayList<Media> watchedList;
	private ArrayList<Media> watchLaterList;

	public UserMenu(ArrayList<Media> watchedList, ArrayList<Media> watchLaterList) {
		this.watchedList = watchedList;
		this.watchLaterList = watchLaterList;
	}

	public void showOptions() {

	}
}
