package restaurantmanagementsystem;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RestaurantManagementSystem {
    private static RestaurantManagementSystem instance;
    private Map<String, MenuItemInventory> menuItemInventories;
    private Map<String, Order> orders;
    private Map<String, Reservation> reservations;
    private Map<String, User> users;
    private int orderIdCounter;

    private RestaurantManagementSystem() {
        this.menuItemInventories = new ConcurrentHashMap<>();
        this.orders = new ConcurrentHashMap<>();
        this.reservations = new ConcurrentHashMap<>();
        this.users = new ConcurrentHashMap<>();
        this.orderIdCounter = 1;
    }

    public static synchronized RestaurantManagementSystem getInstance() {
        if (instance == null) {
            instance = new RestaurantManagementSystem();
        }
        return instance;
    }

    public void addItem(MenuItem item, int quantity) {
        menuItemInventories.put(item.getId(), new MenuItemInventory(item, quantity));
    }

    public void addUser(User user) {
        users.put(user.getId(), user);
    }

    public void updateItemQuantity(String itemId, int quantity) {
        menuItemInventories.get(itemId).updateQuantity(quantity);
    }

    public synchronized Order placeOrder(User user, ShoppingCart cart) {
        List<OrderItem> orderItems = cart.getItems();
        
        if (!isAvailable(orderItems)) {
            return null;
        }
        updateInventory(orderItems);
        String orderId = generateOrderId();
        Order order = new Order(orderId, user, orderItems);
        orders.put(orderId, order);
        return order;
    }

    public void addReservation(Reservation reservation) {
        reservations.put(reservation.getId(), reservation);
    }

    private String generateOrderId() {
        return "O" + String.valueOf(orderIdCounter++);
    }

    private boolean isAvailable(List<OrderItem> orderItems) {
        for (OrderItem orderItem : orderItems) {
            String menuItemId = orderItem.getMenuItem().getId();
            int quantity = orderItem.getQuantity();
            if (!menuItemInventories.get(menuItemId).isAvailable(quantity)) {
                return false;
            }
        }
        return true;
    }

    private void updateInventory(List<OrderItem> orderItems) {
        for (OrderItem orderItem : orderItems) {
            String menuItemId = orderItem.getMenuItem().getId();
            int quantity = orderItem.getQuantity();
            menuItemInventories.get(menuItemId).updateQuantity(quantity);
        }
    }
}