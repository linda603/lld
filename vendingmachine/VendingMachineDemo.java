package vendingmachine;

public class VendingMachineDemo {
    public static void main(String[] args) {
        VendingMachine vendingMachine = VendingMachine.getInstance();

        Item water = new Item("I1", "Water", 1.0);
        Item coke = new Item("I2", "Coke", 1.5);
        Item orangeJuice = new Item("I3", "Orange Juice", 2.0);
    
        vendingMachine.addItem(water, 5);
        vendingMachine.addItem(coke, 3);
        vendingMachine.addItem(orangeJuice, 2);
    
        vendingMachine.selectItem("I1");
    
        vendingMachine.insertCoin(Coin.FIVE_CENT);
        vendingMachine.insertNote(Note.ONE);
    
        vendingMachine.dispenseItem();
        vendingMachine.returnChange();
    }
}
