package parkinglot;

public class CarParkingSpot extends ParkingSpot {
    public CarParkingSpot(int spotNumber) {
        super(spotNumber, VehicleType.CAR);
    }

    @Override
    public boolean canParkVehicle(Vehicle vehicle) {
        return vehicle.getType() == VehicleType.CAR;
    }
}
