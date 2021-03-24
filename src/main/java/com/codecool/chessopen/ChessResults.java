package com.codecool.chessopen;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class ChessResults {

    public List<String> getCompetitorsNamesFromFile(String fileName){
        Map<String, Integer> totalScoresPerCompetitor = new HashMap<>();
        File file = new File(fileName);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                totalScoresPerCompetitor.put(data[0], calculateTotalScore(data));
            }
        } catch (Exception e) {
            System.out.println("File not found!");
        }
        return totalScoresPerCompetitor.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(entry -> entry.getKey())
                .collect(Collectors.toList());
    }

        private static int calculateTotalScore (String[] data) {
        int sum = 0;
        for (int i = 1; i < data.length; i++) {
            sum += Integer.parseInt(data[i]);
        }
        return sum;
    }
}
