package user;

import jakarta.validation.constraints.Min;
import lombok.Getter;

@Getter
public class Seller extends User {

    @Min(value = 0, message = "Điểm uy tín không âm")
    private double rating;
    private int totalAuctions;

    public Seller(String id, String username, String password, String email) {
        super(id, username, password, email, Role.SELLER);
        this.rating = 5.0;
        this.totalAuctions = 0;
    }

    public void updateRating(double newRating) {
        if (newRating < 0 || newRating > 5.0) {
            throw new IllegalArgumentException("Đánh giá phải từ 0 đến 5 sao");
        }
        this.rating = newRating;
    }

    @Override
    public void displayDashboard() {
        System.out.println("Giao diện người bán - Đánh giá: " + rating);
    }
}