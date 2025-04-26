package vendingmachine;

public class ReturnChangeState implements VendingMachineState {
    private VendingMachine vendingMachine;

    public ReturnChangeState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void selectItem(String itemId) {
        System.out.println("Please correct change!");
    }

    @Override
    public void insertNote(Note note) {
        System.out.println("Please correct change!");
    }

    @Override
    public void insertCoin(Coin coin) {
        System.out.println("Please correct change!");
    }

    @Override
    public void dispenseItem() {
        System.out.println("Please correct change!");
    }

    @Override
    public void returnChange() {
        double change = vendingMachine.getTotalPayment() - vendingMachine.getItem(vendingMachine.getSelectedItem()).getPrice();
        if (change > 0) {
            System.out.println("Returned change: " + change);
        } else {
            System.out.println("No change returned.");
        }

        vendingMachine.resetPayment();
        vendingMachine.resetSelectedItem();
        vendingMachine.setState(vendingMachine.getIdleState());
    }
}
