package chatGPT;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternAndMatcher_examples {

    @Test
    public void test01_ReplacingMatches() {
        String str = "The quick brown fox jumps over the lazy dog";
        Pattern pattern = Pattern.compile("\\bfox\\b");
//        System.out.println(pattern.flags());
        Matcher matcher = pattern.matcher(str);
        String replaced = matcher.replaceAll("cat");
        System.out.println(replaced);
    }

    @Test
    public void test02_CapturingGroups() {
        List<String> list = new ArrayList<>();
        list.add("John Smith (30 years old)");
        list.add("Dakota Reed (32 years old)");
        Pattern pattern = Pattern.compile("^(\\w+) (\\w+) \\((\\d+) years old\\)$");
        for (String user : list) {
            Matcher matcher = pattern.matcher(user);
            if (matcher.matches()) {
                String firstName = matcher.group(1);
                String lastName = matcher.group(2);
                int age = Integer.parseInt(matcher.group(3));
                System.out.println("Name: " + firstName + " " + lastName);
                System.out.println("Age: " + age);
            }
        }

        String str = "John Smith (30 years old)";
        Pattern pattern1 = Pattern.compile("^(\\w+) (\\w+) \\((\\d+) years old\\)$");
        Matcher matcher = pattern1.matcher(str);
        if (matcher.matches()) {
            String firstName = matcher.group(1);
            String lastName = matcher.group(2);
            int age = Integer.parseInt(matcher.group(3));
            System.out.println("Name: " + firstName + " " + lastName);
            System.out.println("Age: " + age);
        }
    }

    // we have the problem
    @Test
    public void test03_NonGreedyMatches() {
        String str = "The quick brown fox jumps over the lazy dog";
        Pattern pattern = Pattern.compile("\\b.*?\\b");
//        Pattern pattern = Pattern.compile("\\b.q?\\b");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }

    @Test
    public void test04_ValidatingEmailAddresses() {
//        String email = "example@example.com";
        String email = "example@examplecom";
        String email1 = "example@google.com";
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches()) {
            System.out.println("Valid Email Address");
        } else {
            System.out.println("Invalid Email Address");
        }
    }

    @Test
    public void test05_ValidatingPhoneNumbers() {
        String phoneNumber = "+1 (123) 456-7890";
        Pattern pattern = Pattern.compile("^\\+\\d{1,2}\\s\\(\\d{3}\\)\\s\\d{3}-\\d{4}$");
        Matcher matcher = pattern.matcher(phoneNumber);
        if (matcher.matches()) {
            System.out.println("Valid Phone Number");
        } else {
            System.out.println("Invalid Phone Number");
        }
    }

    // we have the problem
    @Test
    public void test06_FindingDuplicateWords() {
        String text = "the quick brown fox jumps over the lazy dog";
        Pattern pattern = Pattern.compile("\\b(\\w+)\\b\\s+\\b\\1\\b");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }

    @Test
    public void test07_ReplacingSpecificCharacters() {
        String text = "The quick brown fox jumps over the lazy dog";
        Pattern pattern = Pattern.compile("[aeiouT]");
        Matcher matcher = pattern.matcher(text);
        String replaced = matcher.replaceAll("*");
        System.out.println(replaced);

    }

}
