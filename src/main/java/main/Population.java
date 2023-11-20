package main;

public class Population {
    private String countryName;
    private int population;
    public Population(String countryName, int population) {
        this.countryName = countryName;
        this.population = population;
    }
    public String getCountryName() {
        return countryName;
    }
    public int getPopulation() {
        return population;
    }
}
