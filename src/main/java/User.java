import java.util.ArrayList;

public class User {

    private String username;
    private String password;
    private ArrayList<Media> watchedList;
    private ArrayList<Media> watchLaterList;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return username + ", " + password;
    }
}
