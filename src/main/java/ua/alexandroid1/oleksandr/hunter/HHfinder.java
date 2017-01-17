package ua.alexandroid1.oleksandr.hunter;

import org.openqa.selenium.WebDriver;
import ua.alexandroid1.oleksandr.pages.CVApplyer;
import ua.alexandroid1.oleksandr.pages.CVLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static ua.alexandroid1.oleksandr.conversion.TxtFileListTransfer.fileToList;
import static ua.alexandroid1.oleksandr.settings.PropLoader.getProperties;
import static ua.alexandroid1.oleksandr.settings.TimeSetter.getWaitSeconds;
import static ua.alexandroid1.oleksandr.settings.WebDriverLoader.getChromeWebDriverAndLogin;

/**
 * Created by Oleksandr on 08.01.2017.
 */
public class HHfinder {
    public static void searchByDomain(String currentLang) {
        System.out.println("Language = " + currentLang + " Loaded  ");

        Properties prop = getProperties(currentLang);
        List<String> appliedList = new ArrayList<>();
        fileToList(appliedList, prop.getProperty("appliedFilePath"));

        WebDriver driver = getChromeWebDriverAndLogin(prop);

        int waitSeconds = getWaitSeconds();

        CVLoader cvLoader = new CVLoader(driver);
        cvLoader
                .setSearchURL(prop.getProperty("searchURL"))
                .setSearchKeyWord(prop.getProperty("searchKeyWord"))
                .searchByKeyWord(waitSeconds);

        while (cvLoader.getNextPage(waitSeconds)) {
            ArrayList<String> getCvIds = cvLoader.getCvIds();

            getCvIds.forEach((cvId) -> {
                if(! appliedList.contains(cvId) ){
                    CVApplyer cvApplyer = new CVApplyer(driver, cvId);
                    cvApplyer.applyForCV(appliedList, waitSeconds, currentLang);
                }
            });
        }
    }
}
