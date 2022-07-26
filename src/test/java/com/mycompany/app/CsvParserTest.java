package com.mycompany.app;

import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.After;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

@ExtendWith(SoftAssertionsExtension.class)
public class CsvParserTest {

    @InjectSoftAssertions
    private SoftAssertions softly;

    private final CsvParser csvParser = new CsvParser();
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    @DisplayName("Load empty list")
    public void testEmptyList() {
        List<List<String>> data = List.of();

        csvParser.parse(data);
        softly.assertThat(outContent.toString()
                        .replaceAll("\\r\\n?", "\n"))
                        .containsSequence("");
    }

    @Test
    @DisplayName("Load Reader example output")
    public void testExampleOutput() {
        List<List<String>> data = List.of(
                List.of("Adam", "2m", "20", "1m", "20", "5", "10", "500", "15", "25"),
                List.of("Fero", "1m", "8m", "2m", "30", "50", "20", "100", "5m", "10"),
                List.of("Dusan", "20", "5m", "10", "2m", "1m", "50", "70", "25", "12", "4m"),
                List.of("Brano", "2222", "2m", "22", "11", "111", "81", "14"));

        csvParser.parse(data);
        softly.assertThat(outContent.toString()
                .replaceAll("\\r\\n?", "\n"))
                .containsSequence(
                        "Adam 5 500\n" +
                                "Fero 10 100\n" +
                                "Dusan 10 70\n" +
                                "Brano 11 2222\n");
    }

    @Test
    @DisplayName("Load input with empty string")
    public void testWithEmptyString() {
        List<List<String>> data = List.of(List.of("TK", "", "50m", "50"));

        csvParser.parse(data);
        softly.assertThat(outContent.toString()
                .replaceAll("\\r\\n?", "\n"))
                .containsSequence("TK 50 50\n");
    }

    @Test
    @DisplayName("Load no data to display for a person.")
    public void testNoData() {
        List<List<String>> data = List.of(List.of("Adam", "", "50m", "50m"));

        csvParser.parse(data);
        softly.assertThat(outContent.toString()
                        .replaceAll("\\r\\n?", "\n"))
                .containsSequence("Adam no_value no_value\n");
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }
}