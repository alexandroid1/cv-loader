import ua.alexandroid1.oleksandr.hunter.HHfinder;

public class HHRunner extends HHfinder {
    public static void main(String[] args) {


        // TODO synchronization "ru" and "ua"

        Thread HHThreadRU = new Thread(() -> searchByDomain("ru"));
        HHThreadRU.start();

/*        Thread HHThreadUA = new Thread(() -> searchByDomain("ua"));
        HHThreadUA.start();*/
    }

}
