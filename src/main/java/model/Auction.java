package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Auction {
    private final String auctionId;
    private final String itemId;           // Khóa ngoại liên kết sản phẩm của Thành viên 2
    private final String itemName;
    private final double startingPrice;    // Giá khởi điểm
    private final double stepIncrement;    // Bước giá tối thiểu bắt buộc
    private double currentHighestBid;      // Mức giá cao nhất hiện tại
    private String highestBidderId;        // Người đang giữ giá cao nhất
    private String highestBidderName;
    private final LocalDateTime endTime;   // Thời điểm đóng phiên

    // Danh sách lưu lịch sử các lượt đặt giá
    private final List<Bid> bidHistory = new ArrayList<>();

    public Auction(String auctionId, String itemId, String itemName,
                   double startingPrice, double stepIncrement, LocalDateTime endTime) {
        if (startingPrice < 0 || stepIncrement <= 0) {
            throw new IllegalArgumentException("Giá khởi điểm và bước giá không hợp lệ!");
        }
        if (endTime.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Thời gian kết thúc phiên phải ở tương lai!");
        }

        this.auctionId = auctionId;
        this.itemId = itemId;
        this.itemName = itemName;
        this.startingPrice = startingPrice;
        this.stepIncrement = stepIncrement;
        this.currentHighestBid = startingPrice;
        this.endTime = endTime;
    }

    // --- CÁC HÀM GETTER ĐÓNG GÓI CHẶT CHẼ ---
    public String getAuctionId() { return auctionId; }
    public String getItemId() { return itemId; }
    public String getItemName() { return itemName; }
    public double getStartingPrice() { return startingPrice; }
    public double getStepIncrement() { return stepIncrement; }
    public double getCurrentHighestBid() { return currentHighestBid; }
    public String getHighestBidderId() { return highestBidderId; }
    public String getHighestBidderName() { return highestBidderName; }
    public LocalDateTime getEndTime() { return endTime; }

    /**
     * Trả về danh sách lịch sử đặt giá ở dạng chỉ đọc (Unmodifiable List).
     * Ngăn chặn bên ngoài tự ý gọi .clear() hoặc .add() làm sai lệch dữ liệu.
     */
    public List<Bid> getBidHistory() {
        return Collections.unmodifiableList(this.bidHistory);
    }

    // --- CÁC HÀM CẬP NHẬT DỮ LIỆU CƠ BẢN (TUẦN 6) ---
    public void recordNewHighestBid(Bid validBid) {
        this.currentHighestBid = validBid.getAmount();
        this.highestBidderId = validBid.getBidderId();
        this.highestBidderName = validBid.getBidderName();
        this.bidHistory.add(validBid);
    }
}