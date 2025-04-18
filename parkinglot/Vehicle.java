package parkinglot;

public abstract class Vehicle {
    protected String licensePlate;
    protected VehicleType vehicleType;
    private ParkingFeeStrategy feeStrategy;

    public Vehicle(String licensePlate, VehicleType vehicleType, ParkingFeeStrategy feeStrategy) {
        this.licensePlate = licensePlate;
        this.vehicleType = vehicleType;
        this.feeStrategy = feeStrategy;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public VehicleType getType() {
        return vehicleType;
    }

    public double calculateFee(int duration, DurationType durationType) {
        return feeStrategy.calculateFee(vehicleType, duration, durationType);
    }
}
