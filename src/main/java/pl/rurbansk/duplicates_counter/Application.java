package pl.rurbansk.duplicates_counter;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Collectors;

public class Application {

    public static void main(String[] args) throws IOException {
        DuplicatesCounter counter = new DuplicatesCounter();
        Application app = new Application();
        String elements = app.loadData(args);

        Map<String, Integer> duplicates = counter.countDuplicates(elements);
        app.printResults("Duplicates O(log(n))", duplicates);

        Map<String, Integer> duplicatesOptimized = counter.countDuplicatesOptimized(elements);
        app.printResults("Duplicates O(log(n))", duplicatesOptimized);
    }

    private String loadData(String[] data) throws IOException {
        try {
            Path path;
            if (data.length < 1 || data[0] == null || data[0].isBlank()) {
                System.out.println("File path not provided - running for example_data.txt");
                return new String(getClass().getClassLoader().getResourceAsStream("example_data.txt").readAllBytes(), StandardCharsets.UTF_8);
            } else {
                path = Path.of(data[0]);
            }
            return Files.lines(path).collect(Collectors.joining());
        } catch (IOException e) {
            System.out.println("Error occurred during file processing");
            throw e;
        }
    }

    private void printResults(String message, Map<String, Integer> data) {
        System.out.println(message);
        System.out.println(data.toString().replace("{","").replace("}",""));
    }
}
