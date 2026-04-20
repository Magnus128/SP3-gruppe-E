public abstract class Media {
	private String name;
	private int releaseYear;
	private Category[] categories;
	private double rating;

	public Media(String name, int releaseYear, Category[] categories, double rating) {
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

	public Category[] getCategories() {
		return categories;
	}

	public double getRating() {
		return rating;
	}
}
