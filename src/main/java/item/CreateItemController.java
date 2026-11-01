package item;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import item.ItemFactory;

public class CreateItemController {

    // Ánh xạ các fx:id từ file FXML
    @FXML
    private TextField txtItemName;

    @FXML
    private TextField txtStartPrice;

    @FXML
    private ComboBox<String> cbItemType;

    @FXML
    private Button btnCreateItem;

    // Hàm initialize chạy tự động khi load FXML để thiết lập dữ liệu ban đầu
    @FXML
    public void initialize() {
        cbItemType.getItems().addAll("Electronics", "Art", "Vehicle");
    }

    // Hàm xử lý sự kiện khi nhấn nút "Tạo sản phẩm"
    @FXML
    public void handleCreateItem() {
        String ItemName = txtItemName.getText();
        String PriceText = txtStartPrice.getText();
        String ItemType = cbItemType.getValue();

        // Kiểm tra nhập liệu cơ bản
        if (ItemName.isEmpty() || PriceText.isEmpty() || ItemType == null) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Vui lòng điền đầy đủ thông tin!");
            return;
        }

        try {
            int StartPrice = Integer.parseInt(PriceText);

            // Gọi Factory Method để tạo đối tượng theo đúng loại (Polymorphism)
            Item newItem = ItemFactory.createItem(ItemType, ItemName, StartPrice);

            if (newItem != null) {
                showAlert(Alert.AlertType.INFORMATION, "Thành công", "Đã tạo: " + ItemName);
                // Xóa form sau khi tạo thành công
                txtItemName.clear();
                txtStartPrice.clear();
                cbItemType.getSelectionModel().clearSelection();
            }

        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Giá khởi điểm phải là một số hợp lệ!");
        }
    }

    // Hàm tiện ích hiển thị hộp thoại thông báo
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}