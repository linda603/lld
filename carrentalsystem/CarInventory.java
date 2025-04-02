package carrentalsystem;

import java.util.Arrays;

public class CarInventory {
    private final Car car;
    private boolean[] dates;

    public CarInventory(Car car, int totalDates) {
        this.car = car;
        this.dates = new boolean[totalDates];
        Arrays.fill(this.dates, false);
    }

    public Car getCar() {
        return car;
    }

    public boolean isCarAvailable(int startDate, int endDate) {
        for (int day = startDate; day <= endDate; day++) {
            if (dates[day]) {
                return false;
            }
        }
        return true;
    }

    public void bookCar(int startDate, int endDate) {
        for (int day = startDate; day <= endDate; day++) {
            dates[day] = true;
        }
    }
}
