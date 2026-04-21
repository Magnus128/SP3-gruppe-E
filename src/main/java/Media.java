public class Media {
	private String name;
	private int releaseYear;
	private Category[] categories;
	private double rating;

    public Media(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Category[] getCategories() {
        return categories;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public double getRating() {
        return rating;
    }
}
