package vendingmachine;

public class Item {
    private String itemId;
    private String name;
    private double price;

    public Item(String itemId, String name, double price) {
        this.itemId = itemId;
        this.name = name;
        this.price = price;
    }

    public String getId() {
        return itemId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double updatedPrice) {
        price = updatedPrice;
    }
}