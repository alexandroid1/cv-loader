package ua.alexandroid1.oleksandr.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Oleksandr on 26.12.2016.
 */
public class CVLoader {

    private WebDriver driver;
    private String searchUrl;
    private String searchKeyWord;
    private String nextPageURL = null;

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

    public String getNextPageURL() {
        return nextPageURL;
    }

    public void setNextPageURL(String nextPageURL) {
        this.nextPageURL = nextPageURL;
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

    public ArrayList<String> getCvIds(int waitSeconds) {
        ArrayList<String> getProfileIds = new ArrayList<String>();
        try {
            java.util.List<WebElement> links = driver.findElements(By.xpath("//a[@data-qa='vacancy-serp__vacancy-title']"));
            for (int i = 0; i < links.size(); i++) {
                try {
                    String profileUrl = links.get(i).getAttribute("href");
                    getProfileIds.add(getIDfromProfileUrl(profileUrl));
                } catch (Exception e) {
                    System.out.print("-");
                }
            }
        } catch (NoSuchElementException ignored) {
            System.out.print("-");
        }
        return getProfileIds;
    }

    private String getIDfromProfileUrl(String profileUrl) {
        Pattern p = Pattern.compile(".*?(\\d+)", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
        Matcher m = p.matcher(profileUrl);
        if (m.find()) return m.group(1);
        return null;
    }

    public boolean getNextPage(int waitSeconds) {
        try {
            if (nextPageURL != null && !nextPageURL.isEmpty()) driver.get(getNextPageURL());
            WebDriverWait wait = new WebDriverWait(driver, waitSeconds);
            try {
                WebElement nextLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-qa='pager-next']")));
                setNextPageURL(nextLink.getAttribute("href"));
                System.out.println(" link next = " + nextLink.getAttribute("href"));
                nextLink.click();
            } catch (Exception e) {
                System.out.print("-");
            }
            return true;
        } catch (NoSuchElementException ignored) {
            return false;
        }
    }

}
