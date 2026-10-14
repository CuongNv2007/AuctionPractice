import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Đọc file giao diện hello.fxml từ thư mục resources
        Parent root = FXMLLoader.load(getClass().getResource("/hello.fxml"));

        // Gắn giao diện vào Scene và hiển thị cửa sổ Stage
        primaryStage.setTitle("Auction System - Demo");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}