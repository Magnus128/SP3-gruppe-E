import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class User {
	private String userName;
	private String password;
	private ArrayList<Media> watchedList;
	private ArrayList<Media> watchLaterList;

	public User(String userName, String password, ArrayList<Media> watchedList, ArrayList<Media> watchLaterList) {
		this.userName = userName;
		this.password = password;
		this.watchedList = watchedList;
		this.watchLaterList = watchLaterList;
	}

	public void showMenu() {
		if (this instanceof Admin) {
			var adminMenu = new AdminMenu();
			adminMenu.showOptions();
		} else {
			var userMenu = new UserMenu();
			userMenu.showOptions();
		}
	}

    public void loadWatchedFromFile() {
        watchedList.clear(); // Avoids duplicates when reloading

        try(BufferedReader reader = new BufferedReader(
                new FileReader("src/main/resources/watched.csv"))){

            String name;
            while ((name = reader.readLine()) != null){
                if (!name.isBlank()){
                    Media media = new Media(name);
                    watchedList.add(media);
                }
            }
        }catch (IOException e){
            System.out.println("No watched file found yet.");
        }
    }

    public void loadWatchLaterFromFile(){
        watchLaterList.clear();

        try(BufferedReader reader = new BufferedReader(
                new FileReader("src/main/rescources/watchlater.csv"))){

            String name;
            while((name = reader.readLine()) != null){
                if(!name.isBlank()){
                    Media media = new Media(name);
                    watchLaterList.add(media);
                }
            }
        }catch (IOException e){
            System.out.println("No watch later file found yet");
        }
    }

    public void saveWatchedToFile(){
        try(FileWriter writer = new FileWriter("src/main/resources/watched.csv")){

            for(Media media : watchedList){
                writer.write(media.getName() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveWatchLaterToFile(){
        try(FileWriter writer = new FileWriter("src/main/resources/watchlater.csv")){

            for(Media media : watchLaterList){
                writer.write(media.getName() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeFromWatchLater(Media selectedMedia) {
    }

    public void addToWatchLater(Media selectedMedia) {
    }

    public void addToWatched(Media selectedMedia) {
    }
}
