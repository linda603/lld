package carrentalsystem;

public class Car {
    private final String carId;
    private final String model;
    private final String licensePlate;
    private double pricePerDay;

    public Car(String carId, String model, String licensePlate, double pricePerDay) {
        this.carId = carId;
        this.model = model;
        this.licensePlate = licensePlate;
        this.pricePerDay = pricePerDay;
    }

    public String getId() {
        return carId;
    }

    public String getModel() {
        return model;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }
}
