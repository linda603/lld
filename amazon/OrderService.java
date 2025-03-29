package amazon;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class OrderService {
    private static OrderService instance;
    private Map<User, UserRepository> userRepository;
    private Map<String, Product> products;
    private Map<String, Order> orders;
    private AtomicInteger orderIdCounter;

    private OrderService() {
        this.userRepository = new HashMap<>();
        this.products = new HashMap<>();
        this.orders = new HashMap<>();
        this.orderIdCounter = new AtomicInteger(0);
    }

    public static OrderService getInstance() {
        if (instance == null) {
            synchronized (OrderService.class) {
                if (instance == null) {
                    instance = new OrderService();
                }
            }
        }
        return instance;
    }

    public void addUser(User user) {
        userRepository.put(user, new UserRepository(user));
    }

    public void addProduct(Product product) {
        products.put(product.getId(), product);
    }

    public List<Product> searchProducts(String keyword) {
        List<Product> res = new ArrayList<>();

        for (Product product : products.values()) {
            if (product.getName().toLowerCase().contains(keyword.toLowerCase())) {
                res.add(product);
            }
        }
        return res;
    }

    public synchronized Order placeOrder(User user, ShoppingCart cart) {
        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderItem item : cart.getItems()) {
            Product product = item.getProduct();
            int requiredQuantity = item.getQuantity();

            if (!product.isAvailable(requiredQuantity)) {
                throw new ProductNotAvailableException("Product " + product.getName() + " does not have enough " + requiredQuantity + " items.");
            } else {
                product.updateQuantity(-requiredQuantity);
                orderItems.add(item);
            }
        }

        if (orderItems.isEmpty()) {
            throw new ProductNotAvailableException("No available products in the cart.");
        }

        String orderId = generateOrderId();
        Order order = new Order(orderId, user, orderItems);
        orders.put(orderId, order);
        userRepository.get(user).addOrder(order);
        cart.clear();
        return order;
    }

    public String generateOrderId() {
        int orderId = orderIdCounter.incrementAndGet();
        return "O" + String.valueOf(orderId);
    }
}