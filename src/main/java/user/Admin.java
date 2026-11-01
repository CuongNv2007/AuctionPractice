package user;

import lombok.Getter;

@Getter
public class Admin extends User {

    private String adminCode;

    public Admin(String id, String username, String password, String email, String adminCode) {
        super(id, username, password, email, Role.ADMIN);
        this.adminCode = adminCode;
    }

    public void lockUserAccount(User targetUser) {
        targetUser.setActive(false);
        System.out.println("Đã khóa tài khoản: " + targetUser.getUsername());
    }

    @Override
    public void displayDashboard() {
        System.out.println("Giao diện Quản trị viên - Mã Admin: " + adminCode);
    }
}