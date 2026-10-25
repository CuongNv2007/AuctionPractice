package item;

public class Art extends Item {
    public Art(String ItemName, int StartPrice){
        super(ItemName, StartPrice);
    }
    @Override
    public int calculateFee(){
        return getStartPrice();
    }
}
