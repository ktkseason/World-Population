package main;

import java.text.DecimalFormat;

public class LargestCountryReport {
    private DecimalFormat df = new DecimalFormat("0.00");
    private String countryName;
    private String isoName;
    private int totalArea;
    private double growthRate;
    private int worldRank;

    public LargestCountryReport(String countryName, String isoName, int totalArea, int originalPopulation, int newPopulation, int worldRank) {
        this.countryName = countryName;
        this.isoName = isoName;
        this.totalArea = totalArea;
        this.growthRate = Double.parseDouble(df.format((double) (newPopulation - originalPopulation) / originalPopulation * 100));
        this.worldRank = worldRank;
    }
    public String getCountryName() {
        return countryName;
    }
    public String getIsoName() {
        return isoName;
    }
    public int getTotalArea() {
        return totalArea;
    }
    public double getGrowthRate() {
        return growthRate;
    }
    public int getWorldRank() {
        return worldRank;
    }
}

