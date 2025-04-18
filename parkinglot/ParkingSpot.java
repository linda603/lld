package parkinglot;

public abstract class ParkingSpot {
    private int spotNumber;
    private VehicleType vehicleType;
    private boolean isOccupied;
    private Vehicle parkedVehicle;

    public ParkingSpot(int spotNumber, VehicleType vehicleType) {
        this.spotNumber = spotNumber;
        this.vehicleType = vehicleType;
        this.isOccupied = false;
    }

    public int getSpotNumber() {
        return spotNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public Vehicle getParkedVehicle() {
        return parkedVehicle;
    }

    public abstract boolean canParkVehicle(Vehicle vehicle);

    public void parkVehicle(Vehicle vehicle) {
        if (isOccupied) {
            throw new IllegalStateException("Spot is occupied!");
        }

        if (!canParkVehicle(vehicle)) {
            throw new IllegalArgumentException("Spot type is not matched for the vehicle " + vehicle.getLicensePlate());
        }
        this.parkedVehicle = vehicle;
        this.isOccupied = true;
    }

    public void unparkVehicle() {
        if (!isOccupied) {
            throw new IllegalStateException("Spot is already removed!")
        }
        this.parkedVehicle = null;
        this.isOccupied = false;
    }
}
