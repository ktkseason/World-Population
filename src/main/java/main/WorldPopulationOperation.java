package main;

import java.sql.Connection;
import java.util.Scanner;

public interface WorldPopulationOperation {
    Connection conn = DBConnect.getConn();
    Scanner sc = new Scanner(System.in);
    Check check = new Check();
    void operate();
}

