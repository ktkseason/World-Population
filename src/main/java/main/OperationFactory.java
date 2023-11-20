package main;

import java.sql.Connection;
import java.sql.SQLException;

public class OperationFactory {

    private Connection conn = DBConnect.getConn();

    public WorldPopulationOperation getOperation(int option) {
        if (option == 1)
            return new InsertNewPopulation();
        else if(option == 2)
            return new UpdatePopulation();
        else if(option == 3)
            return new ReRankWorldRank();
        else if(option == 4)
            return new Report();
        else if(option == 5) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            System.exit(0);
        }
        return null;
    }
}
