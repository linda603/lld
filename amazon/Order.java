package amazon;

import java.util.*;

public class Order {
    private String orderId;
    private User user;
    private List<OrderItem> items;
    private double totalAmount;
    private OrderStatus status;

    public Order(String orderId, User user, List<OrderItem> items) {
        this.orderId = orderId;
        this.user = user;
        this.items = items;
        this.totalAmount = calculateTotalAmount();
        this.status = OrderStatus.ORDER_PLACED;
    }

    private double calculateTotalAmount() {
        double res = 0.0;
        for (OrderItem item : items) {
            res += item.getProduct().getPrice();
        }
        return res;
    }

    public String getId() {
        return orderId;
    }

    public User getUser() {
        return user;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}