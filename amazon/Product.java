package amazon;

public class Product {
    private String productId;
    private String name;
    private String description;
    private double price;
    private int quantity;

    public Product(String productId, String name, String description, double price, int quantity) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    public String getId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public synchronized void updateQuantity(int quantity) {
        this.quantity += quantity;
    }

    public synchronized boolean isAvailable(int quantity) {
        return this.quantity >= quantity;
    }
}