import java.util.NoSuchElementException;
/**
 * Your implementation of a linked stack. It should NOT be circular.
 *
 * @author Tahirah Ahmad
 * @userid 903175115
 * @GTID tahmad8
 * @version 1.0
 */
public class LinkedStack<T> implements StackInterface<T> {

    // Do not add new instance variables.
    private LinkedNode<T> head;
    private int size;

    @Override
    public T pop() {
        if (size == 0) {
            throw new NoSuchElementException("Error, stack is empty.");
        }

        T retData = head.getData();
        if (head.getNext() == null) {
            head = null;
        } else {
            LinkedNode<T> currHead = head.getNext();
            head = currHead;
        }
        size--;
        return retData;

    }

    @Override
    public void push(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Error: data input"
                    + " cannot be null.");
        }
        if (size == 0) {
            LinkedNode<T> newNode = new LinkedNode(data);
            head = newNode;
        } else {
            LinkedNode<T> newNode = new LinkedNode(data, head);
            LinkedNode<T> curr = head;
            head = newNode;
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
     * Returns the head of this stack.
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

}