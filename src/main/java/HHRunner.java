import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ua.alexandroid1.oleksandr.CVLoader;

public class HHRunner {

    public static void main(String[] args) {

        System.setProperty("webdriver.gecko.driver","C:\\Selenium\\Firefox driver\\geckodriver.exe");

        WebDriver driver = new FirefoxDriver();

        double randNumber = Math.random();
        int waitSeconds = (int)randNumber * 10 + 5;
        CVLoader cvLoader = new CVLoader(driver);
        cvLoader
                .setKeyword("java")
                .search(waitSeconds);
    }
}
