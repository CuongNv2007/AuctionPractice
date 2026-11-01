package auction;

public interface BidSubject {
    void addObserver(BidObserver observer);
    void removeObserver(BidObserver observer);
    void notifyObservers(double newBidPrice);
}