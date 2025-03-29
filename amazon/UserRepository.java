package amazon;

import java.util.*;

public class UserRepository {
    private User user;
    private List<Order> orders;

    public UserRepository(User user) {
        this.user = user;
        this.orders = new ArrayList<>();
    }

    public User getUser() {
        return user;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }
}