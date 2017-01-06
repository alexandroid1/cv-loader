import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import ua.alexandroid1.oleksandr.pages.CVApplyer;
import ua.alexandroid1.oleksandr.pages.CVLoader;
import ua.alexandroid1.oleksandr.TxtFileListTransfer;

import java.util.*;

public class HHRunner extends TxtFileListTransfer {

    public static void main(String[] args) {

        Properties prop = getProperties();

        List<String> appliedList = new ArrayList<>();
        fileToList(appliedList, prop.getProperty("appliedFilePath"));

        FirefoxBinary binary = new FirefoxBinary();
        ProfilesIni profileIni = new ProfilesIni();
        FirefoxProfile myprofile = profileIni.getProfile(prop.getProperty("profileName"));

        myprofile.setAcceptUntrustedCertificates(true);
        WebDriver driver = new FirefoxDriver(binary, myprofile);

        double randNumber = Math.random();
        int waitSeconds = (int) randNumber * 1000 + 500;

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
                    cvApplyer.applyForCV(appliedList, waitSeconds);
                }
            });
        }
    }

}
