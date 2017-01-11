import ua.alexandroid1.oleksandr.hunter.HHfinder;

public class HHRunner extends HHfinder {
    public static void main(String[] args) {

        Thread HHThreadRU = new Thread(() -> searchByDomain("ru"));
        Thread HHThreadUA = new Thread(() -> searchByDomain("ua"));

        // parallel search for https://hh.ru and https://hh.ua
        HHThreadRU.start();
        HHThreadUA.start();
    }

}
