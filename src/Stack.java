// --------------------------------------------------------
// DO NOT EDIT ANYTHING BELOW THIS LINE (except to add JavaDocs)
// --------------------------------------------------------
/**
 * Stack interface abstract methods.
 *
 * @param <T>
 *            generic type.
 */
interface Stack<T> {

    /**
     * adds an element to the stack.
     * 
     * @param value
     *            to be pushed on the stack.
     * @return true if the value was pushed
     */
    public boolean push(T value);

    /**
     * pops an element from the stack.
     * 
     * @return the element popped from the stack throw NoSuchElementException if
     *         nothing to pop.
     */
    public T pop();

    /**
     * check how many elements in the stack.
     * 
     * @return number of elements in the stack.
     */
    public int size();

    /**
     * checks if there is any element in the stack.
     * 
     * @return true if there is no element in the stack, false otherwise.
     */
    public boolean isEmpty();

    /**
     * clears the stack.
     */
    public void clear();
}