package concertbookingsystem;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcertBookingSystem {
    private static volatile ConcertBookingSystem instance;
    private final Map<String, User> users;
    private final Map<String, Concert> concerts;
    private final Map<String, Booking> bookings;
    private final Object lock = new Object();
    private final AtomicInteger bookingIdCounter = new AtomicInteger(0);

    private ConcertBookingSystem() {
        users = new ConcurrentHashMap<>();
        concerts = new ConcurrentHashMap<>();
        bookings = new ConcurrentHashMap<>();
    }

    public static ConcertBookingSystem getInstance() {
        if (instance == null) {
            synchronized (ConcertBookingSystem.class) {
                if (instance == null) {  // Double-check
                    instance = new ConcertBookingSystem();
                }
            }
        }
        return instance;
    }

    public void addUser(User user) {
        users.put(user.getId(), user);
    }

    public void addConcert(Concert concert) {
        concerts.put(concert.getId(), concert);
    }

    public Booking createBooking(User user, Concert concert, List<Seat> seats) {
        synchronized(lock) {
            if (!areSeatsAvailable(seats)) {
                throw new SeatNotAvailableException("One of the seats is not available. Please choose another seat.");
            } else {
                bookSeats(seats);

                // Create a booking
                String bookingId = generateBookingId();
                Booking booking = new Booking(bookingId, user, concert, seats);
                bookings.put(bookingId, booking);
                return booking;
            }
        }
    }

    private boolean areSeatsAvailable(List<Seat> seats) {
        for (Seat seat : seats) {
            if (seat.getStatus() != SeatStatus.AVAILABLE) {
                return false;
            }
        }
        return true;
    }

    private void bookSeats(List<Seat> seats) {
        for (Seat seat : seats) {
            seat.book();
        }
    }

    private String generateBookingId() {
        int bookingId = bookingIdCounter.incrementAndGet();
        return "B" + String.valueOf(bookingId);
    }
}