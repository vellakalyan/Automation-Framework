package pages;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class Config_Reader {
    private String reportPath = "Reports/";
    private static String screenShotPath;

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

    public String getReportPath() {
        String reportDate = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss-SSS-a").format(new Date());
        screenShotPath = reportPath + reportDate;
        reportPath = screenShotPath + "/AutomationReport.html";
        return reportPath;
    }


    public static void renameReportFolder() {
        File oldReport = new File(screenShotPath);
        File newReport = new File("Reports/Report");
        try {
            FileUtils.cleanDirectory(newReport);
            FileUtils.copyDirectory(oldReport, newReport);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
