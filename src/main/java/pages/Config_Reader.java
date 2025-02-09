package pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Config_Reader {

    public static String properties(String key) {
        Properties prob = null;
        File file = null;
        file = new File("src/test/resources/Config/config.properties");

        FileInputStream fileInput = null;
        try {
            fileInput = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        prob = new Properties();
        try {
            prob.load(fileInput);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fileInput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prob.getProperty(key);
    }
}
