package ua.alexandroid1.oleksandr.settings;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Oleksandr on 27.12.2016.
 */
public class PropLoader {
    public static Properties getProperties(String lang) {
        Properties prop = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream(lang + ".properties");
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return prop;
    }

}
