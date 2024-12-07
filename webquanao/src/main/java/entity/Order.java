package entity;

import java.util.List;

import java.math.BigDecimal;
import java.sql.Date;

public class Order {
    private int id;
    private Account customner;
    private List<Item> items;
    private int status;

    public Order(int id, Account customner, List<Item> list, int status) {
        this.id = id;
        this.customner = customner;
        this.items = items;
        this.status = status;
    }
    public Order(){
        this.status=0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Account getCustomner() {
        return customner;
    }

    public void setCustomner(Account customner) {
        this.customner = customner;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
//    public double getTotalPrice() {
//        return items.stream()
//                .mapToDouble(item -> item.getPrice() * item.getQuantity())
//                .sum();
//    }
}
