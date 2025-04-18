package parkinglot;

public class TruckParkingSpot extends ParkingSpot {
    public TruckParkingSpot(int spotNumber) {
        super(spotNumber, VehicleType.TRUCK);
    }

    @Override
    public boolean canParkVehicle(Vehicle vehicle) {
        return vehicle.getType() == VehicleType.TRUCK;
    }
}
