package restaurantmanagementsystem;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class ShoppingCart {
    private Map<String, OrderItem> items;

    public ShoppingCart() {
        this.items = new HashMap<>();
    }

    public void addItem(MenuItem menuItem, int quantity) {
        String menuItemId = menuItem.getId();
        if (items.containsKey(menuItemId)) {
            OrderItem orderItem = items.get(menuItemId);
            orderItem.updateQuantity(quantity);
        } else {
            items.put(menuItemId, new OrderItem(menuItem, quantity));
        }
    }

    public void removeItem(String menuItemId) {
        items.remove(menuItemId);
    }

    public void updateItemQuantity(String menuItemId, int quantity) {
        OrderItem orderItem = items.get(menuItemId);
        if (orderItem != null) {
            orderItem.updateQuantity(quantity);
        }
    }

    public List<OrderItem> getItems() {
        return new ArrayList<>(items.values());
    }

    public void clearCart() {
        items.clear();
    }

}
