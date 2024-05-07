public class VendingMachineDriver {

    public static void main(String[] args) {
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.selectSnack("Coke");
        vendingMachine.insertMoney(2);
        vendingMachine.selectSnack("Pepsi");
        vendingMachine.insertMoney(2.1);
        vendingMachine.selectSnack("Cheetos");
        vendingMachine.insertMoney(3);
        vendingMachine.selectSnack("Doritos");
        vendingMachine.insertMoney(10);
        vendingMachine.selectSnack("KitKat");
        vendingMachine.insertMoney(.5);
        vendingMachine.selectSnack("Snickers");
        vendingMachine.insertMoney(5);
    }
}
