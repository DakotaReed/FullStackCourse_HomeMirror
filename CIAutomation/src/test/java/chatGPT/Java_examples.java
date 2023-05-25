package chatGPT;

import org.testng.annotations.Test;
import java.nio.file.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.io.*;

public class Java_examples {

    @Test
    public void test01_LambdaExpressions() {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        names.forEach(name -> System.out.println("Hello, " + name));
    }

    @Test
    public void test02_Streams() {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        long count = names.stream()
                .filter(name -> name.length() > 4)
                .count();
        System.out.println(count);
    }

    @Test
    public void test03_Optional() {
        String name = null;
        Optional<String> optionalName = Optional.ofNullable(name);
        System.out.println(optionalName.orElse("Unknown"));

        String string = "Name";
        Optional<String> optional = Optional.ofNullable(string);
        if (optional.isPresent()) {
            String result = optional.get();
            System.out.println(result);
        } else {
            // Handle null case
            System.out.println(optional.orElse("Unknown"));
        }

    }

    @Test
    public void test04_TryWithResources() {
        try (InputStream inputStream = new FileInputStream("E:\\Automation\\Files\\output1.csv");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test05_Reversing() {
        String originalString = "hello";
        String reversedString = new StringBuilder(originalString).reverse().toString();
        System.out.println(reversedString); // Output: "olleh"
    }

    @Test
    public void test06_RemovingDuplicateElementsFromList() {
        List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c", "a", "d"));
        List<String> dedupedList = new ArrayList<>(new LinkedHashSet<>(list));
        System.out.println(dedupedList); // Output: [a, b, c, d]
    }

    @Test
    public void test07_JoiningElementsOfList() {
        List<String> list = new ArrayList<>(Arrays.asList("hello", "world", "how", "are", "you"));
        String joinedString = String.join(" ", list);
        System.out.println(joinedString); // Output: "hello world how are you"
    }

    @Test
    public void test08_StreamSumList() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        int sum = list.stream().mapToInt(Integer::intValue).sum();
        System.out.println(sum); // Output: 15
    }

    @Test
    public void test09_RandomNumber() {
        int randomInt = (int) (Math.random() * 10);
        System.out.println(randomInt); // Output: A random integer between 0 and 9
    }

    @Test
    public void test10_StreamAPI() throws IOException {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        int sum = list.stream()
                .filter(n -> n % 2 == 0)
                .map(n -> n * 2)
                .reduce(0, Integer::sum);
        System.out.println(sum); // Output: 12 (2*2 + 4*2)

        Path path = Paths.get("E:\\Automation\\Files\\try.csv");
        List<String> lines = Files.readAllLines(path);
        lines.forEach(System.out::println);

        String newLine = "This is a new line.";
        Files.write(path, newLine.getBytes(), StandardOpenOption.APPEND);

    }

}
