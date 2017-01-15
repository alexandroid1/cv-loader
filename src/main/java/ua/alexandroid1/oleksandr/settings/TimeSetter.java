package ua.alexandroid1.oleksandr.settings;

/**
 * Created by Oleksandr on 06.01.2017.
 */
public class TimeSetter {

    public static int getWaitSeconds() {
        double randNumber = Math.random();
        return (int) randNumber * 1000 + 500;
    }

    public static void waiteOneSec() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
