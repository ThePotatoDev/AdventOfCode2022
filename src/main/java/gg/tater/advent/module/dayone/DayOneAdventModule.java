package gg.tater.advent.module.dayone;

import gg.tater.advent.AdventOfCode;
import gg.tater.advent.module.AdventModule;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

public class DayOneAdventModule extends AdventModule {

    private final Map<UUID, Integer> elfCalorieMap = new HashMap<>();

    @Override
    public String getId() {
        return "day_one";
    }

    @Override
    public void init() {
        try {
            URL url = AdventOfCode.class.getClassLoader().getResource("elves.txt");
            if (url == null) {
                AdventOfCode.LOGGER.severe("Could not find resource for elves data file.");
                return;
            }

            File file = new File(url.toURI());
            if (!file.exists()) {
                AdventOfCode.LOGGER.severe("Could not find elves data file. It does not exist.");
                return;
            }

            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                int totalCalories = 0;

                for (String line : reader.lines().toList()) {

                    // If the line is empty, we break this elf's calorie count and move on
                    if (line.isEmpty()) {
                        UUID id = UUID.randomUUID();
                        elfCalorieMap.put(id, totalCalories);
                        totalCalories = 0;
                        continue;
                    }

                    int nextToAdd = Integer.parseInt(line);
                    totalCalories += nextToAdd;
                }

                Map.Entry<UUID, Integer> bestElfEntry = Collections.max(elfCalorieMap.entrySet(), Comparator.comparingInt(Map.Entry::getValue));
                if (bestElfEntry != null) {
                    AdventOfCode.LOGGER.info("[Day One] [Part One] Elf (" + bestElfEntry.getKey() + ") has most calories with value: " + bestElfEntry.getValue());
                }

                int totalCaloriesHeldByTop3 = elfCalorieMap.entrySet()
                        .stream()
                        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                        .limit(3L)
                        .mapToInt(Map.Entry::getValue)
                        .sum();

                AdventOfCode.LOGGER.info("[Day One] [Part Two] The top three elves are holding " + totalCaloriesHeldByTop3 + " total calories.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
