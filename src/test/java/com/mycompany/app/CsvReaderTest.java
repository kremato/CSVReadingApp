package com.mycompany.app;

import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@ExtendWith(SoftAssertionsExtension.class)
public class CsvReaderTest {

    Path path;

    @InjectSoftAssertions
    private SoftAssertions softly;

    private CsvReader csvReader;
    @BeforeEach
    public void setUp() {
        csvReader = new CsvReader();
    }

    @Test
    @DisplayName("Load empty CSV file")
    public void testEmptyFile() throws URISyntaxException {
        path = Paths.get(CsvReaderTest.class.getResource("/empty.csv").toURI());
        List<List<String>> result = csvReader.read(path);
        softly.assertThat(result).containsExactlyElementsOf(List.of());
    }

    @Test
    @DisplayName("Load CSV file with example data")
    public void testExampleFile() throws URISyntaxException {
        path = Paths.get(CsvReaderTest.class.getResource("/example.csv").toURI());
        List<List<String>> result = csvReader.read(path);
        softly.assertThat(result).containsExactlyElementsOf(List.of(
                List.of("Adam", "2m", "20", "1m", "20", "5", "10", "500", "15", "25"),
                List.of("Fero", "1m", "8m", "2m", "30", "50", "20", "100", "5m", "10"),
                List.of("Dusan", "20", "5m", "10", "2m", "1m", "50", "70", "25", "12", "4m"),
                List.of("Brano", "2222", "2m", "22", "11", "111", "81", "14")
        ));
    }

    @Test
    @DisplayName("Load CSV file with empty value")
    public void testFileWithEmptyValue() throws URISyntaxException {
        path = Paths.get(CsvReaderTest.class.getResource("/emptyValue.csv").toURI());
        List<List<String>> result = csvReader.read(path);
        softly.assertThat(result).containsExactlyElementsOf(List.of(
                List.of("TK", "", "50m", "50")
        ));
    }
}
