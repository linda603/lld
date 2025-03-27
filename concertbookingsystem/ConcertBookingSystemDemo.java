package concertbookingsystem;

import java.time.LocalDateTime;
import java.util.*;

public class ConcertBookingSystemDemo {
    public static void main(String [] args) {
        ConcertBookingSystem bookingSystem = ConcertBookingSystem.getInstance();

        User user1 = new User("U1", "user1");
        User user2 = new User("U2", "user2");

        Venue venue1 = new Venue("V1", 10, 10);
        Concert concert1 = new Concert("C1", "Singer 1", venue1, LocalDateTime.now());
        bookingSystem.addConcert(concert1);

        List<Seat> selectedSeats = List.of(concert1.getSeat("2-5"), concert1.getSeat("1-3"));
        Booking booking = bookingSystem.createBooking(user1, concert1, selectedSeats);
        if (booking != null) {
            System.out.println("Booking " + booking.getId() + " is created successfully.");
        }
    }
}