package ua.alexandroid1.oleksandr.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Oleksandr on 26.12.2016.
 */
public class CVLoader {

    private WebDriver driver;
    private String searchUrl;
    private String searchKeyWord;

    public CVLoader(WebDriver driver) {
        this.driver = driver;
    }

    public CVLoader setSearchURL(String searchUrl) {
        this.searchUrl = searchUrl;
        return this;
    }

    public CVLoader setSearchKeyWord(String searchKeyWord) {
        this.searchKeyWord = searchKeyWord;
        return this;
    }

    public void searchByKeyWord(int waitSeconds) {
        driver.get(searchUrl);
        WebDriverWait wait = new WebDriverWait(driver, waitSeconds);

        WebElement searchInputField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@data-qa='vacancy-serp__query']")));
        searchInputField.click();
        searchInputField.sendKeys(searchKeyWord);

        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-qa='navi-search__button']")));
        searchButton.click();
    }

    public ArrayList<String> getCvIds() {
        ArrayList<String> getProfileIds = new ArrayList<String>();
        java.util.List<WebElement> links = driver.findElements(By.xpath("//a[@data-qa='vacancy-serp__vacancy-title']"));

        for (int i = 0; i < links.size(); i++) {
            String profileUrl = links.get(i).getAttribute("href");
            getProfileIds.add(getIDfromProfileUrl(profileUrl));
            System.out.println(getIDfromProfileUrl(profileUrl));
        }
        return getProfileIds;
    }

    private String getIDfromProfileUrl(String profileUrl) {
        Pattern p = Pattern.compile(".*?(\\d+)", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
        Matcher m = p.matcher(profileUrl);
        if (m.find()) return m.group(1);
        return null;
    }

    public void getNextPage(int waitSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, waitSeconds);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@data-qa,'pager-next')]")));
        element.click();
    }


}
