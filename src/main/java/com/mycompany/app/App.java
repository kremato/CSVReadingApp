package com.mycompany.app;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        if (args.length != 1) {
            System.out.println(
                    "Application takes one argument and that is the CSV file ABSOLUTE path.\n" +
                            "Type 'help' to get more info.");
            return;
        }

        if (Objects.equals(args[0], "help")) {
            System.out.println(
                    "Usage:\n" +
                            "program /this/is/an/absolute/path.csv"
            );
            return;
        }

        CsvReader reader = new CsvReader();
        CsvParser parser = new CsvParser();
        Path path = Paths.get(args[0]);
        parser.parse(reader.read(path));
    }
}
