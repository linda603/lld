package amazon;

import java.util.*;

public class ShoppingCart {
    private Map<String, OrderItem> items;

    public ShoppingCart() {
        items = new HashMap<>();
    }

    public void addItem(Product product, int quantity) {
        String productId = product.getId();
        if (items.containsKey(productId)) {
            OrderItem orderItem = items.get(productId);
            orderItem.updateQuantity(quantity);
        } else {
            items.put(productId, new OrderItem(product, quantity));
        }
    }

    public void removeItem(String productId) {
        items.remove(productId);
    }

    public void updateItemQuantity(String productId, int quantity) {
        OrderItem orderItem = items.get(productId);
        if (orderItem != null) {
            orderItem.updateQuantity(quantity);
        }
    }

    public List<OrderItem> getItems() {
        return new ArrayList<>(items.values());
    }

    public void clear() {
        items.clear();
    }
}