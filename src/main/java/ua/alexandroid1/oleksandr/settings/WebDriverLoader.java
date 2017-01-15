package ua.alexandroid1.oleksandr.settings;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;

import java.util.Properties;

import static ua.alexandroid1.oleksandr.pages.HHLogin.loginToHH;

/**
 * Created by Oleksandr on 11.01.2017.
 */
public class WebDriverLoader {

    protected static WebDriver getGeckoWebDriver(Properties prop) {
        FirefoxBinary binary = new FirefoxBinary();
        ProfilesIni profileIni = new ProfilesIni();
        FirefoxProfile myProfile = profileIni.getProfile(prop.getProperty("profileName"));
        myProfile.setAcceptUntrustedCertificates(true);
        return new FirefoxDriver(binary, myProfile);
    }

    public static WebDriver getChromeWebDriverAndLogin(Properties prop) {

        ChromeDriverManager.getInstance().setup();
        ChromeDriver driver = new ChromeDriver();

        loginToHH(prop, driver);

        return driver;
    }


}
