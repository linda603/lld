package parkinglot;

public class Bike extends Vehicle {
    public Bike(String licensePlate, ParkingFeeStrategy feeStrategy) {
        super(licensePlate, VehicleType.BIKE, feeStrategy);
    }
}
