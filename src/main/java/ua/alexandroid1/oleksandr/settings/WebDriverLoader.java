package ua.alexandroid1.oleksandr.settings;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Properties;

import static ua.alexandroid1.oleksandr.pages.HHLogin.loginToHH;

/**
 * Created by Oleksandr on 11.01.2017.
 */
public class WebDriverLoader {

    public static WebDriver getChromeWebDriverAndLogin(Properties prop) {

        ChromeDriverManager.getInstance().setup();
        ChromeDriver driver = new ChromeDriver();

        loginToHH(prop, driver);

        return driver;
    }
}
