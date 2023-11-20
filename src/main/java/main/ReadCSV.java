package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;

public class ReadCSV {
    public static LinkedList<Population> readData(String filepath) {
        LinkedList<Population> latestPopulation = new LinkedList<>();
        try {
            FileReader fr = new FileReader(filepath);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while((line = br.readLine()) != null) {
                String[] data = line.split(",");
                latestPopulation.add(new Population(data[0], Integer.parseInt(data[1])));
            }
            br.close();
            fr.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return latestPopulation;
    }
}
