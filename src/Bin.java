import java.util.NoSuchElementException;

/**
 * Implementation of stack by the Bin class.
 */
public class Bin implements Stack<Plate> {
    /**
     * this is the internal storage.
     */
    public AttachedList<Plate> internalList;

    /**
     * Constructor to initialize the internalList.
     */
    public Bin() {
        internalList = new AttachedList<>();
    }

    /**
     * Testing class.
     * @param args main class for testing purposes.
     */
    public static void main(String[] args) {

    }

    /**
     * String representation of plates in the bin.
     * @return string values of plates in the bin.
     */
    public String toString() {
        String returnString = "";
        boolean first = true;
        for (Plate p : internalList) {
            if (first) {
                returnString = returnString + p;
                first = false;
            } else {
                returnString = returnString + "|" + p;
            }
        }
        return returnString;
    }

    /**
     * pushes an element into the stack.
     * @param value to push into the stuck
     * @return true after the value is added.
     */
    @Override
    public boolean push(Plate value) {
        internalList.add(0, value);

        return true;
    }

    /**
     * pops an element out of the stack.
     * @return the element popped or null if stack is empty.
     */
    @Override
    public Plate pop() {
        if (internalList.isEmpty())
            throw new NoSuchElementException();
        else
            return internalList.remove(0);

    }

    /**
     * returns how many element stored in the list.
     * @return the number of elements in the list.
     */
    @Override
    public int size() {
        return internalList.size();
    }

    /**
     * returns whether there is any element in the list.
     * @return true if there is no element in the list, false otherwise
     */
    @Override
    public boolean isEmpty() {

        return internalList.isEmpty();
    }

    /**
     * clears the list.
     */
    @Override
    public void clear() {
        internalList.clear();

    }
}
