package parkinglot;

public class Truck extends Vehicle {
    public Truck(String licensePlate, ParkingFeeStrategy feeStrategy) {
        super(licensePlate, VehicleType.TRUCK, feeStrategy);
    }
}
