package carrentalsystem;

public class RentalSystemDemo {
    public static void main(String[] args) {
        RentalSystem rentalSystem = RentalSystem.getInstance();

        User user1 = new User("U1", "User 1");
        rentalSystem.addUser(user1);

        Car car1 = new Car("C1", "Model 1", "XXXX01", 100.0);
        rentalSystem.addCar(car1);

        Booking booking = rentalSystem.createBooking(user1, car1, 1, 10);
        if (booking != null) {
            System.out.println("Booking is successful. Booking ID: " + booking.getId());
        }
    }
}
