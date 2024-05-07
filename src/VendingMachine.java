import java.util.LinkedList;
import java.util.List;

public class VendingMachine {
    private StateOfVendingMachine state;
    private SnackDispenseHandler dispenser;
    private String selection;
    private List<Snack> snacks;

    public VendingMachine() {
        state = StateOfVendingMachine.IDLE;
        selection = "";
        // snacks should probably be populated by user creating the vending machine object, who would
        // most likely be an admin of some kind.  This method is used only for the purpose of this assignment
        populateSnacks();
        dispenser = new SnackDispenseHandler(snacks);
    }

    public void selectSnack(String selection) {
        if (!state.equals(StateOfVendingMachine.IDLE)) {
            return;
        }
        this.selection = selection;
        state = StateOfVendingMachine.WAITING_FOR_MONEY;
    }

    public void insertMoney(double money) {
        if (!state.equals(StateOfVendingMachine.WAITING_FOR_MONEY)) {
            return;
        }
        Snack snack = null;
        for (Snack s : snacks) {
            if (s.name().equals(selection)) {
                snack = s;
                break;
            }
        }
        assert snack != null;
        if (money >= snack.price()) {
            double change = money - snack.price();
            state = StateOfVendingMachine.RETURNING_CHANGE;
            returnChange(change);
            state = StateOfVendingMachine.DISPENSING;
            dispense(selection, money);
        } else {
            state = StateOfVendingMachine.RETURNING_MONEY;
            returnMoney(money);
        }
    }

    private Snack dispense(String selection, double money) {
        if (!state.equals(StateOfVendingMachine.DISPENSING)) {
            return null;
        }
        Snack snack = dispenser.dispense(selection);
        if (snack == null) {
            state = StateOfVendingMachine.RETURNING_MONEY;
            returnMoney(money);
        }
        state = StateOfVendingMachine.IDLE;
        return snack;
    }


    public double returnChange(double change) {
        if (state.equals(StateOfVendingMachine.RETURNING_CHANGE)) {
            return change;
        }
        return -1;

    }

    public double returnMoney(double money) {
        if (state.equals(StateOfVendingMachine.RETURNING_MONEY)) {
            return money;
        }
        return -1;
    }

    private void populateSnacks() {
        snacks = new LinkedList<>();
        snacks.add(new Snack("Coke", 2, 3));
        snacks.add(new Snack("Pepsi", 2.10, 3));
        snacks.add(new Snack("Cheetos", 3, 2));
        snacks.add(new Snack("Doritos", 3.50, 2));
        snacks.add(new Snack("KitKat", 1.25, 1));
        snacks.add(new Snack("Snickers", 5, 0));
    }
}
