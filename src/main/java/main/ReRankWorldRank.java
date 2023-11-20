package main;

import java.sql.*;
import java.util.LinkedList;

public class ReRankWorldRank implements WorldPopulationOperation{

    ReRankWorldRank() {
        operate();
    }

    @Override
    public void operate() {
        String columnName = "2023_population";
        LinkedList<Integer> sortedRank = new LinkedList<>();
        try {
            PreparedStatement selectStatement = conn.prepareStatement("SELECT id, world_rank FROM population ORDER BY " + columnName + " DESC");
            ResultSet resultSet = selectStatement.executeQuery();
            while (resultSet.next()) {
                int value = resultSet.getInt("id");
                sortedRank.add(value);
            }
            PreparedStatement updateStatement = conn.prepareStatement("UPDATE population SET world_rank = ? WHERE id = ?");
            for (int i = 0; i < sortedRank.size(); i++) {
                updateStatement.setInt(1, i + 1);
                updateStatement.setInt(2, sortedRank.get(i));
                updateStatement.addBatch();
            }
            updateStatement.executeBatch();
            resultSet.close();
            selectStatement.close();
            System.out.println("========================================================");
            System.out.println("         The world rank re-ranked successfully.         ");
            System.out.println("========================================================");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
