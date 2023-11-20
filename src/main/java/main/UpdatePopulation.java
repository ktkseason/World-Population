package main;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.InputMismatchException;

public class UpdatePopulation implements WorldPopulationOperation{

    UpdatePopulation() {
        operate();
    }

    @Override
    public void operate() {
        String year, isoName = "";
        int newPopulation = 0;
        do {
            System.out.print("Enter year (2020, 2021, 2022 or 2023): ");
            year = sc.nextLine();
        } while(!check.checkYear(year));
        String updateColumn = "" + year + "_population";
        do {
            System.out.print("Enter country ISO name: ");
            isoName = sc.nextLine();
        } while(!check.checkISO(isoName));
        boolean inputStatus = false;
        while(!inputStatus) {
            try {
                System.out.print("Enter update population amount: ");
                newPopulation = sc.nextInt();
                inputStatus = true;
            }
            catch(InputMismatchException e) {
                System.out.println("Input population cannot be exceeded from 2147483647.");
                sc.nextLine();
            }
        }

        try {
            PreparedStatement updateStatement = conn.prepareStatement("UPDATE population SET " + updateColumn + " = ? WHERE iso_name = ?");

            updateStatement.setInt(1, newPopulation);
            updateStatement.setString(2, isoName.toUpperCase());
            updateStatement.addBatch();

            updateStatement.executeBatch();
            updateStatement.close();

            System.out.println("========================================================");
            System.out.println("         Population data updated successfully.          ");
            System.out.println("========================================================");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
