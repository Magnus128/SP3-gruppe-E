import java.util.ArrayList;
import java.util.Scanner;
public class AdminMenu extends UserMenu {

    private StreamingService service;
    Scanner scanner = new Scanner(System.in);

    public AdminMenu(StreamingService service) {
        super(service);
        this.service = service;

    }

    @Override
    public void showOptions() {
        int choice = 1;
        while (choice != 0) {

            System.out.println("\n=== Admin Menu ===");
            System.out.println("1. Søg efter film eller serie");
            System.out.println("2. Søg i kategori");
            System.out.println("3. Vis set medier");
            System.out.println("4. Vis gemt til senere");
            System.out.println("5. Tilføj nyt medie");
            System.out.println("6. Fjern medie");
            System.out.println("0. Log ud");
            System.out.print("Valg: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> searchMedia();
                case 2 -> searchAllinCategory();
                case 3 -> showWatched();
                case 4 -> showWatchLater();
                case 5 -> addMedia();
                case 6 -> removeMedia();
                case 0 -> System.out.println("Logger ud...");
                default -> System.out.println("Ugyldigt valg.");
            }
        }
    }

    public void addMedia () {


        System.out.println("\n ---Tilføj nyt medie---");
        System.out.println("1. Movie ");
        System.out.println("2. Serie ");

        int type = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Navn: ");
        String name = scanner.nextLine();

        System.out.println("Udgivelsesår: ");
        int releaseYear = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Rating: ");
        double rating = scanner.nextDouble();
        scanner.nextLine();

        Category[] categories = {};

        if (type == 1) {
            Movie movie = new Movie(name, releaseYear, categories, rating);
            service.getMovies().add(movie);
            service.saveData();
            System.out.println("Movie: " + name + "tilføjet");

        } else if (type == 2) {
            System.out.println("Slutår: ");
            int endYear = scanner.nextInt();
            scanner.nextLine();

            Series series = new Series(name, releaseYear, endYear, categories, rating, new ArrayList<>());
            service.getSeries().add(series);
            service.saveData();
            System.out.println("Serien: " + name + "tilføjet");

        } else {
            System.out.println("ugyldigt valg!");
        }

    }

    public void removeMedia(){

        System.out.println("---Fjern medie---");
        System.out.println("Indtast navn på mediet der skal fjernes: ");
        String name = scanner.nextLine();

        boolean removed = false;

        removed = service.getMovies().removeIf(m->m.getName().equalsIgnoreCase(name));

        if(!removed){
            removed = service.getSeries().removeIf(s->s.getName().equalsIgnoreCase(name));
        }

        if(removed){
            service.saveData();
            System.out.println( name + "er fjernet");
        }else{
            System.out.println("Kunne ikke finde " + name);
        }



    }

}




