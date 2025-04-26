package vendingmachine;

public class DispenseState implements VendingMachineState {
    private VendingMachine vendingMachine;

    public DispenseState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void selectItem(String itemId) {
        System.out.println("Item is selected already. Please collect the dispensed Item!");
    }

    @Override
    public void insertNote(Note note) {
        System.out.println("Please collect the dispensed Item!");
    }

    @Override
    public void insertCoin(Coin coin) {
        System.out.println("Please collect the dispensed Item!");
    }

    @Override
    public void dispenseItem() {
        String itemId = vendingMachine.getSelectedItem();
        vendingMachine.updateQuantity(itemId, -1);
        System.out.println("Dispensed Item: " + vendingMachine.getItem(itemId).getName());
        vendingMachine.setState(vendingMachine.getReturnChangeState());
    }

    @Override
    public void returnChange() {
        System.out.println("Please collect the dispensed Item!");
    }
}
