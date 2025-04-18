package parkinglot;

import java.util.List;

public class ParkingLot {
    private List<ParkingSpot> spots;

    public ParkingLot(List<ParkingSpot> spots) {
        this.spots = spots;
    }

    public ParkingSpot findAvailableSpot(VehicleType vehicleType) {
        for (ParkingSpot spot : spots) {
            if (!spot.isOccupied() && spot.getVehicleType() == vehicleType) {
                return spot;
            }
        }
        return null;
    }

    public ParkingSpot parkVehicle(Vehicle vehicle) {
        ParkingSpot spot = findAvailableSpot(vehicle.getType());
        if (spot != null) {
            spot.parkVehicle(vehicle);
            System.out.println("Vehicle " + vehicle.getLicensePlate() + " parked successfully at " + spot.getSpotNumber());
            return spot;
        }
        System.out.println("No available spot found for vehicle " + vehicle.getLicensePlate());
        return null;
    }

    public void unparkVehicle(ParkingSpot spot,  Vehicle vehicle) {
        if (spot != null && spot.isOccupied() && spot.getParkedVehicle() == vehicle) {
            spot.unparkVehicle();
            System.out.println("Vehicle " + vehicle.getLicensePlate() + " unparked successfully");
        } else {
            System.out.println("Invalid spot of vehicle " + vehicle.getLicensePlate());
        }
    }
}
