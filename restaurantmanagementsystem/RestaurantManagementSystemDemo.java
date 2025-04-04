package restaurantmanagementsystem;

public class RestaurantManagementSystemDemo {
    public static void main(String[] args) {
        RestaurantManagementSystem restaurantManager = RestaurantManagementSystem.getInstance();

        MenuItem item1 = new MenuItem("I1", "Item 1", "Description 1", 10.0);
        MenuItem item2 = new MenuItem("I2", "Item 2", "Description 2", 12.0);
        restaurantManager.addItem(item1, 100);
        restaurantManager.addItem(item2, 20);

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(item1, 2);
        shoppingCart.addItem(item2, 7);
        Order order1 = restaurantManager.placeOrder(new User("U1", "User 1"), shoppingCart);
        if (order1 != null) {
            order1.setStatus(OrderStatus.CONFIRMED);
            System.out.println("Order " + order1.getId() + " is created successully.");
        }
    }
}
