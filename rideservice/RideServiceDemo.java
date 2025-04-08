package rideservice;

public class RideServiceDemo {
    public static void main(String[] args) {
        RideService rideService = RideService.getInstance();

        Rider rider1 = new Rider("U1", "Rider 1", new Location(30.0987, -100.0675));
        Driver driver1 = new Driver("D1", "Driver 1", new Location(30.1908, -100.0670));
        Driver driver2 = new Driver("D2", "Driver 2", new Location(30.8890, -90.0690));
        //Rider rider1 = new Rider("U1", "Driver 2", new Location(30.8890, -90.0690));
        rideService.addRider(rider1);
        rideService.addDriver(driver1);
        rideService.addDriver(driver2);

        Ride ride = rideService.requestRide(rider1, rider1.getLocation(), new Location(56.9807, -122.9087));
        rideService.completeRide(ride);
    }
}
