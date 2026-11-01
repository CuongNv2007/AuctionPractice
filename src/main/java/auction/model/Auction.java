package auction.model;
import java.time.LocalDateTime;
public class Auction {
    private final String id;
    private final String title;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private AuctionStatus status;
    public Auction(String id, String title,
                   LocalDateTime startTime,
                   LocalDateTime endTime) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("ID cannot be empty");
        }
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        if (startTime == null || endTime == null
                || !endTime.isAfter(startTime)) {
            throw new IllegalArgumentException("Invalid auction time");
        }
        this.id = id;
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = AuctionStatus.OPEN;
    }
    public synchronized void updateStatus(LocalDateTime now) {
        if (status == AuctionStatus.CANCELED
                || status == AuctionStatus.PAID) {
            return;
        }
        if (status == AuctionStatus.OPEN
                && !now.isBefore(startTime)) {
            status = AuctionStatus.RUNNING;
        }
        if (status == AuctionStatus.RUNNING
                && !now.isBefore(endTime)) {
            status = AuctionStatus.FINISHED;
        }
    }
    public synchronized boolean cancel() {
        if (status != AuctionStatus.OPEN
                && status != AuctionStatus.RUNNING) {
            return false;
        }
        status = AuctionStatus.CANCELED;
        return true;
    }
    public synchronized boolean markPaid() {
        if (status != AuctionStatus.FINISHED) {
            return false;
        }
        status = AuctionStatus.PAID;
        return true;
    }
    public String getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public LocalDateTime getStartTime() {
        return startTime;
    }
    public LocalDateTime getEndTime() {
        return endTime;
    }
    public synchronized AuctionStatus getStatus() {
        return status;
    }
}

