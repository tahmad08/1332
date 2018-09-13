import java.util.Comparator;
import java.util.Random;
import java.util.LinkedList;

/**
 * Your implementation of various sorting algorithms.
 *
 * @author Tahirah Ahmad
 * @userid tahmad8
 * @GTID 903175115
 * @version 1.0
 */
public class Sorting {

    /**
     * Implement bubble sort.
     *
     * It should be:
     *  in-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * You may assume that the array doesn't contain any null elements.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void bubbleSort(T[] arr, Comparator<T> comparator) {
        if (arr == null) {
            throw new IllegalArgumentException("Error: array can't be null");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("Error: comparator"
                + " can't be null");
        }
        int length = arr.length;
        int i = 0;
        boolean swapped = true;
        while (i < (length - 1) && swapped) {
            swapped = false;
            for (int j = 0; j < (length - i - 1); j++) {
                if (comparator.compare(arr[j], arr[j + 1]) > 0) {
                    swap(arr, j, j + 1);
                    swapped = true;
                }
            }
            i++;
        }

    }

    /**
     * a private method for swappin for them o(n^2) methods
     * @param array to swap items
     * @param a larger item to swap
     * @param b smaller iten to swap
     * @param <T> data type to sort
     */
    private static <T> void swap(T[] array, int a, int b) {
        T old = array[a];
        array[a] = array[b];
        array[b] = old;
    }

    /**
     * Implement insertion sort.
     *
     * It should be:
     *  in-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * You may assume that the array doesn't contain any null elements.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null) {
            throw new IllegalArgumentException("Error: array can't be null");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("Error: comparator"
                + " can't be null");
        }
        // T old = null;
        int length = arr.length;
        for (int i = 0; i < (length); i++) {
            int j = i;
            while (j > 0 && (comparator.compare(arr[j - 1], arr[j]) > 0)) {
                swap(arr, j - 1, j);
                j--;
            }
        }
    }

    /**
     * Implement selection sort.
     *
     * It should be:
     *  in-place
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n^2)
     *
     *
     * You may assume that the array doesn't contain any null elements.
     *
     * Note that there may be duplicates in the array.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void selectionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null) {
            throw new IllegalArgumentException("Error: array can't be null");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("Error: comparator"
                + " can't be null");
        }
        int length = arr.length;
        int currMin = 0;
        for (int i = 0; i < length; i++) {
            currMin = i;
            for (int j = i + 1; j < (length); j++) {
                if (comparator.compare(arr[j], arr[currMin]) < 0) {
                    currMin = j;
                }
            }
            swap(arr, currMin, i);
        }
    }

    /**
     * Implement quick sort.
     *
     * Use the provided random object to select your pivots.
     * For example if you need a pivot between a (inclusive)
     * and b (exclusive) where b > a, use the following code:
     *
     * int pivotIndex = rand.nextInt(b - a) + a;
     *
     * It should be:
     *  in-place
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n log n)
     *
     * You may assume that the array doesn't contain any null elements.
     *
     * Note that there may be duplicates in the array.
     *
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not get full
     * credit if you do not use the one we have taught you!
     *
     * @throws IllegalArgumentException if the array or comparator or rand is
     * null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @param rand the Random object used to select pivots
     */
    public static <T> void quickSort(T[] arr, Comparator<T> comparator,
                                     Random rand) {
        if (arr == null) {
            throw new IllegalArgumentException("Error: array can't be null");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("Error: comparator"
                + " can't be null");
        }
        if (rand == null) {
            throw new IllegalArgumentException("Error: rand can't be null");
        }

        quickSort(arr, comparator, rand, 0, arr.length - 1);
    }

    /**
     * recursive quick sort function
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @param rand the Random object used to select pivots
     * @param left left pointer, basically i
     * @param right pointer, like j
     */
    private static <T> void quickSort(T[] arr, Comparator<T> comparator,
        Random rand, int left, int right) {

        if (!(left >= right)) {
            int pivotInd = rand.nextInt(right - left) + left;
            T pivot = arr[pivotInd];
            swap(arr, left, pivotInd);

            int leftInd = left + 1;
            int rightInd = right;

            while (leftInd <= rightInd) {
                while ((leftInd <= rightInd)
                    && comparator.compare(arr[leftInd], pivot) <= 0) {
                    leftInd++;
                }
                while ((leftInd <= rightInd)
                    && comparator.compare(arr[rightInd], pivot) >= 0) {
                    rightInd--;
                }
                if (leftInd < rightInd) {
                    swap(arr, leftInd, rightInd);
                    leftInd++;
                    rightInd--;
                }
            }
            // swap(arr, rightInd, leftInd)
            arr[left] = arr[rightInd];
            arr[rightInd] = pivot;

            //swap(arr, pivotInd, rigthInd);
            if (left < (rightInd - 1)) {
                quickSort(arr, comparator, rand, left, (rightInd - 1));
            }
            if ((rightInd + 1) < right) {
                quickSort(arr, comparator, rand, (rightInd + 1), right);
            }
        }

    }

