package vendingmachine;

public class ItemInventory {
    private String itemId;
    private int quantity;

    public ItemInventory(String itemId, int quantity) {
        this.itemId = itemId;
        this.quantity = quantity;
    }
    
    public String getItemId() {
        return itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void updateQuantity(int quantity) {
        this.quantity += quantity;
    }

    public boolean isAvailable() {
        return this.quantity > 0;
    }
}
