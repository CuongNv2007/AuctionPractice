package item;

public class Electronics extends Item{
    public Electronics(String ItemName, int StartPrice) {
        super(ItemName, StartPrice);
    }

    @Override
    public int calculateFee(){
        //Đa hình: tính phí riêng cho từng sản phẩm
        return getStartPrice(); //Chưa nhân hệ số
    }
}