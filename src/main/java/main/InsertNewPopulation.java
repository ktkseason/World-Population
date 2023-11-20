package main;

import java.sql.*;
import java.util.LinkedList;

public class InsertNewPopulation implements WorldPopulationOperation{

    InsertNewPopulation() {
        operate();
    }

    @Override
    public void operate() {
        String filepath = "data/new_population.csv";
        LinkedList<Population> latestPopulation = ReadCSV.readData(filepath);
        String newColumnName = "2024_population";
        try {
            if(check.checkColumn(newColumnName)) {
                try {
                    PreparedStatement alterStatement = conn.prepareStatement("ALTER TABLE population ADD " + newColumnName + " int(11) NOT NULL");
                    alterStatement.executeUpdate("ALTER TABLE population ADD " + newColumnName + " int(11) NOT NULL");
                    PreparedStatement updateStatement = conn.prepareStatement("UPDATE population SET " + newColumnName + " = ? WHERE country_name = ?");
                    for (Population p: latestPopulation) {
                        updateStatement.setInt(1, p.getPopulation());
                        updateStatement.setString(2, p.getCountryName());
                        updateStatement.addBatch();
                    }
                    updateStatement.executeBatch();
                    alterStatement.close();
                    System.out.println("========================================================");
                    System.out.println("        New population data added successfully.         ");
                    System.out.println("========================================================");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
