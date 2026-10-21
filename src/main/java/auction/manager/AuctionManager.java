package auction.manager;
public class AuctionManager {
    private static final AuctionManager INSTANCE =
            new AuctionManager();
    private AuctionManager() {
    }
    public static AuctionManager getInstance() {
        return INSTANCE;
    }
}

