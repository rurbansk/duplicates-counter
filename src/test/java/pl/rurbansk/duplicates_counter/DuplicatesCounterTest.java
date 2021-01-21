package pl.rurbansk.duplicates_counter;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class DuplicatesCounterTest {

    DuplicatesCounter duplicatesCounter = new DuplicatesCounter();

    @ParameterizedTest
    @CsvSource({
            "base_test.txt, base_test_result.txt",
            "100_test.txt,  100_test_result.txt",
            "1000_test.txt, 1000_test_result.txt"
            })
    void countDuplicates(String data, String expected_result) {
        // given
        String elements = loadData(data);

        // when
        Map<String, Integer> result = duplicatesCounter.countDuplicates(elements);

        // then
        Map<String, Integer> expected = Arrays.stream(loadData(expected_result).split(",")).
                map(s -> s.split("=")).
                collect(Collectors.toMap(k -> k[0], k -> Integer.valueOf(k[1])));
        assertEquals(result, expected);
    }

    @ParameterizedTest
    @CsvSource({
            "base_test.txt, base_test_result.txt",
            "100_test.txt,  100_test_result.txt",
            "1000_test.txt, 1000_test_result.txt"
    })
    void countDuplicatesOptimized(String data, String expected_result) {
        // given
        String elements = loadData(data);

        // when
        Map<String, Integer> result = duplicatesCounter.countDuplicatesOptimized(elements);

        // then
        Map<String, Integer> expected = Arrays.stream(loadData(expected_result).split(",")).
                map(s -> s.split("=")).
                collect(Collectors.toMap(k -> k[0], k -> Integer.valueOf(k[1])));
        assertEquals(result, expected);
    }

    private String loadData(String fileName) {
        try {
            Path path = Paths.get(getClass().getClassLoader().getResource(fileName).toURI());
            return Files.lines(path).collect(Collectors.joining());
        } catch (IOException | URISyntaxException e) {
            fail("Missing file");
        }
        return fileName;
    }
}