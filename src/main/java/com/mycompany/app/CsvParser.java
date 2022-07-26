package com.mycompany.app;

import java.util.List;

public class CsvParser implements ParserInterface {

    private int[] assignMinMax(Integer min, Integer max, Integer currentValue) {
        if (min == null) min = currentValue;
        if (max == null) max = currentValue;

        min = Math.min(min, currentValue);
        max = Math.max(max, currentValue);

        return new int[]{ min, max};
    }

    public void parse(List<List<String>> csvData) {
        for (List<String> line: csvData) {
            if (line.isEmpty()) continue;

            System.out.print(line.get(0) + ' ');
            Integer min = null, max = null;
            for (int i=1; i<line.size(); i++) {
                String word = line.get(i);
                if (word.endsWith("m")) continue;

                int number;
                try {
                    number = Integer.parseInt(word);
                } catch (NumberFormatException e){
                    continue;
                }

                int[] result = assignMinMax(min, max, number);
                min = result[0];
                max = result[1];
            }

            System.out.println(
                    (min == null ? "no_value" : min.toString())
                            + ' ' + (max == null ? "no_value" : max.toString())
            );
        }
    }
}
