package auction.manager;
import auction.model.Auction;
import auction.model.AuctionStatus;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
public class AuctionManager {
    private static final AuctionManager INSTANCE =
            new AuctionManager();
    private final ConcurrentHashMap<String, Auction> auctions =
            new ConcurrentHashMap<>();
    private final ScheduledExecutorService scheduler =
            Executors.newSingleThreadScheduledExecutor(task -> {
                Thread thread = new Thread(task, "auction-status-checker");
                thread.setDaemon(true);
                return thread;
            });
    private AuctionManager() {
        scheduler.scheduleAtFixedRate(
                this::checkAuctionTimes,
                0,
                1,
                TimeUnit.SECONDS
        );
    }
    public static AuctionManager getInstance() {
        return INSTANCE;
    }
    public void addAuction(Auction auction) {
        if (auction == null) {
            throw new IllegalArgumentException("Auction cannot be null");
        }
        Auction existing = auctions.putIfAbsent(
                auction.getId(), auction
        );
        if (existing != null) {
            throw new IllegalArgumentException(
                    "Auction ID already exists: " + auction.getId()
            );
        }
    }
    public Optional<Auction> findAuctionById(String id) {
        if (id == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(auctions.get(id));
    }
    public List<Auction> getAllAuctions() {
        return new ArrayList<>(auctions.values());
    }
    public boolean cancelAuction(String id) {
        return findAuctionById(id)
                .map(Auction::cancel)
                .orElse(false);
    }
    public boolean markAuctionPaid(String id) {
        return findAuctionById(id)
                .map(Auction::markPaid)
                .orElse(false);
    }
    public void checkAuctionTimes() {
        LocalDateTime now = LocalDateTime.now();
        for (Auction auction : auctions.values()) {
            AuctionStatus previous = auction.getStatus();
            auction.updateStatus(now);
            AuctionStatus current = auction.getStatus();
            if (previous != current) {
                System.out.println(
                        "Auction " + auction.getId()
                        + ": " + previous + " -> " + current
                );
            }
        }
    }
    public void shutdown() {
        scheduler.shutdown();
    }
}
