package parkinglot;

public interface ParkingFeeStrategy {
    double calculateFee(VehicleType vehicleType, int duration, DurationType durationType);
}