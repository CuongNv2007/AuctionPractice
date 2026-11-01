package user;

import jakarta.validation.constraints.*;
import lombok.Getter;

@Getter
public abstract class User {
    
    @NotBlank(message = "ID không được để trống")
    private final String id;

    @NotBlank(message = "Tên đăng nhập không được để trống")
    @Size(min = 4, max = 20, message = "Tên đăng nhập từ 4 đến 20 ký tự")
    private String username;

    @NotBlank(message = "Mật khẩu không được để trống")
    @Size(min = 6, message = "Mật khẩu ít nhất 6 ký tự")
    private String password;

    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không hợp lệ")
    private String email;

    private final Role role;
    private boolean active;

    protected User(String id, String username, String password, String email, Role role) {
        this.id = id;
        this.setUsername(username);
        this.setPassword(password);
        this.setEmail(email);
        this.role = role;
        this.active = true;
    }

    public void setUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username không được rỗng");
        }
        this.username = username.trim();
    }

    public void setPassword(String password) {
        if (password == null || password.length() < 6) {
            throw new IllegalArgumentException("Mật khẩu phải từ 6 ký tự trở lên");
        }
        this.password = password;
    }

    public void setEmail(String email) {
        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new IllegalArgumentException("Email không đúng định dạng");
        }
        this.email = email;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public abstract void displayDashboard();
}