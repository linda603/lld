package restaurantmanagementsystem;

import java.util.List;

public class Order {
    private String id;
    private User user;
    private List<OrderItem> items;
    private double totalPrice;
    private OrderStatus status;

    public Order(String id, User user, List<OrderItem> items) {
        this.id = id;
        this.user = user;
        this.items = items;
        this.totalPrice = calculateTotalPrice();
        this.status = OrderStatus.PENDING;
    } 

    public String getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    private double calculateTotalPrice() {
        return items.stream().map(item -> item.getMenuItem().getPrice() * item.getQuantity())
                    .mapToDouble(f -> f).sum();
    }
}
