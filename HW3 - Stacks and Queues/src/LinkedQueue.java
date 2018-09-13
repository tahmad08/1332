import java.util.NoSuchElementException;
/**
 * Your implementation of a linked queue. It should NOT be circular.
 *
 * @author Tahirah Ahmad
 * @userid 903175115
 * @GTID tahmad8
 * @version 1.0
 */
public class LinkedQueue<T> implements QueueInterface<T> {

    // Do not add new instance variables.
    private LinkedNode<T> head;
    private LinkedNode<T> tail;
    private int size;

    @Override
    public T dequeue() {
        if (size == 0) {
            throw new NoSuchElementException("Error: queue empty");
        }
        T retObj = head.getData();
        if (head.getNext() == null) {
            head = null;
            tail = null;
        } else {
            LinkedNode<T> newHead = head.getNext();
            head = newHead;
        }
        size--;
        return retObj;
    }

    @Override
    public void enqueue(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Error: cannot input null");
        }
        if (size == 0) {
            LinkedNode<T> newNode = new LinkedNode(data);
            head = newNode;
            tail = newNode;
        } else {
            LinkedNode<T> oldNode = tail;
            LinkedNode<T> newNode = new LinkedNode(data);
            oldNode.setNext(newNode);
            tail = newNode;
        }
        size++;
    }

    @Override
    public T peek() {
        if (size == 0) {
            return null;
        }
        return (head.getData());
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
     * Returns the head of this queue.
     * Normally, you would not do this, but we need it for grading your work.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the head node
     */
    public LinkedNode<T> getHead() {
        // DO NOT MODIFY THIS METHOD!
        return head;
    }

    /**
     * Returns the tail of this queue.
     * Normally, you would not do this, but we need it for grading your work.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the tail node
     */
    public LinkedNode<T> getTail() {
        // DO NOT MODIFY THIS METHOD!
        return tail;
    }

}