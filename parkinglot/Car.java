package parkinglot;

public class Car extends Vehicle {
    public Car(String licensePlate, ParkingFeeStrategy feeStrategy) {
        super(licensePlate, VehicleType.CAR, feeStrategy);
    }
}
