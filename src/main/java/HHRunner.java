import ua.alexandroid1.oleksandr.hunter.HHfinder;

public class HHRunner extends HHfinder {

    private static final String[] HHLANGS = { "ru", "ua" };

    public static void main(String[] args) {

        for (int i = 0; i < HHLANGS.length; i++) {
            String currentLang = HHLANGS[i];
            searchByDomain(currentLang);
        }

        // TODO cuncurrency
/*
        Thread HHThreadRU = new Thread(() -> searchByDomain("ru"));
        Thread HHThreadUA = new Thread(() -> searchByDomain("ua"));


        HHThreadRU.start();
        HHThreadUA.start();
        */

    }

}
