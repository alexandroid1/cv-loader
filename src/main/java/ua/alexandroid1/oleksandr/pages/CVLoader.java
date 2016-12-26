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

    private WebDriver driver;
    private String searchUrl;
    private String searchKeyWord;

    public CVLoader(WebDriver driver)
    {
        this.driver = driver;
    }

    public CVLoader setSearchURL(String searchUrl)
    {
        this.searchUrl = searchUrl;
        return this;
    }

    public CVLoader setSearchKeyWord(String searchKeyWord)
    {
        this.searchKeyWord = searchKeyWord;
        return this;
    }

    public void searchByKeyWord(int waitSeconds)
    {
        driver.get(searchUrl);
        WebDriverWait wait = new WebDriverWait(driver, waitSeconds);

        WebElement searchInputField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@data-qa='vacancy-serp__query']")));
        searchInputField.click();
        searchInputField.sendKeys(searchKeyWord);

        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-qa='navi-search__button']")));
        searchButton.click();
    }

}
