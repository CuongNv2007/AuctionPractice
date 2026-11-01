package item;

public class Vehicle extends Item {
    public Vehicle(String ItemName, int StartPrice){
        super(ItemName, StartPrice);
    }
    @Override
    public int calculateFee(){
        return getStartPrice();
    }
}
