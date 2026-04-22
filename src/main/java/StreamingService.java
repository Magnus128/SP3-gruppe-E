import util.FileIO;

import java.util.ArrayList;

public class StreamingService {
	private String name;
	private User currentUser;
	private ArrayList<Movie> movies;
	private ArrayList<Series> series;

	public StreamingService() {
		movies = new ArrayList<>();
		series = new ArrayList<>();
	}

	public void startSession() {
		// Indlæser media
		loadMedia();

		// Viser StartMenu
		loadStartMenu();
	}

	public void runSession() {
		// Viser UserMenu
		loadUserMenu();
	}

	private void loadUserMenu() {
	}

	public void endSession() {
		// Gemmer data
		saveData();
	}

	private void saveData() {
	}

	public void loadMedia() {

		// Film
		ArrayList <String> filmInfos = FileIO.readData("src/main/resources/film.csv");

		for (String filmInfo : filmInfos ) {
			String [] filmInfoValues = filmInfo.trim().split(";");
			String filmName = filmInfoValues[0].trim();
			int filmReleaseYear = Integer.parseInt(filmInfoValues[1].trim());

			ArrayList<Category> filmcategoryList = new ArrayList<>();
			String [] filmCategoryValues = filmInfoValues[2].trim().split(",");
			for (String filmCategory : filmCategoryValues ) {
				filmcategoryList.add(Category.valueOf(filmCategory.trim().toUpperCase().replace("-","")));
			}

			double rating = Double.parseDouble(filmInfoValues[3].trim().replace(",", "."));

			movies.add(new Movie(filmName, filmReleaseYear, filmcategoryList, rating));

		}

		// Series
		ArrayList <String> seriesInfos = FileIO.readData("src/main/resources/serier.csv");

		for (String seriesInfo : seriesInfos) {
			String[] seriesInfoValues = seriesInfo.trim().split(";");
			String seriesName = seriesInfoValues[0].trim();

			String [] seriesYear = seriesInfoValues[1].trim().split("-");
			int seriesReleaseYear = Integer.parseInt(seriesYear[0].trim());
			// nogle værdier er tomme for endYear i serier.csv
			int seriesEndYear = 0;
			if (seriesYear.length == 2) {
				seriesEndYear = Integer.parseInt(seriesYear[1].trim());
			}

			ArrayList<Category> seriescategoryList = new ArrayList<>();
			String [] seriesCategoryValues = seriesInfoValues[2].trim().split(",");
			for (String seriesCategory : seriesCategoryValues ) {
				seriescategoryList.add(Category.valueOf(seriesCategory.trim().toUpperCase().replace("-","")));
			}

			double rating = Double.parseDouble(seriesInfoValues[3].trim().replace(",", "."));

			ArrayList<Season> seasonList = new ArrayList<>();
			String [] seasons = seriesInfoValues[4].trim().split(",");
			for (String s : seasons) {
				String [] sValues = s.split("-");
				int numberOfSeason = Integer.parseInt(sValues[0].trim());
				int episode = Integer.parseInt(sValues[1].trim());

				seasonList.add(new Season(numberOfSeason, episode));
			}

			series.add(new Series(seriesName, seriesReleaseYear, seriesEndYear, seriescategoryList, rating, seasonList));

		}

		// TEST
		/*
		for (Movie s : movies) {
			System.out.println(s);
		}
		for (Movie s : movies) {
			System.out.println(s);
		}
		*/


	}

	private void loadStartMenu() {
		//int choice = StartMenu.showOptions(); //1 = login  2 = create account
		

	}

}
