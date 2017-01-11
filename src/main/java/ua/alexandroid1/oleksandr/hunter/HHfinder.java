package ua.alexandroid1.oleksandr.hunter;

import org.openqa.selenium.WebDriver;
import ua.alexandroid1.oleksandr.pages.CVApplyer;
import ua.alexandroid1.oleksandr.pages.CVLoader;
import ua.alexandroid1.oleksandr.settings.TimeSetter;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by Oleksandr on 08.01.2017.
 */
public class HHfinder extends TimeSetter {
    protected static void searchByDomain(String currentLang) {
        System.out.println("Language = " + currentLang + " Loaded  ");

        Properties prop = getProperties(currentLang);
        List<String> appliedList = new ArrayList<>();
        fileToList(appliedList, prop.getProperty("appliedFilePath"));

        // login with FireFox profile
        //WebDriver driver = getGeckoWebDriver(prop);

        // login with login and pass
        WebDriver driver = getChromeWebDriverAndLogin(prop);

        int waitSeconds = getWaitSeconds();

        CVLoader cvLoader = new CVLoader(driver);
        cvLoader
                .setSearchURL(prop.getProperty("searchURL"))
                .setSearchKeyWord(prop.getProperty("searchKeyWord"))
                .searchByKeyWord(waitSeconds);

        while (cvLoader.getNextPage(waitSeconds)) {
            ArrayList<String> getCvIds = cvLoader.getCvIds(waitSeconds);

            getCvIds.forEach((cvId) -> {
                if(! appliedList.contains(cvId) ){
                    CVApplyer cvApplyer = new CVApplyer(driver, cvId);
                    cvApplyer.applyForCV(appliedList, waitSeconds, currentLang);
                }
            });
        }
    }
}
