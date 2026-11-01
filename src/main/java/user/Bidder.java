package user;

import jakarta.validation.constraints.Min;
import lombok.Getter;

@Getter
public class Bidder extends User {

    @Min(value = 0, message = "Số dư ví không được âm")
    private double walletBalance;

    public Bidder(String id, String username, String password, String email, double initialBalance) {
        super(id, username, password, email, Role.BIDDER);
        this.setWalletBalance(initialBalance);
    }

    public void setWalletBalance(double walletBalance) {
        if (walletBalance < 0) {
            throw new IllegalArgumentException("Số dư ví không được là số âm");
        }
        this.walletBalance = walletBalance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Số tiền nạp phải lớn hơn 0");
        }
        this.walletBalance += amount;
    }

    @Override
    public void displayDashboard() {
        System.out.println("Giao diện người mua - Số dư: " + walletBalance);
    }
}