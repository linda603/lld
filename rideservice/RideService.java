package rideservice;

import javax.swing.plaf.multi.MultiTableHeaderUI;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class RideService {
    private static RideService instance;
    private Map<String, Rider> riders;
    private Map<String, Driver> drivers;
    private Map<String, Ride> rides;
    private Queue<Ride> requestedRides;
    private AtomicInteger rideIdCounter;

    private RideService() {
        this.riders = new ConcurrentHashMap<>();
        this.drivers = new ConcurrentHashMap<>();
        this.rides = new ConcurrentHashMap<>();
        this.requestedRides = new ConcurrentLinkedQueue<>();
        rideIdCounter = new AtomicInteger(0);
    }

    public static synchronized RideService getInstance() {
        if (instance == null) {
            instance = new RideService();
        }
        return instance;
    }

    public void addRider(Rider rider) {
         riders.put(rider.getId(), rider);
    }

    public void addDriver(Driver driver) {
        drivers.put(driver.getId(), driver);
    }

    public Ride requestRide(Rider rider, Location source, Location destination) {
        String rideId = generateRideId();
        Ride ride = new Ride(rideId, rider, null, source, destination, RideStatus.REQUESTED, 0.0);
        double fare = calculateFare(ride);
        ride.setFare(fare);

        rides.put(rideId, ride);
        requestedRides.add(ride);
        notifyNearbyDrivers(ride);
        return ride;
    }

    private void notifyNearbyDrivers(Ride ride) {
        for (Driver driver : drivers.values()) {
            if (driver.getStatus() == DriverStatus.AVAILABLE) {
                double currDistance = calculateDistance(driver.getLocation(), ride.getSource());
                // Only inform drivers within 5km
                if (currDistance <= 5.0) {
                    notifyDriverNewRide(driver, ride);
                }
            }
        }
    }

    private String generateRideId() {
        int rideId = rideIdCounter.incrementAndGet();
        return "R" + String.valueOf(rideId);
    }

    private double calculateDistance(Location source, Location destination) {
        // Random generated distance from 1 to 5 km
        return Math.random() * 5 + 1;
    }

    public void notifyDriverNewRide(Driver driver, Ride ride) {
        // Driver accepts ride
        System.out.println("Notify driver " + driver.getId() + " - new ride: " + ride.getId());
        acceptRide(driver, ride);
    }

    public void acceptRide(Driver driver, Ride ride) {
        if (ride.getStatus() == RideStatus.REQUESTED) {
            ride.setStatus(RideStatus.ACCEPTED);
            ride.setDriver(driver);
            driver.setStatus(DriverStatus.BUSY);
            notifyRider(ride);
        }
    }

    public void notifyRider(Ride ride) {
        String message = switch (ride.getStatus()) {
            case ACCEPTED -> " has been accepted by Driver " + ride.getDriver().getName();
            case COMPLETED -> " is completed. Hope you enjoyed the ride.";
            case CANCELLED -> " is cancelled by rider.";
            default -> "";
        };
        System.out.println("Ride ID: " + ride.getId() + message);
    }

    public void notifyDriver(Ride ride) {
        String message = switch (ride.getStatus()) {
            case COMPLETED -> " is completed. Fare: $" + ride.getFare();
            case CANCELLED -> " is cancelled by the rider.";
            default -> "";
        };
        System.out.println("Ride ID: " + ride.getId() + message);
    }

    public void completeRide(Ride ride) {
        if (ride.getStatus() == RideStatus.ACCEPTED) {
            ride.setStatus(RideStatus.COMPLETED);
            notifyRider(ride);
            notifyDriver(ride);
        }
    }

    private double calculateFare(Ride ride) {
        double baseFare = 5.0;
        double perKmFare = 1.0;
        double distance = calculateDistance(ride.getSource(), ride.getDestination());
        double fare = baseFare + distance * perKmFare;

        return Math.round(fare * 100.0) / 100.0;
    }
}
