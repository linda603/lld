package parkinglot;

public class BikeParkingSpot extends ParkingSpot {
    public BikeParkingSpot(int spotNumber) {
        super(spotNumber, VehicleType.BIKE);
    }

    @Override
    public boolean canParkVehicle(Vehicle vehicle) {
        return vehicle.getType() == VehicleType.BIKE;
    }
}
