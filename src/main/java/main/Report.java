package main;

import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Report implements WorldPopulationOperation{

    Report() {
        operate();
    }

    private int reportMenu() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("                       Report Menu                      ");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("1. Top ten most populated countries");
        System.out.println("2. Top ten largest countries with population growth rate");
        System.out.println("========================================================");

        String option;
        boolean optionStatus = false;
        do {
            System.out.print("Enter report number 1 or 2: ");
            option = sc.nextLine();
        } while(!check.checkReportOption(option));
        return Integer.parseInt(option);
    }

    @Override
    public void operate() {
        switch(reportMenu()) {
            case 1: reportMostPopulated(); break;
            case 2: reportLargestCountry(); break;
        }
    }

    private void reportMostPopulated() {
        String columnName = "2023_population";
        LinkedList<MostPopulatedReport> mostPopulated = new LinkedList<>();

        try {
            PreparedStatement selectStatement = conn.prepareStatement("SELECT country_name, iso_name, " + columnName + ", world_rank FROM " +
                            "population ORDER BY " + columnName + " DESC");
            ResultSet resultSet = selectStatement.executeQuery();

            for(int i = 0; i < 10; i++) {
                if (resultSet.next()) {
                    mostPopulated.add(new MostPopulatedReport(resultSet.getString("country_name"), resultSet.getString(
                            "iso_name"), resultSet.getInt(columnName), resultSet.getInt("world_rank")));

                }
            }

            List<String> header = Arrays.asList("Country Name", "ISO Name", "Latest Population", "World Rank");
            AsciiTable at = new AsciiTable();
            at.addRule();
            at.addRow(header);
            at.addRule();
            for(MostPopulatedReport m: mostPopulated) {
                List<Object> data = Arrays.asList(m.getCountryName(), m.getIsoName(), m.getLatestPopulation(), m.getWorldRank());
                at.addRow(data);
                at.addRule();
            }
            at.setTextAlignment(TextAlignment.CENTER);
            System.out.println(at.render());
            resultSet.close();
            selectStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void reportLargestCountry() {
        String originalColumnName = "2022_population";
        String newColumnName = "2023_population";
        LinkedList<LargestCountryReport> largestCountry = new LinkedList<>();

        try {
            PreparedStatement selectStatement = conn.prepareStatement("SELECT country_name, iso_name, area_sq_km, " + originalColumnName + ", "
                    + newColumnName + ", world_rank FROM population ORDER BY area_sq_km DESC");
            ResultSet resultSet = selectStatement.executeQuery();
            for(int i = 0; i < 10; i++) {
                if (resultSet.next()) {
                    largestCountry.add(new LargestCountryReport(resultSet.getString("country_name"), resultSet.getString(
                            "iso_name"), resultSet.getInt("area_sq_km"),
                            resultSet.getInt(originalColumnName),
                            resultSet.getInt(newColumnName), resultSet.getInt("world_rank")));
                }
            }

            List<String> header = Arrays.asList("Country Name", "ISO Name", "Total Area", "Growth Rate", "World Rank");
            AsciiTable at = new AsciiTable();
            at.addRule();
            at.addRow(header);
            at.addRule();
            for(LargestCountryReport l: largestCountry) {
                List<Object> data = Arrays.asList(l.getCountryName(), l.getIsoName(), l.getTotalArea(), l.getGrowthRate() + "%", l.getWorldRank());
                at.addRow(data);
                at.addRule();
            }
            at.setTextAlignment(TextAlignment.CENTER);
            System.out.println(at.render());
            resultSet.close();
            selectStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
