package rideservice;

public class Ride {
    private String id;
    private Rider rider;
    private Driver driver;
    private Location source;
    private Location destination;
    private RideStatus status;
    private double fare;

    public Ride(String id, Rider rider, Driver driver, Location source, Location destination,
                RideStatus status, double fare) {
        this.id = id;
        this.rider = rider;
        this.driver = driver;
        this.source = source;
        this.destination = destination;
        this.status = status;
        this.fare = fare;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Rider getRider() {
        return rider;
    }

    public void setRider(Rider rider) {
        this.rider = rider;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Location getSource() {
        return source;
    }

    public void setSource(Location source) {
        this.source = source;
    }

    public Location getDestination() {
        return destination;
    }

    public void setDestination(Location destination) {
        this.destination = destination;
    }

    public RideStatus getStatus() {
        return status;
    }

    public void setStatus(RideStatus status) {
        this.status = status;
    }

    public double getFare() {
        return fare;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }
}
