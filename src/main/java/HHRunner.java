import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import ua.alexandroid1.oleksandr.pages.CVLoader;

public class HHRunner {

    public static void main(String[] args) {

        System.setProperty("webdriver.gecko.driver","C:\\Selenium\\Firefox driver\\geckodriver.exe");
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
    }
}
