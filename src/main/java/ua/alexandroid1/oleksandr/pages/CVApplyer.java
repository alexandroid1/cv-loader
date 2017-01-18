package ua.alexandroid1.oleksandr.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;

import static ua.alexandroid1.oleksandr.conversion.TxtFileListTransfer.listToFile;
import static ua.alexandroid1.oleksandr.settings.PropLoader.getProperties;
import static ua.alexandroid1.oleksandr.settings.TimeSetter.waiteOneSec;

/**
 * Created by Oleksandr on 29.12.2016.
 */
public class CVApplyer {

    private final String coverLetter = "Добрый день! \n" +
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
            "avpavlov96@gmail.com";
    private WebDriver driver;
    private String cvId;

    public CVApplyer(WebDriver driver, String cvId) {
        this.driver = driver;
        this.cvId = cvId;
    }

    public void applyForCV(List<String> appliedList, int waitSeconds, String lang) {
        Properties prop = getProperties(lang);
        driver.get(prop.getProperty("searchUrlForCV") + cvId);
        System.out.println("");
        System.out.println(prop.getProperty("searchUrlForCV") + cvId);
        if (driver.findElements(By.xpath("//a[@data-qa='vacancy-response-link']")).size() > 0) {
            preApply();
            if (driver.findElements(By.xpath("//span[@data-qa='vacancy-response-link-force']")).size() > 0) {
                if (lang.equals("ru")) {
                    applyFromAnywhere(waitSeconds);
                }
                waiteOneSec();
                if (driver.findElements(By.xpath("//span[@class='bloko-link-switch bloko-link-switch_secondary']")).size() > 0) {
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
            listToFile(appliedList, prop.getProperty("appliedFilePath"));
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
            WebElement showTextArea = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='bloko-link-switch bloko-link-switch_secondary']")));
            showTextArea.click();
        } catch (NoSuchElementException ignored) {
            System.out.print("-");
        }
    }

    private void coverLetter() {
        if((driver.findElements(By.xpath("//textarea[@name='letter']")).size() > 0)) {
            waiteOneSec();
            WebElement textArea = driver.findElement(By.xpath("html/body/div[8]/div[1]/div[2]/div/form/div[2]/div/textarea"));
            textArea.click();
            textArea.sendKeys(coverLetter);
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
                System.out.print("there is no such element");
            } catch (Exception e) {
                System.out.print("something wrong");
            }
        }
    }
}
