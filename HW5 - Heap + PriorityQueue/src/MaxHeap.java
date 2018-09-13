import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Your implementation of a max heap.
 *
 * @author Tahirah Ahmad
 * @userid tahmad8
 * @GTID 903175115
 * @version 1.0
 */
public class MaxHeap<T extends Comparable<? super T>>
    implements HeapInterface<T> {

    private T[] backingArray;
    private int size;
    // Do not add any more instance variables. Do not change the declaration
    // of the instance variables above.

    /**
     * Creates a Heap with an initial capacity of {@code INITIAL_CAPACITY}
     * for the backing array.
     *
     * Use the constant field in the interface. Do not use magic numbers!
     */
    public MaxHeap() {
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Creates a properly ordered heap from a set of initial values.
     *
     * You must use the Build Heap algorithm that was taught in lecture! Simply
     * adding the data one by one using the add method will not get any credit.
     *
     * The initial array before the Build Heap algorithm takes place should
     * contain the data in the same order as it appears in the ArrayList.
     *
     * The {@code backingArray} should have capacity 2n + 1 where n is the
     * number of data in the passed in ArrayList (not INITIAL_CAPACITY from
     * the interface). Index 0 should remain empty, indices 1 to n should
     * contain the data in proper order, and the rest of the indices should
     * be empty.
     *
     * @param data a list of data to initialize the heap with
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public MaxHeap(ArrayList<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("Error: data cannot be null");
        }

        backingArray = (T[]) new Comparable[(2 * data.size()) + 1];

        for (int i = 0; i < data.size(); i++) {
            if ((data.get(i)) == null) {
                throw new IllegalArgumentException("Error:"
                        + " data cannot be null");
            }
            //bc heap array starts at 1 for easier calc of parents + children
            backingArray[i + 1] = (data.get(i));
        }

        size = data.size();

        for (int i = size / 2; i > 0; i--) {
            downHeap(i);
        }

    }

    @Override
    public void add(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Error: data cannot be null");
        } else {
            if (size >= backingArray.length - 1) {
                backingArray = resize();
            }
        }
        size++;
        backingArray[size] = item;
        heapifyAdd(size);
    }

    /**
    * A method to help resize
    * @return the resized array
    *
     */
    private T[] resize() {

        T[] tempArr = (T[]) new Comparable[backingArray.length * 2];

        for (int i = 1; i < backingArray.length; i++) {
            tempArr[i] = backingArray[i];
        }
        return tempArr;
    }
    /**
    * this method reinforces the order property after adding
    * @param i is the node to heapify from
    *
     */
    private void heapifyAdd(int i) {

        while (i > 1 && ((backingArray[i].compareTo(backingArray[i / 2]))
                > 0)) {
            T par = backingArray[i/2];
            backingArray[i/2] = backingArray[i];
            backingArray[i] = par;
            i = i/2;
        }
    }
    /**
    * A method to help swap for the heapify methods
    * @param i node to swap
    * @param parent node to swap
    *
     */
    private void swap(int i, int parent) {
        T temp = backingArray[i];
        backingArray[i] = backingArray[parent];
        backingArray[parent] = temp;
    }

    @Override
    public T remove() {
        if (size == 0) {
            throw new NoSuchElementException("Heap empty! Cannot dequeue.");
        }
        T retObj = backingArray[1];
        backingArray[1] = backingArray[size];
        backingArray[size] = null;
        size--;
        downHeap(1);
        return retObj;
    }
    /**
    * this method reinforces the order property after removing
    * @param i is the node to heapify from
    *
     */
    private void downHeap(int i) {
        //i represents the parent here

        //while a left child exists (i.e., you aren't at the end of the array)
        while ((i * 2) <= size) {
            //index for right child
            int rightExist = (i * 2) + 1;
            //index for left child
            int leftExist = i * 2;
            //index for left child as placeholder. this variable bigBoi is meant
            //to represent the largest child.
            int bigBoi = leftExist;
            //if a right child exists, check if it's bigger than the parent
            //if so, then bigBoi will change to equal the right child
            //otherwise it'll remain the left child
            if (rightExist <= size) {
                //compare the right child to the current node (parent)
                int compo =
                        backingArray[rightExist].compareTo(
                                backingArray[bigBoi]);
                //if the right child is bigger than bigBoi (Repping the largest
                        // child) will hold the right childs index
                        //otherwise, stays the left child
                if (compo > 0) {
                    bigBoi = rightExist;
                }
            }
            //compare the parent to the "bigBoi", which is the right child if
            //the right child was bigger
            //if it was not, then bigBoi equals the left child
            //now compare the child with the parent to double check
            //if its really bigger than the parent
            int comp = backingArray[bigBoi].compareTo(backingArray[i]);
            if (comp > 0) {
                //if bigBOi is indeed bigger than parent, swap em
                swap(bigBoi, i);
            }
            //we are moving down the heap, so i equals the location just swapped
            //from. while loops continues so long as it's not OOB`
            i = bigBoi;
        }

    }

    @Override
    public boolean isEmpty() {
        // DO NOT MODIFY THIS METHOD!
        return size == 0;
    }

    @Override
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    @Override
    public void clear() {
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public Comparable[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }
}
