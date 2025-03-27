package concertbookingsystem;

import java.util.List;

public class Booking {
    private final String bookingId;
    private final User user;
    private final Concert concert;
    private final List<Seat> seats;
    private final double totalPrice;
    private BookingStatus status;

    public Booking(String bookingId, User user, Concert concert, List<Seat> seats) {
        this.bookingId = bookingId;
        this.user = user;
        this.concert = concert;
        this.seats = List.copyOf(seats);
        this.totalPrice = calculateTotalPrice();
        this.status = BookingStatus.CONFIRMED;
    }

    private double calculateTotalPrice() {
        double totalPrice = 0.0;
        for (Seat seat : seats) {
            totalPrice += seat.getPrice();
        }
        return totalPrice;
    }

    public String getId() {
        return bookingId;
    }

    public User getUser() {
        return user;
    }

    public Concert getConcert() {
        return concert;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public BookingStatus getStatus() {
        return status;
    }
}