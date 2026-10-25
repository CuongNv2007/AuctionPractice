package item;

public class ItemFactory {
    //gọi mà không cần dùng new
    public static Item createItem(String itemType, String ItemName, int StartPrice) {
        if (itemType == null || itemType.trim().isEmpty()){
            throw new IllegalArgumentException("Loại sản phẩm bị để trống");
        }

        //chuyển hết về chữ hoa để không bị lỗi chữ thường / chữ hoa
        switch (itemType.toUpperCase()){
            case "ELECTRONICS":
                return new Electronics(ItemName, StartPrice);
            case "ART":
                return new Art(ItemName, StartPrice);
            case "VEHICLE":
                return new Vehicle(ItemName, StartPrice);
            default:
                throw new IllegalArgumentException("Không hỗ trợ loại sản phẩm: " + itemType);
        }
    }
}
