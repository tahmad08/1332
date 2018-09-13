import java.util.NoSuchElementException;

/**
 * Your implementation of an array-backed stack.
 *
 * @author Tahirah Ahmad
 * @userid 903175115
 * @GTID tahmad8
 * @version 1.0
 */
public class ArrayStack<T> implements StackInterface<T> {

    // Do not add new instance variables.
    private T[] backingArray;
    private int size;

    /**
     * Constructs a new ArrayStack.
     */
    public ArrayStack() {
        size = 0;
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
    }

    /**
     * Pop from the stack.
     *
     * Do not shrink the backing array.
     *
     * You should replace any spots that you pop from with null. Failure to do
     * so can result in a loss of points.
     *
     * @see StackInterface#pop()
     */
    @Override
    public T pop() {
        if (size == 0) {
            throw new NoSuchElementException("Stack is empty, "
            + "nothing to return");
        }
        T retObj = backingArray[size - 1];
        backingArray[size - 1] = null;
        size--;
        return retObj;
    }

    /**
     * Push the given data onto the stack.
     *
     * If sufficient space is not available in the backing array, you should
     * regrow it to double the current length.
     *
     * @see StackInterface#push(T)
     */
    @Override
    public void push(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot add null to stack");
        }
        if (size >= backingArray.length) {
            int leng = backingArray.length;
            T[] copyArr = (T[]) new Object[leng * 2];
            for (int j = 0; j < backingArray.length; j++) {
                copyArr[j] = backingArray[j];
            }
            backingArray = copyArr;
        }
        backingArray[size] = data;
        size++;
    }

    @Override
    public T peek() {
        if (size == 0) {
            return null;
        }
        T returnObj = backingArray[size - 1];
        return returnObj;
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
    /**
     * Returns the backing array of this stack.
     * Normally, you would not do this, but we need it for grading your work.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the backing array
     */
    public Object[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

    public static void main(String args[]) {
        ArrayStack<Integer> arr = new ArrayStack<>();
        arr.push(1);
        System.out.println(arr.size());

    }
}