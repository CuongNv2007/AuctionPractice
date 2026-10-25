package model;

import java.time.LocalDateTime;

/**
 * Đại diện cho một lượt đặt giá trong phiên đấu giá.
 * Lớp này được thiết kế theo dạng Immutable Object (Bất biến) để an toàn đa luồng.
 */
public final class Bid {
    private final String bidId;
    private final String bidderId;   // Mã định danh người đặt giá (liên kết với TV4)
    private final String bidderName; // Tên hiển thị người đặt giá
    private final double amount;     // Số tiền đặt
    private final LocalDateTime timestamp; // Thời điểm đặt giá

    public Bid(String bidId, String bidderId, String bidderName, double amount) {
        // Kiểm tra hợp lệ dữ liệu đầu vào (Validation)
        if (bidId == null || bidId.isBlank()) {
            throw new IllegalArgumentException("Mã lượt đặt giá không được để trống!");
        }
        if (bidderId == null || bidderId.isBlank()) {
            throw new IllegalArgumentException("Mã người đặt giá không hợp lệ!");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Số tiền đặt giá phải lớn hơn 0!");
        }

        this.bidId = bidId;
        this.bidderId = bidderId;
        this.bidderName = bidderName;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
    }

    // CHỈ CÓ GETTER - TUYỆT ĐỐI KHÔNG CÓ SETTER (Bảo toàn tính bất biến)
    public String getBidId() {
        return bidId;
    }

    public String getBidderId() {
        return bidderId;
    }

    public String getBidderName() {
        return bidderName;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s đặt: %,.2f VNĐ", timestamp, bidderName, amount);
    }
}