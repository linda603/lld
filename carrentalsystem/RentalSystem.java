package carrentalsystem;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class RentalSystem {
    private static RentalSystem instance;
    private Map<String, CarInventory> CarInventories;
    private Map<String, Booking> bookings;
    private Map<User, UserBooking> userBookings;
    private int bookingCounterId;
    
    private RentalSystem() {
        this.CarInventories = new ConcurrentHashMap<>();
        this.bookings = new ConcurrentHashMap<>();
        this.userBookings = new ConcurrentHashMap<>();
        this.bookingCounterId = 1;
    }

    public static synchronized RentalSystem getInstance() {
        if (instance == null) {
            instance = new RentalSystem();
        }
        return instance;
    }

    public void addCar(Car car) {
        CarInventories.put(car.getId(), new CarInventory(car, 90));
    }

    public void addUser(User user) {
        userBookings.put(user, new UserBooking(user));
    }

    public synchronized Booking createBooking(User user, Car car, int startDate, int endDate) {
        CarInventory carInventory = CarInventories.get(car.getId());
        if (!carInventory.isCarAvailable(startDate, endDate)) {
            throw new CarNotAvailableException("Car is not available. Please check again.");
        } else {
            carInventory.bookCar(startDate, endDate);
        }
        String bookingId = generateBookingId();
        Booking booking = new Booking(bookingId, user, car, startDate, endDate);
        bookings.put(bookingId, booking);
        return booking;
    }

    private String generateBookingId() {
        return "B" + String.valueOf(bookingCounterId++);
    }
}
