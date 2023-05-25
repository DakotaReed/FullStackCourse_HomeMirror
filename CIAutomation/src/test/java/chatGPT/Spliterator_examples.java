package chatGPT;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.StreamSupport;

public class Spliterator_examples {
    @Test
    public void test01() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Spliterator<Integer> spliterator = numbers.spliterator();

        spliterator = spliterator.trySplit();
        if (spliterator != null) {
            spliterator.forEachRemaining(System.out::println);
        }
    }

    @Test
    public void test02() {
        List<String> strings = Arrays.asList("apple", "banana", "cherry", "date", "elderberry");
        Spliterator<String> spliterator = strings.spliterator();
        StreamSupport.stream(spliterator, true)
                .filter(s -> s.length() % 2 == 0)
                .map(String::toUpperCase)
                .forEach(System.out::println);
    }

}
