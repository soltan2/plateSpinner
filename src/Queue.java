// --------------------------------------------------------
// DO NOT EDIT ANYTHING BELOW THIS LINE (except to add JavaDocs)
// --------------------------------------------------------
/**
 * Queue interface abstract methods.
 * @param <T>  generic type.
 */
interface Queue<T> {

    /**
     * adds an element to the queue.
     * @param value to get enqueued.
     * @return true after the element get enqueued.
     */
    public boolean enqueue(T value);

    /**
     * dequeues element from the list. throws NoSuchElementException if nothing to
     * dequeue.
     * @return element dequeued.
     */
    public T dequeue();

    /**
     * finds the size of the queue.
     * @return number of elements in the queue.
     */
    public int size();

    /**
     * checks for any element in the queue.
     * @return true if there is no element in the queue, false otherwise.
     */
    public boolean isEmpty();

    /**
     * clears the queue from all elements.
     */
    public void clear();
}