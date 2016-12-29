package ua.alexandroid1.oleksandr.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;

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

    public void applyForCV(int waitSeconds) {
        driver.get(url + cvId);
        System.out.println("");
        System.out.println(url + cvId);

        // green button apply click
        try {
            WebDriverWait wait = new WebDriverWait(driver, waitSeconds);
            WebElement applyLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-qa='vacancy-response-link']")));
            applyLink.click();
        } catch (NoSuchElementException ignored) {
            System.out.print("-");
        }

        // apply from anywhere
        try {
            WebDriverWait wait = new WebDriverWait(driver, waitSeconds);
            WebElement applyFromAnywhereLnk = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@data-qa='vacancy-response-link-force']")));
            applyFromAnywhereLnk.click();
        } catch (NoSuchElementException ignored) {
            System.out.print("-");
        }

        // Write a cover letter link click
        try {
            WebDriverWait wait = new WebDriverWait(driver, waitSeconds);
            WebElement writeCoverLetterLnk = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='link-switch-secondary']")));
            writeCoverLetterLnk.click();
        } catch (NoSuchElementException ignored) {
            System.out.print("-");
        }

        // Write a cover letter text pole click
        // send cover letter
        try {
            WebDriverWait wait = new WebDriverWait(driver, waitSeconds);
            WebElement writeCoverLetterLnk = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//textarea[@maxlength='10000']")));
            writeCoverLetterLnk.click();

            writeCoverLetterLnk.sendKeys("Добрый день! \n" +
                    "Меня заинтересовала Ваша вакансия! \n" +
                    "Сейчас рассматриваю предложения с удаленной работой.\n" +
                    "Возможна ли удаленная работа или начать удаленно?\n" +
                    "С уважением, Александр.\n" +
                    "\n" +
                    "LinkedIn: https://www.linkedin.com/in/alexandroid1\n" +
                    "Locations: Donetsk, Ukraine\n" +
                    "Phone: 050-426-31-93\n" +
                    "Skype: alexandr_pavlov_ukraine\n" +
                    "E-mail: avpavlov108@gmail.com\n" +
                    "avpavlov96@gmail.com");

        } catch (NoSuchElementException ignored) {
            System.out.print("-");
        }


        // submit click
        try {
            WebDriverWait wait = new WebDriverWait(driver, waitSeconds);
            WebElement submitClickLnk = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='submit']")));
            submitClickLnk.click();
        } catch (NoSuchElementException ignored) {
            System.out.print("-");
        }


    }
}
