package ua.alexandroid1.oleksandr.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Oleksandr on 26.12.2016.
 */
public class CVLoader {

    private static final String url = "http://www.hh.ru";

    private WebDriver driver;
    private String searchKeyWord = null;

    public CVLoader(WebDriver driver)
    {
        this.driver = driver;
    }

    public CVLoader setSearchKeyWord(String searchKeyWord)
    {
        this.searchKeyWord = searchKeyWord;
        return this;
    }

    public String search(int waitSeconds)
    {
        driver.get(url);

        WebDriverWait wait = new WebDriverWait(driver, waitSeconds);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@data-qa='vacancy-serp__query']")));

        element.click();
        element.sendKeys(searchKeyWord);

        wait = new WebDriverWait(driver, waitSeconds);
        element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-qa='navi-search__button']")));
        element.click();
        return "";
    }


}
