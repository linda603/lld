package carrentalsystem;

public class Booking {
    private final String bookingId;
    private final User user;
    private final Car car;
    private final int startDate;
    private final int endDate;
    private final double totalPrice;

    public Booking(String bookingId, User user, Car car, int startDate, int endDate) {
        this.bookingId = bookingId;
        this.user = user;
        this.car = car;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalPrice = calculateTotalPrice();
    }

    private double calculateTotalPrice() {
        int duration = endDate - startDate;
        return car.getPricePerDay() * duration;
    }

    public String getId() {
        return bookingId;
    }
}
