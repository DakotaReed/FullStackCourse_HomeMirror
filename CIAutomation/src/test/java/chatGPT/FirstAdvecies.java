package chatGPT;

import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FirstAdvecies {
    @Test
    public void test01_WritingOutputToFile() throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = null;
        writer = new PrintWriter("E:\\Automation\\Files\\output.txt", "UTF-8");
        writer.println("Hello, world! =)");
        writer.close();

        writer = new PrintWriter("E:\\Automation\\Files\\output1.csv", "UTF-8");
        writer.println("Hello, world, table, csv, =)");
        writer.println("yes, no, this, second, line");
        writer.close();
    }

    @Test
    public void test02_UsingRegularExpressions() {
        String str = "The quick brown fox jumps over the lazy dog";
        Pattern pattern = Pattern.compile("\\b\\w{3}\\b");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }

    }

    @Test
    public void test03_UsingMultithreading() {
        MyThread thread1 = new MyThread();
        MyThread thread2 = new MyThread();
//        thread1.setName();
        thread1.start();
        thread2.start();
        thread2.setName();
    }

}
