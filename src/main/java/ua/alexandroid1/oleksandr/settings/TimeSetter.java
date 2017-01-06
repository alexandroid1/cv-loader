package ua.alexandroid1.oleksandr.settings;

import ua.alexandroid1.oleksandr.conversion.TxtFileListTransfer;

/**
 * Created by Oleksandr on 06.01.2017.
 */
public class TimeSetter extends TxtFileListTransfer {

    protected static int getWaitSeconds() {
        double randNumber = Math.random();
        return (int) randNumber * 1000 + 500;
    }

    protected void waiteOneSec() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
