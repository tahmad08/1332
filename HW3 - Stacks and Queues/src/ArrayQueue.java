import java.util.NoSuchElementException;
/**
 * Your implementation of an array-backed queue.
 *
 * @author Tahirah Ahmad
 * @userid 903175115
 * @GTID tahmad8
 * @version 1.0
 */
public class ArrayQueue<T> implements QueueInterface<T> {

    // Do not add new instance variables.
    private T[] backingArray;
    private int front;
    private int size;

    /**
     * Constructs a new ArrayQueue.
     */
    public ArrayQueue() {
        size = 0;
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
        front = 0;
    }

    /**
     * Dequeue from the front of the queue.
     *
     * Do not shrink the backing array.
     * If the queue becomes empty as a result of this call, you should
     * explicitly reset front to 0.
     *
     * You should replace any spots that you dequeue from with null. Failure to
     * do so can result in a loss of points.
     *
     * See the homework pdf for more information on implementation details.
     *
     * @see QueueInterface#dequeue()
     */
    @Override
    public T dequeue() {
        if (size == 0) {
            throw new NoSuchElementException("Nothing to return, queue empty");
        }
        int fronHolder = front;
        T retObj = backingArray[fronHolder];
        front = (front + 1) % backingArray.length;
        backingArray[fronHolder] = null;
        size--;
        return retObj;
    }

    /**
     * Add the given data to the queue.
     *
     * If sufficient space is not available in the backing array, you should
     * regrow it to double the current length. If a regrow is necessary,
     * you should copy elements to the front of the new array and reset
     * front to 0.
     *
     * @see QueueInterface#enqueue(T)
     */
    @Override
    public void enqueue(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot add null to queue");
        }

        if (size >= backingArray.length) {
            int leng = backingArray.length;
            T[] copyArr = (T[]) new Object[leng * 2];
            for (int j = 0; j < size; j++) {
                copyArr[j] = backingArray[front];
                front = (front + 1) % leng;
            }
            backingArray = copyArr;
            front = 0;
        }
        int r = (front + size) % (backingArray.length);
        backingArray[r] = data;
        size++;
    }

    @Override
    public T peek() {
        T returnObj = backingArray[front];
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
     * Returns the backing array of this queue.
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


}