package com.mycompany.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CsvReader implements ReaderInterface {

    public List<List<String>> read(Path path) {
        List<List<String>> csvData = new ArrayList<>();
        try (BufferedReader bufferedReader = Files.newBufferedReader(path, CHARSET)) {
            List<String> current;
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                current = Arrays.stream(line.split(DELIMITER))
                        .map(String::trim)
                        .collect(Collectors.toCollection(ArrayList::new));
                csvData.add(current);
            }
        } catch (IOException e) {
            System.out.println("Could not read file with path: " + path);
        }

        return Collections.unmodifiableList(csvData);
    }
}
