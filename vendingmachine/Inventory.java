package vendingmachine;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Inventory {
    private Map<String, Item> items;
    private Map<String, ItemInventory> inventory;

    public Inventory() {
        items = new ConcurrentHashMap<>();
        inventory = new ConcurrentHashMap<>();
    }

    public void addItem(Item item, int quantity) {
        items.put(item.getId(), item);
        inventory.put(item.getId(), new ItemInventory(item.getId(), quantity));
    }

    public void removeItem(String itemId) {
        items.remove(itemId);
    }

    public void updateItemQuantity(String itemId, int quantity) {
        ItemInventory itemInventory = inventory.get(itemId);
        itemInventory.updateQuantity(quantity);
    }

    public boolean isAvailable(String itemId) {
        return inventory.get(itemId).isAvailable();
    }

    public Item getItem(String itemId) {
        return items.get(itemId);
    }

}
