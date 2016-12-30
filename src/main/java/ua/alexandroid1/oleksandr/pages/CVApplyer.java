package ua.alexandroid1.oleksandr.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Created by Oleksandr on 29.12.2016.
 */
public class CVApplyer {

    private static final String url = "https://hh.ru/vacancy/";
    private WebDriver driver;
    private String cvId;

    public List<String> appliedList = new ArrayList<>();

    public CVApplyer(WebDriver driver, String cvId) {
        this.driver = driver;
        this.cvId = cvId;
    }

    public void applyForCV(int waitSeconds) {
        driver.get(url + cvId);
        System.out.println("");
        System.out.println(url + cvId);

        if (driver.findElements(By.xpath("//a[@data-qa='vacancy-response-link']")).size() > 0) {
            preApply();
            if (driver.findElements(By.xpath("//span[@data-qa='vacancy-response-link-force']")).size() > 0) {
                applyFromAnywhere(waitSeconds);
                if (driver.findElements(By.xpath("//span[@class='link-switch-secondary']")).size() > 0) {
                    showTextArea(waitSeconds);
                }
                if((driver.findElements(By.xpath("//input[contains(@value,'Отправить отклик')]")).size() > 0)){
                    coverLetter();
                    apply(waitSeconds);
                    apply(waitSeconds);
                }
            }
        } else {
            // already applied
            appliedList.add(cvId);

            String text = "Text to save to file";
            try {
                Files.write(Paths.get("./appliedList.txt"), appliedList);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void preApply() {
        try {
            WebElement applyLink = driver.findElement(By.xpath("//a[@data-qa='vacancy-response-link']"));
            applyLink.click();
        } catch (NoSuchElementException ignored) {
            System.out.print("-");
        }
    }

    private void applyFromAnywhere(int waitSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, waitSeconds);
            WebElement applyFromAnywhereLnk = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@data-qa='vacancy-response-link-force']")));
            applyFromAnywhereLnk.click();
        } catch (NoSuchElementException ignored) {
            System.out.print("-");
        }
    }

    private void showTextArea(int waitSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, waitSeconds);
            WebElement showTextArea = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='link-switch-secondary']")));
            showTextArea.click();
        } catch (NoSuchElementException ignored) {
            System.out.print("-");
        }
    }

    private void coverLetter() {
        if((driver.findElements(By.xpath("//textarea[@name='letter']")).size() > 0)) {
            try {
                Set<String> handles = driver.getWindowHandles();//To handle multiple windows
                String firstWinHandle = driver.getWindowHandle();
                handles.remove(firstWinHandle);
                    WebElement coverLetterArea = driver.findElement(By.xpath("//textarea[@name='letter']"));
                    coverLetterArea.sendKeys("Добрый день!");
            } catch (NoSuchElementException ignored) {
                System.out.print("-");
            }
        }
    }

    private void apply(int waitSeconds) {
        if (driver.findElements(By.xpath("//input[contains(@value,'Отправить отклик')]")).size() > 0) {
            try {
                WebDriverWait wait = new WebDriverWait(driver, waitSeconds);
                WebElement submitClickLnk = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[contains(@value,'Отправить отклик')]")));
                submitClickLnk.click();
            } catch (NoSuchElementException ignored) {
                System.out.print("нет элемента");
            } catch (Exception e) {
                System.out.print("something wrong");
            }
        }
    }
}
