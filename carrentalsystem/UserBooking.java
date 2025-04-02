package carrentalsystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserBooking {
    private final User user;
    private HashMap<String, Booking> bookings;

    public UserBooking(User user) {
        this.user = user;
        this.bookings = new HashMap<>();
    }

    public User getUser() {
        return user;
    }

    public List<Booking> getBookings() {
        return new ArrayList<>(bookings.values());
    }

    public void addBooking(Booking booking) {
        bookings.put(booking.getId(), booking);
    }
}
