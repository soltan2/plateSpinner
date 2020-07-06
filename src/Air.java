import java.util.NoSuchElementException;

/**
 * Implementation of Queue by the Air list.
 */
public class Air implements Queue<Plate> {
    /**
     * Maximum allowed items in the air.
     */
    public static final int MAX_CAPACITY = 13;
    /**
     * internal storage.
     */
    public AttachedList<Plate> internalList;

    /**
     * Constructor to initialize the internalList.
     */
    public Air() {

        internalList = new AttachedList<>();
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
     * String representation of air list.
     * 
     * @return the string value of plates in the air.
     */
    public String toString() {

        String returnString = "";
        for (Plate p : internalList) {
            returnString = p + returnString;
        }
        return returnString;
    }

    /**
     * enqueues a plate in the air if air capacity allows.
     * 
     * @param value
     *            plate to enqueue.
     * @return true after enqueue,false otherwise.
     */
    @Override
    public boolean enqueue(Plate value) {
        if (internalList.size() < MAX_CAPACITY) {
            internalList.add(value);
            return true;
        } else
            return false;
    }

    /**
     * dequeues a plate from the air.
     * 
     * @return the element(plate) dequeued.
     * @throws NoSuchElementException if element does not exist.
     */
    @Override
    public Plate dequeue() {
        if (internalList.isEmpty())
            throw new NoSuchElementException();
        return internalList.remove(0);
    }

    /**
     * finds the size of the queue.
     * 
     * @return the number of elements.
     */
    @Override
    public int size() {

        return internalList.size();
    }

    /**
     * finds whether the queue has any elements.
     * 
     * @return true if list is empty(no plate in the air), false otherwise.
     */
    @Override
    public boolean isEmpty() {

        return internalList.isEmpty();
    }

    /**
     * removes elements of the lists. 
     */
    @Override
    public void clear() {
        internalList.clear();

    }
}
