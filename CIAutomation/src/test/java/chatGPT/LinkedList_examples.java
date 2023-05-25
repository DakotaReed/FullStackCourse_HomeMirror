package chatGPT;

import org.testng.annotations.Test;

import java.util.*;

public class LinkedList_examples {

    @Test
    public void test01_ImplementingStack() {
        LinkedList<Integer> stack = new LinkedList<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        int top = stack.pop(); // top = 3
        System.out.println(top);
    }

    @Test
    public void test02_ImplementingQueue() {
        LinkedList<Integer> queue = new LinkedList<>();
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        int front = queue.poll(); // front = 1
        System.out.println(front);
    }

    @Test
    public void test03_ImplementingCircularBuffer() {
        LinkedList<Integer> buffer = new LinkedList<>();
        int capacity = 3;
        buffer.offer(1);
        buffer.offer(2);
        buffer.offer(3);
        if (buffer.size() == capacity) {
            buffer.remove(); // remove first element
        }
        buffer.offer(4); // add new element to end
        System.out.println(buffer);
    }

    @Test
    public void test04_RemovingDuplicatesFromLinkedList() {
        LinkedList<Integer> list = new LinkedList<>(Arrays.asList(1, 2, 2, 3, 3, 3));
        HashSet<Integer> set = new HashSet<>(list);
        LinkedList<Integer> noduplicates = new LinkedList<>(set);
        System.out.println(noduplicates);
    }

    @Test
    public void test05_CheckingIfLinkedListIsPalindrome() {
        LinkedList<Character> list = new LinkedList<>(Arrays.asList('r', 'a', 'c', 'e', 'c', 'a', 'r'));
        System.out.println(isPalindrome(list));
    }
    public static boolean isPalindrome(LinkedList<Character> list) {
        LinkedList<Character> reversed = new LinkedList<>(list);
        Collections.reverse(reversed);
        return Objects.equals(list, reversed);
    }

    @Test
    public void test06_FindingTheMiddleElementOfLinkedList() {
        LinkedList<Integer> list = new LinkedList<>(Arrays.asList(1, 2, 3, 4, 5));
        for (int i = 0; i < list.size()/2; i++) {
            list.removeFirst();
        }
        int middleElement = list.getFirst(); // middleElement = 3
        System.out.println(middleElement);
    }

}
