import java.util.ArrayList;

public class Series extends Media {

    private int endYear;
    private ArrayList<Season> seasons;

    public Series(String name, int releaseYear, int endYear, ArrayList<Category> categories, double rating, ArrayList<Season> seasons) {
        super(name, releaseYear, categories, rating);
        this.endYear = endYear;
        this.seasons = seasons;
    }

    @Override
    public String toString() {
        return getName() + ", "
                + getReleaseYear() + ", "
                + endYear + ", "
                + getCategories() + ","
                + getRating() + ", "
                + seasons;
    }
}
