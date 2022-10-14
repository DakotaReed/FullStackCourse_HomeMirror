package Lesson12__DataDrivenTesting;

import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

public class Utils {

    @DataProvider(name = "dataProvider Wiki CSV")
    public Object[][] getDataObject() {
        return getDataFromCSV("C:/Automation/FullStackCourse_HomeMirror/SeleniumV2/CSV/data__wiki.csv");
    }

    public static Object[][] getDataFromCSV(String filePath) {
        Object[][] data = new Object[4][2];
        List<String> csvData = readCSV(filePath);
        for (int i=0; i< csvData.size(); i++) {
            data[i][0] = csvData.get(i).split(", ")[0];
            data[i][1] = csvData.get(i).split(", ")[1];
        }
    return data;
    }

    public static List<String> readCSV(String csvFile) {
        List<String> lines = null;
        File file = new File(csvFile);
        try {
            lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
        }
        catch (IOException e) {
            System.out.println("error. "+e);
        }

        return lines;
    }

}
