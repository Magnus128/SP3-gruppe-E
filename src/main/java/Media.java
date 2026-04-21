import java.util.ArrayList;

public abstract class Media {

    private String name;
    private int releaseYear;
    private ArrayList<Category> categories;
    private double rating;

    public Media(String name, int releaseYear, ArrayList<Category> categories, double rating) {
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

    public ArrayList<Category> getCategories() {
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