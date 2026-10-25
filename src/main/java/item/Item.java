package item;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import auction.BidSubject;
import auction.BidObserver;

public abstract class Item implements BidSubject {
    // Sử dụng collection an toàn đa luồng thay vì ArrayList thông thường
    private final List<BidObserver> observers = new CopyOnWriteArrayList<>();

    protected int ID;
    protected int StartPrice;        // Giá khởi điểm
    protected String ItemName;       // Tên sản phẩm

    public Item(String ItemName, int StartPrice) {
        this.ItemName = ItemName;
        this.StartPrice = StartPrice;
    }

    @Override
    public void addObserver(BidObserver o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(BidObserver o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers(double newBidPrice) {
        // Tránh lỗi khi danh sách bị luồng khác thay đổi trong quá trình duyệt
        for (BidObserver observer : observers) {
            observer.update(newBidPrice);
        }
    }

    public String getName() {
        return ItemName;
    }

    public int getStartPrice() {
        return StartPrice;
    }

    public abstract int calculateFee();
}