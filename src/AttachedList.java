import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Linked list implementation of list interface.
 * @param <T>  Generic Data Type.
 */
public class AttachedList<T> implements List<T> {
    // for more information on these methods
    // read the documentation of the list interface
    // here: https://docs.oracle.com/javase/8/docs/api/java/util/List.html
    // keep in mind that we are doing a _linked_ list
    // but the documentation is for general lists (like array lists)
    // NOTE: the documentation above is not optional, it tells you things
    // like what exceptions to throw

    /**
     * Node class.
     * @param <T> genetic type.
     */
    private static class Node<T> {
        /**
         * value of the node.
         */
        T value;
        /**
         * pointer to next node.
         */
        Node<T> next;

        /**
         * Constructor of the Node.
         * @param value held by the node.
         */
        Node(T value) {
            this.value = value;
        }
    }

    /**
     * the first node of the linked-list.
     */
    private Node<T> head = null;
    /**
     * the node currently pointed at.
     */
    private Node<T> current = head;
    /**
     * Number of elements in the linked list.
     */
    private int numElements;

    /**
     * Default constructor to initialize the number of elements to 0.
     */
    public AttachedList() {
        numElements = 0;
    }

    /**
     * checks the size of the list.
     * @return the number of elements.
     */
    public int size() {
        return numElements;
    }

    /**
     * checks if the list has no element.
     * @return true if the number of elements is 0, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return (numElements == 0);
    }

    /**
     * finds the position of the element in the list if it exist, throw exception
     * otherwise.
     * @param o element to search for.
     * @return index at which element o is located.        
     * @throws NoSuchElementException if element does not exist. 
     */
    @Override
    public int indexOf(Object o) {
        
        int index = 0;
        for (Node<T> current = head; current.next != null; current = current.next) {
            if (current.value == o)
                return index;
            else
                index++;
        }
        throw new NoSuchElementException();
    }

    /**
     * checks if a list contains a given element.
     * @param o element to search for.
     * @return true if the list contains o , false otherwise.
     */
    @Override
    public boolean contains(Object o) {
        
        for (Node<T> current = head; current.next != null; current = current.next) {
            if (current.value == o)
                return true;
        }
        return false;
    }

    /**
     * appends at the end of the list.
     * @param e element to be appended.
     * @return true once the element is added.
     */

    @Override
    public boolean add(T e) {
        Node<T> temp = new Node<>(e);
        if (head == null) {
            head = temp;
            current = head;
        } else {
            current.next = temp;
            current = current.next;
        }
        numElements++;

        return true;

    }

    /**
     * Tests for valid index and adds element at the given index .
     * @param index  position where to add the element.
     * @param element  to add.
     * @throws IndexOutOfBoundsException for invalid indexes.            
     */
    @Override
    public void add(int index, T element) {
        if (index < 0 || index > numElements) {
            throw new IndexOutOfBoundsException();
        } // checked for invalid index

        if (index == 0) {
            Node<T> temp = new Node<>(element);
            temp.next = head;
            head = temp;
            numElements++;
        }else if (index == numElements) {
            add(element);
        }
        else {
            Node<T> previous = null;
            Node<T> current = head;
            for (int i = 0; i < index; i++) {
                previous = current;
                current = current.next;
            }
            Node<T> temp = new Node<>(element);
            temp.next = current;
            previous.next = temp;
            numElements++;
        }

    }

    /**
     * checks for valid index and removes the element at a given index.
     * @param index where to locate the element to remove.
     * @throws IndexOutOfBoundsException for invalid indexes.            
     * @return the element removed.
     */
    @Override
    public T remove(int index) {
        
        if (index < 0 || index >= numElements) {// checking for invalid index
            throw new IndexOutOfBoundsException();
        }
        Node<T> previous = null;
        Node<T> current = head;

        for (int i = 0; i < index; i++) {
            previous = current;
            current = current.next;
        }
        T data = current.value;
        if (current == head) {
            head = head.next;
        } else {
            previous.next = current.next;
        }
        numElements--;

        return data;
    }

