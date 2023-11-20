package main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckTestIT {
    Check check;

    @BeforeEach
    void setUp() {
        check = new Check();
    }

    @Test
    void checkISO() {
        assertAll(
                () -> assertTrue(check.checkISO("Ind")),
                () -> assertTrue(check.checkISO("CHN")),
                () -> assertFalse(check.checkISO("India")),
                () -> assertFalse(check.checkISO("China"))
        );
    }

    @Test
    void checkColumn() {
        String msg = "========================================================\n" +
                "                Column is already added.                \n" +
                "========================================================";
        Exception exception = assertThrows(Exception.class, () -> check.checkColumn("2024_population"));
        assertEquals(msg, exception.getMessage());
    }
}