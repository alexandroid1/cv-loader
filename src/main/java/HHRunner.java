import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import ua.alexandroid1.oleksandr.pages.CVLoader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class HHRunner {

    public static void main(String[] args) {

        File f = new File("geckodriver.exe");
        System.setProperty("webdriver.gecko.driver", f.getAbsolutePath());
        ProfilesIni profile = new ProfilesIni();
        FirefoxProfile myprofile = profile.getProfile("default");
        WebDriver driver = new FirefoxDriver(myprofile);

        double randNumber = Math.random();
        int waitSeconds = (int)randNumber * 10 + 5;

        CVLoader cvLoader = new CVLoader(driver);
        cvLoader
                .setSearchURL("http://www.hh.ru")
                .setSearchKeyWord("java")
                .searchByKeyWord(waitSeconds);

        while (true){
            cvLoader.getNextPage(waitSeconds);
            ArrayList<String> getProfileIds = cvLoader.getCvIds();
        }
    }
}
