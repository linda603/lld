package vendingmachine;

public class VendingMachine {
    private static VendingMachine instance;
    private Inventory inventory;
    private VendingMachineState idleState;
    private VendingMachineState readyState;
    private VendingMachineState dispenseState;
    private VendingMachineState returnChangeState;
    private VendingMachineState currState;
    private String selectedItem;
    private double totalPayment;

    private VendingMachine() {
        this.inventory = new Inventory();
        this.idleState = new IdleState(this);
        this.readyState = new ReadyState(this);
        this.dispenseState = new DispenseState(this);
        this.returnChangeState = new ReturnChangeState(this);
        this.currState = this.idleState;
        this.selectedItem = null;
        this.totalPayment = 0.0;
    }

    public static synchronized VendingMachine getInstance() {
        if (instance == null) {
            instance = new VendingMachine();
        }
        return instance;
    }

    public void selectItem(String itemId) {
        currState.selectItem(itemId);
    }

    public void insertNote(Note note) {
        currState.insertNote(note);
    }

    public void insertCoin(Coin coin) {
        currState.insertCoin(coin);
    }

    public void dispenseItem() {
        currState.dispenseItem();
    }

    public void returnChange() {
        currState.returnChange();
    }

    public void setState(VendingMachineState state) {
        currState = state;
    }

    public VendingMachineState getIdleState() {
        return idleState;
    }

    public VendingMachineState getReadyState() {
        return readyState;
    }

    public VendingMachineState getDispenseState() {
        return dispenseState;
    }

    public VendingMachineState getReturnChangeState() {
        return returnChangeState;
    }

    public String getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(String itemId) {
        selectedItem = itemId;
    }

    public void resetSelectedItem() {
        selectedItem = null;
    }

    public double getTotalPayment() {
        return totalPayment;
    }

    public void resetPayment() {
        totalPayment = 0.0;
    }

    public boolean isAvailable(String itemId) {
        return inventory.isAvailable(itemId);
    }

    public Item getItem(String itemId) {
        return inventory.getItem(itemId);
    }

    public void addNote(Note note) {
        totalPayment += note.getValue();
    }

    public void addCoin(Coin coin) {
        totalPayment += coin.getValue();
    }

    public void updateQuantity(String itemId, int quantity) {
        inventory.updateItemQuantity(itemId, quantity);
    }

    public void addItem(Item item, int quantity) {
        inventory.addItem(item, quantity);
    }
}
