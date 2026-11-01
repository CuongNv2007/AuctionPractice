package auction;

public interface BidObserver {
    void update(double newBidAmount, String bidderName);
    void update(double newBidPrice);
}