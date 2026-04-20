import java.util.ArrayList;

public abstract class Media {

    private String name;
    private int releaseYear;
    private ArrayList<String> categories;
    private double rating;

    public Media(String name, int releaseYear, ArrayList<String> categories, double rating) {
        this.name = name;
        this.releaseYear = releaseYear;
        this.categories = categories;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public ArrayList<String> getCategories() {
        return categories;
    }

    public double getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return name + "," + releaseYear + ", " + categories + ", " + rating;
    }
}