package main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class InsertNewPopulationTestIT {

    Connection conn;

    @BeforeEach
    void setUp() {
        conn = DBConnect.getConn();
    }

    @Test
    void operate() {
        new InsertNewPopulation();
        Check check = new Check();
        String msg = "========================================================\n" +
                "                Column is already added.                \n" +
                "========================================================";
        Exception exception = assertThrows(Exception.class, () -> check.checkColumn("2024_population"));
        assertEquals(msg, exception.getMessage());
    }
}