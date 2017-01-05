package ua.alexandroid1.oleksandr.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
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

    public CVApplyer(WebDriver driver, String cvId) {
        this.driver = driver;
        this.cvId = cvId;
    }

    public void applyForCV(List<String> appliedList, int waitSeconds) {
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
                    waiteOneSec();
                    apply(waitSeconds);
                }
            }
        } else {
            appliedList.add(cvId);
            listToFile(appliedList);
        }
    }

    private void listToFile(List<String> appliedList) {
        try {
            Files.write(Paths.get("./appliedList.txt"), appliedList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void preApply() {
        try {
            waiteOneSec();
            WebElement applyLink = driver.findElement(By.xpath("//a[@data-qa='vacancy-response-link']"));
            applyLink.click();
        } catch (NoSuchElementException ignored) {
            System.out.print("-");
        }
    }

    private void waiteOneSec() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void applyFromAnywhere(int waitSeconds) {
        try {
            waiteOneSec();
            WebDriverWait wait = new WebDriverWait(driver, waitSeconds);
            WebElement applyFromAnywhereLnk = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@data-qa='vacancy-response-link-force']")));
            applyFromAnywhereLnk.click();
        } catch (NoSuchElementException ignored) {
            System.out.print("-");
        }
    }

    private void showTextArea(int waitSeconds) {
        try {
            waiteOneSec();
            WebDriverWait wait = new WebDriverWait(driver, waitSeconds);
            WebElement showTextArea = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='link-switch-secondary']")));
            showTextArea.click();
        } catch (NoSuchElementException ignored) {
            System.out.print("-");
        }
    }

    private void coverLetter() {
        if((driver.findElements(By.xpath("//textarea[@name='letter']")).size() > 0)) {

                //TODO breakpoint here ???
                // paste cover letter manually

            waiteOneSec();

            String parentWindowHandler = driver.getWindowHandle(); // Store your parent window
            driver.switchTo().window(parentWindowHandler);  // switch back to parent window

            WebDriverWait wait = new WebDriverWait(driver, 10);
            WebElement textArea = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//textarea[contains(@class,'sized-rows\n" +
                    "                                      HH-VacancyResponsePopup-Letter ')]")));
            textArea.click();
            textArea.sendKeys("Добрый день! \n" +
                    "Меня заинтересовала ваша вакансия!\n" +
                    "Сейчас рассматриваю предложения с удаленной работой.\n" +
                    "Возможна ли удаленная работа или начать удаленно?\n" +
                    "С уважением, Александр.\n" +
                    "\n" +
                    "LinkedIn: https://www.linkedin.com/in/alexandroid1\n" +
                    "GitHub: https://github.com/alexandroid1\n" +
                    "Locations: Donetsk, Ukraine\n" +
                    "Phone: 050-426-31-93\n" +
                    "Skype: alexandr_pavlov_ukraine\n" +
                    "E-mail: avpavlov108@gmail.com\n" +
                    "avpavlov96@gmail.com");


        }
    }

    private void apply(int waitSeconds) {
        if (driver.findElements(By.xpath("//input[contains(@value,'Отправить отклик')]")).size() > 0) {
            try {
                waiteOneSec();
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
