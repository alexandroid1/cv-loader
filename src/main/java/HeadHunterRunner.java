import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;

public class HeadHunterRunner {
    public static void main(String[] args) {
        ChromeDriverManager.getInstance().setup();
        double randNumber = Math.random();
        int waitSeconds = (int)randNumber * 10 + 5;

    }
}
