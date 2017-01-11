package ua.alexandroid1.oleksandr.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static ua.alexandroid1.oleksandr.conversion.TxtFileListTransfer.fileToList;

/**
 * Created by Oleksandr on 11.01.2017.
 */
public class HHLogin {
    protected static void loginToHH(Properties prop, ChromeDriver driver) {

        List<String> passwordList = new ArrayList<>();
        fileToList(passwordList, prop.getProperty("loginpassfile"));

        String loginStr = passwordList.get(0);
        String passwordStr = passwordList.get(1);

        // Resize the browser screen to make sure that the input field is not hidden.
        driver.manage().window().maximize();

        driver.get(prop.getProperty("searchURL"));

        WebElement loginInput = driver.findElement(By.xpath("html/body/div[5]/div/div[3]/div/div/div[2]/form/label[1]/span[2]/input"));
        loginInput.sendKeys(loginStr);

        WebElement passwordInput = driver.findElement(By.xpath("html/body/div[5]/div/div[3]/div/div/div[2]/form/label[2]/span[2]/input"));
        passwordInput.sendKeys(passwordStr);

        WebElement submitBtn = driver.findElement(By.xpath("html/body/div[5]/div/div[3]/div/div/div[2]/form/div[3]/input"));
        submitBtn.click();
    }
}
