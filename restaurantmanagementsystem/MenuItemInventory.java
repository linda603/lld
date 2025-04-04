package restaurantmanagementsystem;

public class MenuItemInventory {
    private MenuItem menuItem;
    private int quantity;

    public MenuItemInventory(MenuItem menuItem, int quantity) {
        this.menuItem = menuItem;
        this.quantity = quantity;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public void updateQuantity(int quantity) {
        this.quantity += quantity;
    }

    public boolean isAvailable(int quantity) {
        return this.quantity >= quantity;
    }
}
