import java.util.List;

public class SnackDispenseHandler {

    private SnackDispenser first;

    public SnackDispenseHandler(List<Snack> snacks) {
        first = new SnackDispenser();
        if (snacks.isEmpty()) {
            return;
        }
        first.snack = snacks.get(0);
        first.quantity = first.snack.quantity();
        SnackDispenser current = first;
        for (int i = 1; i < snacks.size(); i++) {
            SnackDispenser temp = new SnackDispenser();
            temp.snack = snacks.get(i);
            temp.quantity = temp.snack.quantity();
            current.next = temp;
            current = current.next;
        }
    }

    public Snack dispense(String selection) {
        SnackDispenser current = first;
        while (current.hasNext()) {
            if (current.snack.name().equals(selection)) {
                if (current.quantity > 0) {
                    current.quantity--;
                    return current.snack;
                } else {
                    return null;
                }

            }
            current = current.next;
        }
        return null;
    }


    private static class SnackDispenser {
        public Snack snack;
        public int quantity;
        public SnackDispenser next;

        public SnackDispenser() {
        }

        public boolean hasNext() {
            return next != null;
        }

    }
}
