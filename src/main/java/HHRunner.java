import org.openqa.selenium.WebDriver;
import ua.alexandroid1.oleksandr.settings.TimeSetter;
import ua.alexandroid1.oleksandr.pages.CVApplyer;
import ua.alexandroid1.oleksandr.pages.CVLoader;

import java.util.*;

public class HHRunner extends TimeSetter {

    private static final String[] HHLANGS = { "ru", "ua" };

    public static void main(String[] args) {

        for (int i = 0; i < HHLANGS.length; i++) {
            System.out.println("Language = " + HHLANGS[i] + " Loaded  ");

            Properties prop = getProperties(HHLANGS[i]);
            List<String> appliedList = new ArrayList<>();
            fileToList(appliedList, prop.getProperty("appliedFilePath"));
            WebDriver driver = getWebDriver(prop);
            int waitSeconds = getWaitSeconds();

            CVLoader cvLoader = new CVLoader(driver);
            cvLoader
                    .setSearchURL(prop.getProperty("searchURL"))
                    .setSearchKeyWord(prop.getProperty("searchKeyWord"))
                    .searchByKeyWord(waitSeconds);

            while (cvLoader.getNextPage(waitSeconds)) {
                ArrayList<String> getCvIds = cvLoader.getCvIds(waitSeconds);

                int langNum = i;
                getCvIds.forEach((cvId) -> {
                    if(! appliedList.contains(cvId) ){
                        CVApplyer cvApplyer = new CVApplyer(driver, cvId);
                        cvApplyer.applyForCV(appliedList, waitSeconds, HHLANGS[langNum]);
                    }
                });
            }
        }
    }
}
