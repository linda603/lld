package parkinglot;

public class PremiumFeeStrategy implements ParkingFeeStrategy {
    @Override
    public double calculateFee(VehicleType vehicleType, int duration, DurationType durationType) {
        switch (vehicleType) {
            case VehicleType.BIKE:
                return durationType == DurationType.HOURS ? duration * 4.0 : duration * 20;
            case VehicleType.CAR:
                return durationType == DurationType.HOURS ? duration * 5.0 : duration * 30;
            case VehicleType.TRUCK:
                return durationType == DurationType.HOURS ? duration * 14.0 : duration * 40;
            default:
                return durationType == DurationType.HOURS ? duration * 20.0 : duration * 60.0;

        }
    }
}
