/**
 * Your implementation of an ArrayList.
 *
 * @author Tahirah Ahmad
 * @userid tahmad8
 * @GTID 90375115
 * @version 1
 */
public class ArrayList<T> implements ArrayListInterface<T> {

    // Do not add new instance variables.
    private T[] backingArray;
    private int size;

    /**
     * Constructs a new ArrayList.
     *
     * You may add statements to this method.
     */
    public ArrayList() {
        size = 0;
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
    }
    @Override
    public void addAtIndex(int index, T data) {
        if (index > size) {
            throw new IndexOutOfBoundsException("Error: Index is too large");
        }
        if (index < 0) {
            throw new IndexOutOfBoundsException("Error: Index is negative");
        }
        if (data == null) {
            throw new IllegalArgumentException("Cannot add null to array");
        }
        //case of full backing array
        if (size == backingArray.length) {
            int leng = backingArray.length;
            T[] copyArr = (T[]) new Object[leng * 2];
            //copy everything from before the index to add at
            for (int j = 0; j < index; j++) {
                copyArr[j] = backingArray[j];
            }
            //copy in the data
            copyArr[index] = data;
            //copy in everything from after the index
            for (int k = index; k < size; k++) {
                copyArr[k + 1] = backingArray[k];
            }
            //now set the old array to the new one
            backingArray = copyArr;
        }
        //case of
        else if (index != size) {
            for (int i = backingArray.length - 1; i > index; i--) {
                backingArray[i] = backingArray[i - 1];
            }
        }
        else {
            backingArray[size] = data;
        }
        backingArray[index] = data;
        size++;
    }

    @Override
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot add null to array");
        }
        if (size == 0) {
            backingArray[0] = data;
        }
        else if (size == backingArray.length) {
            T[] copyArr = (T[]) new Object[size * 2];
            for (int i = 0; i < size; i++) {
                copyArr[i+1] = backingArray[i];
            }
            copyArr[0] = data;
            backingArray = copyArr;
        } else {
            for (int j = backingArray.length - 1; j > 0; j--) {
                backingArray[j] = backingArray[j - 1];
            }
            backingArray[0] = data;
        }
        size++;
    }

    @Override
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot add null to array");
        }
        if (size == backingArray.length) {
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
    public T removeAtIndex(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("Error: Index is too large");
        }
        if (index < 0) {
            throw new IndexOutOfBoundsException("Error: Index is negative");
        }
        if (size == 0) {
            return null;
        }
        T retObj = backingArray[index];
        backingArray[index] = null;

        for (int k = index; k < backingArray.length - 1; k++) {
            backingArray[k] = backingArray[k + 1];
        }
        size--;
        return retObj;
    }

    @Override
    public T removeFromFront() {
        if (size == 0) {
            return null;
        }
        T frontObj = removeAtIndex(0);
        // backingArray[backingArray.length - 1] = null;
        return frontObj;
    }

    @Override
    public T removeFromBack() {
        if (size == 0) {
            return null;
        }
        T backObj = backingArray[size - 1];
        backingArray[size - 1] = null;
        size--;
        return backObj;
    }

    @Override
    public T get(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("Error: Index is too large");
        }
        if (index < 0) {
            throw new IndexOutOfBoundsException("Error: Index is negative");
        }
        return (backingArray[index]);
    }

    public boolean removeFirstOcc(T data) {
        int a = 0;
        if (size == 0) {
            return false;
        }
        else if (backingArray[0] == data) {
            backingArray[0] = null;
            size--;
            return true;
        }
        while (a < size && backingArray[a] != data) {
            a++;
        }
        if(a == (size - 1) && backingArray[a] != data) {
            return false;
        }
        else if (backingArray[a] == data) {
            backingArray[a] = null;
            size--;
            for (int k = a; k < backingArray.length - 1; k++) {
                backingArray[k] = backingArray[k + 1];
            }
            return true;
        }
        else {
            return false;
        }

    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        size = 0;
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
    }

    @Override
    public Object[] getBackingArray() {
        // DO NOT MODIFY.
        return backingArray;
    }

    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();
        arr.addToBack(0);
        arr.addToBack(2);
        arr.addToBack(3);
        arr.addToBack(4);
        arr.addToBack(1);
        arr.addToBack(1);
        arr.addToBack(1);
        arr.addToBack(1);
        arr.addToBack(1);
        arr.addToBack(1);
        arr.addToBack(1);
        System.out.println(arr.size());
        Object[] back = arr.getBackingArray();
        boolean rem = arr.removeFirstOcc(1);
        for (int i = 0; i < back.length; i++) {
            System.out.println(back[i]);
        }
        System.out.println(rem);
        System.out.println(arr.size());

    }
}
