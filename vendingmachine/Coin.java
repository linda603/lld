package vendingmachine;

public enum Coin {
    ONE_CENT(0.1),
    TWO_CENT(0.2),
    FIVE_CENT(0.5);

    private double value;

    Coin(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
