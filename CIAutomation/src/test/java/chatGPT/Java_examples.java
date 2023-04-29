package chatGPT;

import org.testng.annotations.Test;

import java.io.*;
import java.util.Arrays;
import java.util.List;

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

    // we have version problem
    @Test
    public void test03_Optional() {
//        String name = null;
//        Optional<String> optionalName = Optional.ofNullable(name);
//        System.out.println(optionalName.orElse("Unknown"));
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

}
