package main;

public class MostPopulatedReport {
    private String countryName;
    private String isoName;
    private int latestPopulation;
    private int worldRank;

    public MostPopulatedReport(String countryName, String isoName, int latestPopulation, int worldRank) {
        this.countryName = countryName;
        this.isoName = isoName;
        this.latestPopulation = latestPopulation;
        this.worldRank = worldRank;
    }
    public String getCountryName() {
        return countryName;
    }
    public String getIsoName() {
        return isoName;
    }
    public int getLatestPopulation() {
        return latestPopulation;
    }
    public int getWorldRank() {
        return worldRank;
    }
}

