package vendingmachine;

public enum Note {
    ONE(1),
    TWO(2),
    FIVE(5),
    TEN(10);

    private int value;

    Note(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
