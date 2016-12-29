package ua.alexandroid1.oleksandr.pages;

import org.openqa.selenium.WebDriver;

/**
 * Created by Oleksandr on 29.12.2016.
 */
public class CVApplyer {

    private static final String url = "https://hh.ru/vacancy/";
    private WebDriver driver;
    private String cvId;

    public CVApplyer(WebDriver driver, String cvId) {
        this.driver = driver;
        this.cvId = cvId;
    }

    public void applyForCV() {
        driver.get(url + cvId);
        System.out.println("");
        System.out.println(url + cvId);
    }
}
