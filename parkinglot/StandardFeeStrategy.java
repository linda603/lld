package parkinglot;

public class StandardFeeStrategy implements ParkingFeeStrategy {
    @Override
    public double calculateFee(VehicleType vehicleType, int duration, DurationType durationType) {
        switch (vehicleType) {
            case VehicleType.BIKE:
                return durationType == DurationType.HOURS ? duration * 2.0 : duration * 10;
            case VehicleType.CAR:
                return durationType == DurationType.HOURS ? duration * 5.0 : duration * 15;
            case VehicleType.TRUCK:
                return durationType == DurationType.HOURS ? duration * 7.0 : duration * 20;
            default:
                return durationType == DurationType.HOURS ? duration * 10.0 : duration * 30.0;

        }
    }
}
