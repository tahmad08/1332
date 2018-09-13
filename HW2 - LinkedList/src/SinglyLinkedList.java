/**
 * Your implementation of a circular singly linked list.
 *
 * @author Tahirah Ahmad
 * @userid tahmad8
 * @GTID 903175115
 * @version 1.0
 */
public class SinglyLinkedList<T> implements LinkedListInterface<T> {
    // Do not add new instance variables or modify existing ones.
    private LinkedListNode<T> head;
    private int size;

    @Override
    public void addAtIndex(T data, int index) {
        if (index > size) {
            String error = String.format("Error: index: %d is larger than"
                    + " list size (%d).", index, size);
            throw new IndexOutOfBoundsException(error);
        } else if (index < 0) {
            throw new IndexOutOfBoundsException("Error:"
                    + "index is negative.");
        } else if (data == null) {
            throw new IllegalArgumentException("Error: data input cannot"
                    + " be null.");
        }

        if (index == 0) {
            addToFront(data);
        } else if (index == size && index != 0) {
            addToBack(data);
        } else if (index < size && index > 0) {
            LinkedListNode<T> curr = head;
            for (int i = 0; i < index - 1; i++) {
                curr = curr.getNext();
            }
            LinkedListNode<T> newNode = new LinkedListNode<T>(data,
                curr.getNext());
            curr.setNext(newNode);
            size++;
        }
    }

    @Override
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Error: data input"
                    + " cannot be null.");
        }
        if (size == 0) {
            LinkedListNode<T> newNode = new LinkedListNode(data);
            head = newNode;
            newNode.setNext(head);
        } else {
            LinkedListNode<T> newNode = new LinkedListNode(head.getData(),
                head.getNext());
            head.setData(data);
            head.setNext(newNode);
        }
        size++;

    }

    @Override
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Error: data input"
                    + " cannot be null.");
        }
        if (size == 0) {
            addToFront(data);
        } else {
            LinkedListNode<T> curr = new LinkedListNode(head.getData(),
                    head.getNext());
            head.setData(data);
            head.setNext(curr);
            head = curr;
            size++;
        }
    }

    @Override
    public T removeAtIndex(int index) {
        if (index >= size) {
            String error = String.format("Error: index: %d is larger than"
                    + " list size (%d).", index, size);
            throw new IndexOutOfBoundsException(error);
        } else if (index < 0) {
            throw new IndexOutOfBoundsException("Error: index is negative.");
        }
        if (index == 0) {
            return removeFromFront();
        }
        if (index == size - 1) {
            return removeFromBack();
        } else {
            LinkedListNode<T> prev = null;
            LinkedListNode<T> header = head;
            for (int i = 0; i < index; i++) {
                prev = header;
                header = header.getNext();
            }
            if (prev != null) {
                LinkedListNode<T> headNext = header.getNext();
                prev.setNext(headNext);
            }
            size--;
            return header.getData();
        }
    }

    @Override
    public T removeFromFront() {
        if (head == null || size == 0) {
            return null;
        }
        if (size == 1) {
            T retobj = head.getData();
            head = null;
            size--;
            return retobj;
        }
        T retData = head.getData();
        LinkedListNode<T> headNext = head.getNext();
        LinkedListNode<T> thirdNext = headNext.getNext();
        head.setData(headNext.getData());
        head.setNext(thirdNext);
        size--;
        return retData;
    }

    @Override
    public T removeFromBack() {
        if (head == null || size == 0) {
            return null;
        }
        if (size == 1) {
            T retobj = head.getData();
            head = null;
            size--;
            return retobj;
        }
        LinkedListNode<T> curr = head;
        for (int i = 0; i < size - 2; i++) {
            curr = curr.getNext();
        }
        T retObj = (curr.getNext()).getData();
        curr.setNext(head);
        size--;
        return retObj;
    }

    @Override
    public T removeLastOccurrence(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Error: data input cannot"
                    + " be null.");
        }
        if (size == 0) {
            return null;
        }
        LinkedListNode<T> curr = head;
        LinkedListNode<T> lastOcc = null;
        // LinkedListNode<T> prev = null;
        T retObj = null;
        int i = 0;
        int loc = 0;
        while (curr != null && i < size) {
            if ((curr.getData()).equals(data)) {
                lastOcc = curr;
                retObj = curr.getData();
                loc = i;
            }
            i++;
            curr = curr.getNext();
        }
        removeAtIndex(loc);
        return retObj;
    }

    @Override
    public T get(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("Error: index can't be larger"
                    + " than the list size. ");
        } else if (index < 0) {
            throw new IndexOutOfBoundsException("Error: index is negative.");
        }
        if (index == 0) {
            return head.getData();
        } else {
            LinkedListNode<T> curr = head;
            for (int i = 0; i < index; i++) {
                curr = curr.getNext();
            }
            return curr.getData();
        }
    }

    @Override
    public Object[] toArray() {
        LinkedListNode<T> curr = head;
        Object[] arrayB = new Object[size];
        for (int i = 0; i < size; i++) {
            arrayB[i] = curr.getData();
            curr = curr.getNext();
        }
        return arrayB;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public void clear() {
        size = 0;
        head = null;
    }

    @Override
    public int size() {
        // DO NOT MODIFY!
        return size;
    }

    @Override
    public LinkedListNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }
}