    /**
     * removes an element from the list.
     * @param o element to be removed.
     * @return true if the element was removed, false otherwise.
     */
    @Override
    public boolean remove(Object o) {

        Node<T> current = head;
        Node<T> previous = null;

        while (current != null) {

            if (current.value.equals(o)) {
                if (current == head) {
                    head = head.next;
                } else {
                    previous.next = current.next;
                }
                numElements--;
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;
    }

    /**
     * clears the list by setting up the head to null and reset number of elements
     * to 0.
     */
    @Override
    public void clear() {
        // O(1)
        head = null;
        numElements = 0;
    }

    /**
     * traverse the list until the specified index.
     * @param index of the element.
     * @throws IndexOutOfBoundsException for invalid index. 
     * @return element at the given index.
     */
    @Override
    public T get(int index) {
        // O(n)
        if (index < 0 || index >= numElements) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current.value;
    }

    /**
     * replaces the element at a given index by a another given element.
     * @param index  of the element.
     * @param element to set
     * @throws IndexOutOfBoundsException for invalid indexes. 
     * @return new element set at the given index.
     */
    @Override
    public T set(int index, T element) {
        // O(n)
        if (index < 0 || index >= numElements) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        current.value = element;
        return element;
    }

    /**
     * removes a "slice" from fromIndex to toIndex (inclusive) checks for valid
     * fromIndex and toIndex. throws IndexOutOfBoundsException if fromIndex or
     * toIndex are invalid.
     * @param fromIndex  the starting index.
     * @param toIndex the ending index.
     * @return the slice as a new AttachedList.
     * @throws IndexOutOfBoundsException for invalid indexes.
     * 
     */
    public AttachedList<T> slice(int fromIndex, int toIndex) {
    

        if (toIndex < fromIndex || fromIndex < 0 || fromIndex >= numElements || toIndex < 0 || toIndex >= numElements) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> current = head;
        AttachedList<T> newList = new AttachedList<>();

        for (int i = 0; i < fromIndex; i++) {
            current = current.next;
        }
        for (int i = fromIndex; i <= toIndex; i++) {
            newList.add(current.value);
            current = current.next;
        }
        return newList;
    }

    /**
     * make a reverse copy of a given list.
     * @return a copy of the list with the elements reversed. does not alter the original list.
     */
    public AttachedList<T> reverseCopy() {
        
        AttachedList<T> reverseCopy = new AttachedList<>();
        
        reverseOrder(reverseCopy,head);
    
    
        return reverseCopy;
    }
    /**
     * Recursively pushes elements into the stack and copies them in reversed order. 
     * Helper of reverseCopy method. 
     * @param head the first node 
     * @param newList new reversed list. 
     */
    private void reverseOrder(AttachedList<T> newList,Node<T> head) {
        
        if (head != null) { 
            reverseOrder(newList,head.next);
            newList.add(head.value);
        }
        else return;

     
    }
    /**
     * flatten a list of lists into one list.
     * @param <E> genetic type.
     * @param packedList (list of lists).
     * @return flatList the new 1D list.
     */
    public static <E> AttachedList<E> flatten(AttachedList<AttachedList<E>> packedList) {
        // given a 2D list of lists (packedList), "flatten" the list into 1D
        // Example 1: [[1,2,3],[4,5],[6]] becomes [1,2,3,4,5,6]
        // Example 2: [[null],[1,3],[5],[6]] becomes [null,1,3,5,6]
        // IMPORTANT: the above examples are _lists NOT arrays_
        AttachedList<E> flatList = new AttachedList<>();

        for (AttachedList<E> elements : packedList) {
            for (E value : elements) {
                flatList.add(value);
            }
        }

        return flatList;
    }

    /**
     * packs sequential items together in lists.
     * @param <E>  genetic type.
     * @param flatList 1D list.
     * @return packedList packed lists (list of lists).
     */
    public static <E> AttachedList<AttachedList<E>> pack(AttachedList<E> flatList) {
        // given a 1D (flatList), "pack" sequential items together
        // to form a 2D list of values

        // Example 1: [1,1,2,3,3] becomes [[1,1],[2],[3,3]]
        // Example 1: [1,1,2,1,1,2,2,2,2] becomes [[1,1],[2],[1,1],[2,2,2,2]]
        // Example 3: [1,2,3,4,5] becomes [[1],[2],[3],[4],[5]]
        // IMPORTANT: the above examples are _lists NOT arrays_

        // promise: we will never test this with nulls in the flatList
        // though there's no harm in coding it to work with nulls

        AttachedList<AttachedList<E>> packedList = new AttachedList<>();
        AttachedList<E> subList = new AttachedList<>();
        E lastValue = null;
        Iterator<E> iterator = flatList.iterator();

        while (iterator.hasNext()) {
            
            E value = iterator.next();
            if (lastValue == null) {
                subList.add(value);//adds to the same sub-list.
            }else if(lastValue.equals(value)) {
                subList.add(value);   
            }else {
                packedList.add(subList);
                subList = new AttachedList<>();// creates new sub-lists.
                subList.add(value); //adds the next different value to the new list. 
            }
            lastValue = value;
        }
        if (!subList.isEmpty()) {
            packedList.add(subList);
        }
        return packedList;
    }

    /**
     * Iterator to traverse elements in the list.
     * 
     * @return Iterator to use.
     */
    public Iterator<T> iterator() {

        return new Iterator<T>() {
            /**
             * starting from the head as the first node.
             */
            private Node<T> current = head;

            /**
             * checks if the next node exist.
             * 
             * @return true if is not null, false otherwise.
             */
            public boolean hasNext() {

                return current != null;
            }

            /**
             * Traverses the nodes.
             * 
             * @return data at the current node.
             */
            public T next() {

                T data = current.value;
                current = current.next;
                return data;
            }
        };
    }

    /**
     * String representation of lists.
     * @return a string value of elements inside the brackets separated by a comma.
     */
    public String toString() {

        String list = "[";
        Iterator<T> iterator = iterator();

        while (iterator.hasNext()) {

            list = list + iterator.next();
            if (iterator.hasNext()) {
                list = list + ",";
            }
        }
        list += "]";
        return list;

    }

    /**
     * main class for testing.
     * @param args  main class for testing.
     */

    public static void main(String[] args) {

        AttachedList<Integer> items = new AttachedList<>();
        AttachedList<Integer> flatList = new AttachedList<>();
        AttachedList<AttachedList<Integer>> packedList = new AttachedList<>();
        items.add(1);
        items.add(1);
        items.add(2);
        items.add(3);
        items.add(4);
        items.add(4);
        System.out.println("Original items " + items);
        packedList = pack(items);
        System.out.println("After packing " + packedList);
        flatList = flatten(packedList);
        System.out.println("After flatten " + flatList);
       
        AttachedList<Integer> reversed=flatList.reverseCopy();
        System.out.println("After reverse copy "+reversed);
        flatList.add(3, 5);
        System.out.println("After adding 5 at index 3 " + flatList);
        System.out.println("The list contains 5? " + flatList.contains(5));
        if (flatList.remove((Object) 5))
        System.out.println("after removing 5" + flatList);
        flatList.remove(3);
        System.out.println("After removing element at index 3 " + flatList);
        flatList = flatList.slice(1, 3);
        System.out.println("After slicing from index 1 to 3 " + flatList);
        System.out.println("using get method, the element at index 1 is " + flatList.get(1));
        flatList.set(1, 6);
        System.out.println("using set method, the element at index 1 is becomes 6 " + flatList);
        System.out.println("index of 6 is " + flatList.indexOf(6));

    }

    // --------------------------------------------------------
    // DO NOT EDIT ANYTHING BELOW THIS LINE (except to add JavaDocs)
    // --------------------------------------------------------

    /**
     * copies elements of the list into an array.
     * @return items the array created.
     */
    @Override
    public Object[] toArray() {
        Object[] items = new Object[this.size()];
        int i = 0;
        for (T val : this) {
            items[i++] = val;
        }
        return items;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        return (T[]) this.toArray();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public ListIterator<T> listIterator() {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Not supported.");
    }
}