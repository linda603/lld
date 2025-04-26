package vendingmachine;

public class IdleState implements VendingMachineState {
    private VendingMachine vendingMachine;

    public IdleState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void selectItem(String itemId) {
        if (vendingMachine.isAvailable(itemId)) {
            vendingMachine.setSelectedItem(itemId);
            vendingMachine.setState(vendingMachine.getReadyState());
            System.out.println("Selected Item: " + vendingMachine.getItem(itemId).getName());
        } else {
            System.out.println("Item is not available. Please choose another Item!");
        }
    }

    @Override
    public void insertNote(Note note) {
        System.out.println("Please selecte an Item first!");
    }

    @Override
    public void insertCoin(Coin coin) {
        System.out.println("Please selecte an Item first!");
    }

    @Override
    public void dispenseItem() {
        System.out.println("Please selecte an Item first!");
    }

    @Override
    public void returnChange() {
        System.out.println("Please selecte an Item first!");
    }
}
