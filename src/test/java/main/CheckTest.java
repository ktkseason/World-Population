package main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckTest {
    Check check;

    @BeforeEach
    void setUp() {
        check = new Check();
    }

    @Test
    void checkMenuOption() {
        assertAll(
                () -> assertTrue(check.checkMenuOption("1")),
                () -> assertTrue(check.checkMenuOption("3")),
                () -> assertFalse(check.checkMenuOption("9")),
                () -> assertFalse(check.checkMenuOption("menu"))
        );
    }

    @Test
    void checkReportOption() {
        assertAll(
                () -> assertTrue(check.checkReportOption("1")),
                () -> assertTrue(check.checkReportOption("2")),
                () -> assertFalse(check.checkReportOption("3")),
                () -> assertFalse(check.checkReportOption("menu"))
        );
    }

    @Test
    void checkYear() {
        assertAll(
                () -> assertTrue(check.checkYear("2020")),
                () -> assertTrue(check.checkYear("2023")),
                () -> assertFalse(check.checkYear("2000")),
                () -> assertFalse(check.checkYear("Two thousand"))
        );
    }
}