package rideservice;

public class Driver {
    private String id;
    private String name;
    private String phone;
    private Location location;
    private DriverStatus status;

    public Driver(String id, String name, Location location) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.status = DriverStatus.AVAILABLE;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public DriverStatus getStatus() {
        return status;
    }

    public void setStatus(DriverStatus status) {
        this.status = status;
    }
}
