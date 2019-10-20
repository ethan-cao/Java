package designPattern;

import java.util.Observable;
import java.util.Observer;

/**
 * Observer Pattern
 *
 * It defines a one-to-many dependency between observable and observers, when observable changes, observers are notified
 */

class ObserverPattern {
    public static void main(String[] args) {
        StockPrice stockPrice = new StockPrice();

        Alarm alarm1 = new Alarm();
        stockPrice.addObserver(alarm1);

        Alarm alarm2 = new Alarm();
        stockPrice.addObserver(alarm2);

        stockPrice.updatePrice(100);

        System.out.printf("%s price : %d\n", "alarm1", alarm1.getPrice()); // 100
        System.out.printf("%s price : %d\n", "alarm2", alarm2.getPrice()); // 100
    }
}

class StockPrice extends Observable {
    private int price;

    public void updatePrice(int newPrice) {
        this.price = newPrice;

        // mark Observable has changed and notify observers
        this.setChanged();
        this.notifyObservers(this.price);
    }

}

class Alarm implements Observer {
    private int price;

    @Override
    public void update(Observable observable, Object price) {
        this.price = (Integer) price;
    }

    public int getPrice() {
        return this.price;
    }
}