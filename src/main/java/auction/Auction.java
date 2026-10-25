package auction;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Auction implements BidSubject {
    // Chuẩn bị sẵn CopyOnWriteArrayList để đảm bảo an toàn đa luồng cho Bước 5
    private List<BidObserver> observers = new CopyOnWriteArrayList<>();
    private double currentHighestBid;

    @Override
    public void addObserver(BidObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(BidObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(double newBidAmount) {
        for (BidObserver observer : observers) {
            observer.update(newBidAmount);
        }
    }

    public void placeBid(double amount, String bidderName) {
        if (amount > currentHighestBid) {
            this.currentHighestBid = amount;
            notifyObservers(amount);
        }
    }
}