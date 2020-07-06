/**
 * Spinner Class to execute Syeda's movements.
 */
public class Spinner {
    /**
     *The Hand of the Spinner.
     */
    private static class Hand {
        /**
         * signifies plate in hand. Initialized to null since hand is empty.
         */
        private Plate plate = null;

        /**
         * catches a plate.
         * @param plate caught by the hand.
         * @throws RuntimeException if there is no plate to catch. 
         */
        public void catchPlate(Plate plate) {
            if (hasPlate())
                throw new RuntimeException("Catching hand not empty");
            if (plate == null)
                throw new RuntimeException("Can't catch a plate that doesn't exist");
            this.plate = plate;
        }

        /**
         * tosses the plate.
         * @return plate removed from the hand.
         * @throws RuntimeException if this hand was empty.
         */
        public Plate tossPlate() {
            Plate temp;
            if (!hasPlate())
                throw new RuntimeException("Tossing hand was empty");
            else {
                temp = plate;
                this.plate = null;
                return temp;
            }
        }

        /**
         * checks if a plate is in hand.
         * @return true if a plate in hand, false otherwise
         */
        public boolean hasPlate() {

            return (plate != null);
        }

        /**
         * String representation of the plate value in hand. print spaces if hand is
         * empty.
         * @return " "if this hand is empty, otherwise return the plate's string value.
         */
        public String toString() {

            if (hasPlate())
                return plate.toString();
            else
                return "   ";
        }
    }

    /**
     * put a plate from hand 2 and pass it to hand 1.
     */
    public void passPlate() {

        hands[0].catchPlate(hands[1].tossPlate());

    }

    /**
     * removes a plate from hand 1 and pushes it into the bin stack.
     */
    public void putDownPlate() {

        bin.push(hands[0].tossPlate());
    }

    /**
     * pops the bin stack to get a plate and catch it by hand 1. 
     * @throws RuntimeException if there is no plate to pick up.
     */
    public void pickUpPlate() {

        if (bin.isEmpty())
            throw new RuntimeException("Can't pick up a plate that does not exist!");
        else {
            hands[0].catchPlate(bin.pop());
        }
    }

    /**
     * tosses the plate by hand 1, which get enqueued by the air. If the air can't
     * hold anymore plates, it throw a RuntimeException
     * @throws RuntimeException if the max number of plates in the air is reached. 
     */
    public void spinPlate() {

        if (!air.enqueue(hands[0].tossPlate()))
            throw new RuntimeException("Too many plates in the air!");

    }

    /**
     * dequeue a plate from the air which get caught by the hand. if there are no
     * plates in the air, throw a RuntimeException
     */
    public void catchPlate() {

        if (air.isEmpty())
            throw new RuntimeException("Can't catch a plate that doesn't exist!");
        hands[1].catchPlate(air.dequeue());
    }

    /**
     * Testing class.
     * 
     * @param args
     *            main class for testing purposes.
     */
    public static void main(String[] args) {

    }

    /**
     * Air list which implement the queue of plates.
     */
    private final Air air = new Air();
    /**
     * Bin list which implement the stack of plates.
     */
    private final Bin bin = new Bin();
    /**
     * Array of hands of size 2 for Syeda's left and right hands.
     */
    private final Hand[] hands = new Hand[2];

    /**
     * spinners have two hands and starts with a bin full of plates.
     * 
     * @param totalPlates
     *            to initialize a full bin.
     */
    public Spinner(int totalPlates) {
        hands[0] = new Hand();
        hands[1] = new Hand();

        for (int i = totalPlates; i > 0; i--) {
            this.bin.push(new Plate(i));
        }
    }

    /**
     * String representation of person. this does the beautiful ascii art.
     * 
     * @return string person and plate values in her hands.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();

        String[] personParts = { "   " + air.toString() + "\n", "\n",
            "    " + hands[0].toString() + "( )" + hands[1].toString() + "\n", "     \\__|__/\n", "        |\n",
            "        |\n", "       / \\\n", "      /   \\\n" };

        String[] stackParts = this.bin.toString().split("[|]");

        int total = (this.bin.size() < personParts.length) ? personParts.length : this.bin.size();
        for (int i = total; i >= 0; i--) {
            sb.append((this.bin.size() - 1 < i) ? "   " : stackParts[stackParts.length - i - 1]);
            if (i < personParts.length) {
                sb.append(personParts[personParts.length - i - 1]);
            } else {
                sb.append("\n");
            }
        }

        return sb.toString();
    }
}
