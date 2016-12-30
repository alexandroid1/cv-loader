import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import ua.alexandroid1.oleksandr.pages.CVApplyer;
import ua.alexandroid1.oleksandr.pages.CVLoader;
import ua.alexandroid1.oleksandr.pages.PropLoader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class HHRunner extends PropLoader {

    public static void main(String[] args) {

        List<String> appliedList = new ArrayList<>();
        fileToList(appliedList);

        Properties prop = getProperties();
        File f = new File(prop.getProperty("driverPathName"));
        System.setProperty("webdriver.gecko.driver", f.getAbsolutePath());
        ProfilesIni profile = new ProfilesIni();
        FirefoxProfile myprofile = profile.getProfile(prop.getProperty("profileName"));
        WebDriver driver = new FirefoxDriver(myprofile);

        double randNumber = Math.random();
        int waitSeconds = (int)randNumber * 1000 + 500;

        CVLoader cvLoader = new CVLoader(driver);
        cvLoader
                .setSearchURL(prop.getProperty("searchURL"))
                .setSearchKeyWord(prop.getProperty("searchKeyWord"))
                .searchByKeyWord(waitSeconds);

        while (cvLoader.getNextPage(waitSeconds)){
            ArrayList<String> getCvIds = cvLoader.getCvIds(waitSeconds);

            getCvIds.forEach((cvId) -> {
                CVApplyer cvApplyer = new CVApplyer(driver, cvId);
                cvApplyer.applyForCV(appliedList, waitSeconds);
            });
        }
    }

    private static void fileToList(List<String> appliedList) {
        Scanner s = null;
        try {
            s = new Scanner(new File("./appliedList.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (s.hasNext()){
            appliedList.add(s.next());
        }
        s.close();
    }

}
