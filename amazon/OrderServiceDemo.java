package amazon;

import java.util.*;

public class OrderServiceDemo {
    public static void main(String[] args) {
        OrderService orderService = OrderService.getInstance();

        User user1 = new User("U1", "user1");
        orderService.addUser(user1);

        Product product1 = new Product("P1", "Product 1", "Description 1", 50.0, 10);
        Product product2 = new Product("P2", "Product 2", "Description 2", 30.0, 2);
        orderService.addProduct(product1);
        orderService.addProduct(product2);

        ShoppingCart cart1 = new ShoppingCart();
        cart1.addItem(product1, 2);
        cart1.addItem(product2, 1);
        Order order1 = orderService.placeOrder(user1, cart1);
        if (order1 != null) {
            System.out.println("Order placed: " + order1.getId());
        }
    }
}