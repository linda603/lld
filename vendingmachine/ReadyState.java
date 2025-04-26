package vendingmachine;

class ReadyState implements VendingMachineState {
    private VendingMachine vendingMachine;

    public ReadyState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void selectItem(String ItemId) {
        System.out.println("Item is selected already. Please make a payment!");
    }

    @Override
    public void insertNote(Note note) {
        vendingMachine.addNote(note);
        System.out.println("Inserted note: " + note);
        checkPayment();
    }

    @Override
    public void insertCoin(Coin coin) {
        vendingMachine.addCoin(coin);
        System.out.println("Inserted coin: " + coin);
        checkPayment();
    }

    @Override
    public void dispenseItem() {
        System.out.println("Please make a payment!");
    }

    @Override
    public void returnChange() {
        System.out.println("Please make a payment!");
    }

    private void checkPayment() {
        if (vendingMachine.getTotalPayment() >= vendingMachine.getItem(vendingMachine.getSelectedItem()).getPrice()) {
            vendingMachine.setState(vendingMachine.getDispenseState());
        }
    }
}
