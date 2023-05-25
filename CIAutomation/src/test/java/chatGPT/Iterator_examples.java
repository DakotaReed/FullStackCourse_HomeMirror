package chatGPT;

import org.testng.annotations.Test;

import java.util.*;

public class Iterator_examples {

    @Test
    public void test01_RemoveElement() {
        List<String> list = new ArrayList<>(Arrays.asList("foo", "bar", "baz"));
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String element = iterator.next();
            if (element.equals("bar")) {
                iterator.remove();
            }
        }
        System.out.println(list); // Output: [foo, baz]
    }

    @Test
    public void test02_removeEntriesThatMeetCertainCondition() {
        Map<String, Integer> map = new HashMap<>();
        map.put("foo", 1);
        map.put("bar", 2);
        map.put("baz", 3);
        Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            if (entry.getValue() > 2) {
                iterator.remove();
            }
            if (entry.getKey().equalsIgnoreCase("foo"))
                iterator.remove();
        }
        System.out.println(map); // Output: {bar=2}
    }

    @Test
    public void test03_modifyElement() {
        List<String> fruits = new ArrayList<>(Arrays.asList("apple", "banana", "cherry", "date"));
        ListIterator<String> iterator = fruits.listIterator();
        while (iterator.hasNext()) {
            String fruit = iterator.next();
            if (fruit.length() % 2 == 0) {
                iterator.remove();
            } else {
                iterator.set(fruit.toUpperCase());
            }
        }
        System.out.println(fruits); // Output: [APPLE]
    }

    @Test
    public void test04_reverseTheOrderOfElement() {
        List<String> list = new ArrayList<>(Arrays.asList("foo", "bar", "baz"));
        ListIterator<String> iterator = list.listIterator(list.size());
        while (iterator.hasPrevious()) {
            System.out.println(iterator.previous());
        }    // Output: baz, bar, foo
    }

    @Test
    public void test05_countNumberOfElementsThatMeetCertainCondition() {
        List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        int count = 0;
        Iterator<Integer> iterator = numbers.iterator();
        while (iterator.hasNext()) {
            int number = iterator.next();
            if (number % 2 == 0) {
                count++;
            }
        }
        System.out.println(count); // Output: 3

    }
}
