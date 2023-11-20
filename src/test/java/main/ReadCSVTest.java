package main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class ReadCSVTest {

    ReadCSV readCSV;

    @BeforeEach
    void setUp() {
        readCSV = new ReadCSV();
    }

    @Test
    void readData() throws IOException {
        LinkedList<Population> populations = readCSV.readData("data/new_population.csv");
        assertEquals(213, populations.size());
    }
}