    /**
     * Implement merge sort.
     *
     * It should be:
     *  stable
     *
     * Have a worst case running time of:
     *  O(n log n)
     *
     * And a best case running time of:
     *  O(n log n)
     *
     * You may assume that the array doesn't contain any null elements.
     *
     * You can create more arrays to run mergesort, but at the end,
     * everything should be merged back into the original T[]
     * which was passed in.
     *
     * When splitting the array, if there is an odd number of elements, put the
     * extra data on the right side.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Error, "
                + "argument cannot be null");
        }
        int length = arr.length;
        if (length > 1) {

            int mid = arr.length / 2;

            //create left array
            T[] lefto = (T[]) new Object[mid];
            for (int i = 0; i < mid; i++) {
                lefto[i] = arr[i];
            }
            //create right array
            int rightLen = (int) (arr.length - mid);
            T[] righto = (T[]) new Object[rightLen];
            for (int i = mid; i < arr.length; i++) {
                righto[i - mid] = arr[i];
            }

            mergeSort(lefto, comparator);
            mergeSort(righto, comparator);

            merge(lefto, righto, arr, comparator);
        }
    }

    /**
     * the merging algorithm
     * @param lefto the left array
     * @param righto the right array
     * @param arr the main array
     * @param comparator to compare
     * @param <T> the data type
     */
    private static <T> void merge(T[] lefto, T[] righto,
        T[] arr, Comparator<T> comparator) {

        T[] newArr = (T[]) new Object[righto.length + lefto.length];
        int indxL = 0;
        int indxR = 0;
        int indxCurr = 0;
        //this part iterates through both the sorted arrays and add them in order
        //of what is smallest from both. After one element from an array is added,
        //the marker on that array increments
        //continue till one array is completely emptied
        while ((indxL < lefto.length) && (indxR < righto.length)) {
            if (comparator.compare(lefto[indxL], righto[indxR]) <= 0) {
                arr[indxCurr] = lefto[indxL];
                indxL++;
            } else {
                arr[indxCurr] = righto[indxR];
                indxR++;
            }
            indxCurr++;
        }
        //by now, one array is emptied. this loop goes through remaining
        //elements in the left array and dumps them all in the sorted main array
        while (indxL < lefto.length) {
            arr[indxCurr] = lefto[indxL];
            indxL++;
            indxCurr++;
        }
        //goes through right array and dumps any remaining elements in sorted
        //array
        while (indxR < righto.length) {
            arr[indxCurr] = righto[indxR];
            indxR++;
            indxCurr++;
        }
    }

    /**
     * Implement LSD (least significant digit) radix sort.
     *
     * Remember you CANNOT convert the ints to strings at any point in your
     * code! Doing so may result in a 0 for the implementation.
     *
     * It should be:
     *  stable
     *
     * Have a worst case running time of:
     *  O(kn)
     *
     * And a best case running time of:
     *  O(kn)
     *
     * Refer to the PDF for more information on LSD Radix Sort.
     *
     * You may use {@code java.util.ArrayList} or {@code java.util.LinkedList}
     * if you wish, but it may only be used inside radix sort and any radix sort
     * helpers. Do NOT use these classes with other sorts.
     *
     * @throws IllegalArgumentException if the array is null
     * @param arr the array to be sorted
     */
    public static void lsdRadixSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Error, array can't be null");
        }

        if (arr.length == 0) {
            return;
        }

        int iterations = iterations(arr);
        int length = arr.length;

        int divider = 1;
        LinkedList<Integer>[] buckets = new LinkedList[19];
        for (int i = 0; i < 19; i++) {
            buckets[i] = new LinkedList<Integer>();
        }

        for (int i = 0; i < iterations; i++) {
            for (int j = 0; j < length; j++) {

                int bucket = (arr[j] / divider) % 10;
                buckets[bucket + 9].add(arr[j]);
            }
            int index = 0;
            for (int buck = 0; buck < buckets.length; buck++) {
                LinkedList<Integer> curr = buckets[buck];
                while (curr.size() > 0) {
                    arr[index] = curr.remove();
                    index++;
                }
            }
            divider *= 10;
        }

    }

    /**
     * a helper method for finding the num of iterations
     * @param arr to find max iterations needed
     * @return the num of iterations
     */
    private static int iterations(int[] arr) {
        int max = arr[0];

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 0) {
                int check = arr[i] * -1;
                if (check >= max) {
                    max = arr[i];
                }
            } else {
                int maxCheck = max;
                if (arr[i] >= (maxCheck < 0 ? (maxCheck * -1) : maxCheck)) {
                    max = arr[i];
                }
            }
        }
        int its = 0;
        int dix = max;
        while (dix != 0) {
            dix = (int) dix / 10;
            its++;
        }
        return its;
    }
}
