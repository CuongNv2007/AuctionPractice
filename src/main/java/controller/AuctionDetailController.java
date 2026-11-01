package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class AuctionDetailController {

    @FXML private Label lblItemName;
    @FXML private Label lblTimeRemaining;
    @FXML private Label lblCurrentPrice;
    @FXML private Label lblHighestBidder;
    @FXML private Label lblStartingPrice;
    @FXML private Label lblMinIncrement;
    @FXML private ListView<String> lvBidHistory;
    @FXML private TextField txtBidAmount;
    @FXML private Button btnPlaceBid;

    @FXML
    public void initialize() {
        // Phương thức tự động kích hoạt sau khi FXMLLoader tiêm xong các biến @FXML
    }

    @FXML
    private void handlePlaceBid() {
        // Logic xử lý khi bấm nút ĐẶT GIÁ NGAY
    }
}