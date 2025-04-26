package vendingmachine;

public interface VendingMachineState {
    void selectItem(String itemId);

    void insertNote(Note note);

    void insertCoin(Coin coin);

    void dispenseItem();

    void returnChange();
}
