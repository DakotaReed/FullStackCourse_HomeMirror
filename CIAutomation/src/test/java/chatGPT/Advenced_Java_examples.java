package chatGPT;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Advenced_Java_examples {

    @Test
    public void test01_Generics() {
        String[] abc = {"a", "b", "c", "d", "e", "a"};
        Integer[] numbers = {1, 2, 3, 4, 5, 1};
        List<String> stringList = new ArrayList<>();
        stringList.add("a");
        stringList.add("b");
        stringList.add("a");
        List<Integer> numberList = new ArrayList<>();
        numberList.add(1);
        numberList.add(2);
        numberList.add(1);

        System.out.println(max(abc));
        System.out.println(max(numbers));
        System.out.println(max(stringList.toArray(new String[0])));
        System.out.println(max(numberList.toArray(new Integer[0])));

        System.out.println(abc[(int) (Math.random() * abc.length)]);
    }
    public static <T extends Comparable<T>> T max(T[] array) {
        T max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i].compareTo(max) > 0) {
                max = array[i];
            }
        }
        return max;
    }

    // multithreaded programs
    @Test
    public void test02_Concurrency() {
        ExecutorService executor = Executors.newFixedThreadPool(20);
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            int finalI = i;
            executor.submit(() -> {
                // Do some work
                System.out.println("i is: " + finalI);
                list.add(finalI);
            });
        }
        executor.shutdown();
        System.out.println("List size is: " + list.size() + "\n");
    }

    @Test
    public void test03_Iterator() {
        List<String> list = Arrays.asList("foo", "bar", "baz");
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String element = iterator.next();
            System.out.println(element);
        }
//        In addition to the basic Iterator interface, Java also provides some specialized iterator interfaces,
//                such as ListIterator, which provides additional methods for iterating over lists,
//                and Spliterator, which provides support for parallel iteration over collections.
    }

}
