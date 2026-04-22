public class Season {

    private int season;
    private int numberOfEpisodes;

    public Season(int season, int numberOfEpisodes) {
        this.season = season;
        this.numberOfEpisodes = numberOfEpisodes;
    }

    @Override
    public String toString() {
        return season + "-" + numberOfEpisodes;
    }

}
