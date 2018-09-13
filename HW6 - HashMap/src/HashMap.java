import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.NoSuchElementException;

/**
 * Your implementation of HashMap.
 *
 * @author Tahirah Ahmad
 * @userid tahmad8
 * @GTID 903175115
 * @version 1.0
 */
public class HashMap<K, V> implements HashMapInterface<K, V> {

    // Do not make any new instance variables.
    private MapEntry<K, V>[] table;
    private int size;

    /**
     * Create a hash map with no entries. The backing array has an initial
     * capacity of {@code INITIAL_CAPACITY}.
     *
     * Do not use magic numbers!
     *
     * Use constructor chaining.
     */
    public HashMap() {
        this(INITIAL_CAPACITY);
    }

    /**
     * Create a hash map with no entries. The backing array has an initial
     * capacity of {@code initialCapacity}.
     *
     * You may assume {@code initialCapacity} will always be positive.
     *
     * @param initialCapacity initial capacity of the backing array
     */
    public HashMap(int initialCapacity) {
        table = (MapEntry<K, V>[]) new MapEntry[initialCapacity];
        size = 0;
    }

    @Override
    public V put(K key, V value) {
        if (key == null || value == null) {
            String error = String.format("Error: "
                    + "Cannot insert null key or value into map");
            throw new IllegalArgumentException(error);
        }
        double currFactor = (double) (size + 1) / (double) (table.length);
        boolean needsResize = currFactor > MAX_LOAD_FACTOR;
        //check for resizing
        if (needsResize) {
            int newSize = (2 * table.length) + 1;
            resizeBackingTable(newSize);
        }

        // Calc the index for the code
        int hashIndex = Math.abs(key.hashCode() % table.length);

        // Create new KV entry
        MapEntry<K, V> newEntry = new MapEntry(key, value);

        // If the spot is available, put it in
        if (table[hashIndex] == null) {
            table[hashIndex] = newEntry;
            size++;
            return null;
        } else {
            //curr is what's currently in the new elements hashcode spot
            MapEntry<K, V> curr = table[hashIndex];

            // If the current index has a diff key + it has an empty next spot
            // (no list after it), then start the list by shifting it forward
            if (!(curr.getKey().equals(key)) && curr.getNext() == null) {
                MapEntry<K, V> newHead = new MapEntry(key, value,
                    table[hashIndex]);
                table[hashIndex] = newHead;
                size++;
                return null;
            } else if ((curr.getKey()).equals(key) || (curr.getKey() == key)) {
                //otherwise if the current index has the same key, replace val
                V val = curr.getValue();
                curr.setValue(value);
                return val;
            } else {
                //else there's a list after this index + u need to check
                // it for more duplicate keys
                //need to iterate
                while (curr.getNext() != null) {
                    // Duplicate check
                    MapEntry<K, V> nexto = curr.getNext();
                    if (nexto.getKey().equals(key)) {
                        V val = nexto.getValue();
                        nexto.setValue(value);
                        return val;
                    }
                    curr = curr.getNext();
                }
                //Now set the front of the linkedlist if there are no duplicates
                //you've taken care of duplicates in prev while loop
                MapEntry<K, V> newHead = new MapEntry(key, value,
                    table[hashIndex]);
                table[hashIndex] = newHead;
                size++;
                return null;
            }
        }
    }

    @Override
    public V remove(K key) {
        if (key == null) {
            String error = String.format("Error: "
                    + "Cannot insert null key into map");
            throw new IllegalArgumentException(error);
        }
        V retVal;
        //find the hashcode for obj to be removed. Obj MUST be at this hashInd
        int hashIndex = Math.abs(key.hashCode() % table.length);
        MapEntry<K, V> loc = table[hashIndex];
        // if hashcode empty, then key not there
        if (loc == null) {
            throw new NoSuchElementException("No such key exists in map");
        }
        //case that the key is at the start of the index
        if (loc.getKey().equals(key)) {
            retVal = loc.getValue();
            //case that the key is the only thing at that index
            if (loc.getNext() == null) {
                table[hashIndex] = null;
                size--;
                return retVal;
            } else {
                //key at start but with a linked list, so shift data of the next
                //node into keys spot + shift keys pointer to next next
                //and this sever ties
                MapEntry<K, V> nexto = loc.getNext();
                MapEntry<K, V> thirdo = nexto.getNext();
                loc.setKey(nexto.getKey());
                loc.setValue(nexto.getValue());
                loc.setNext(thirdo);
                size--;
                return retVal;
            }
        }
        // find key in linked list if it's not the first element
        MapEntry<K, V> prev = loc;
        while (!(loc.getKey().equals(key))) {
            prev = loc;
            loc = loc.getNext();
        }
        //get the value of removing node
        retVal = loc.getValue();
        //case that if its at the end of a list
        if (loc.getNext() == null) {
            prev.setNext(null);
            size--;
            return retVal;
        } else {
            //else shift data, keys and pointers to sever ties like w head
            MapEntry<K, V> nexto = loc.getNext();
            MapEntry<K, V> thirdo = nexto.getNext();
            prev.setNext(nexto);
            size--;
            return retVal;
        }
        throw new NoSuchElementException("Key not in map");
    }


    @Override
    public V get(K key) {
        if (key == null) {
            String error = String.format("Error: "
                    + "Cannot insert null key into map");
            throw new IllegalArgumentException(error);
        }
        //key must be at this hashcode index bc of nature of hashcodes
        int hashIndex = Math.abs(key.hashCode() % table.length);
        if (table[hashIndex] == null) {
            throw new NoSuchElementException("Key not in map!");
        }
        //if first element at the hashcode index
        if ((table[hashIndex].getKey()).equals(key)) {
            return (table[hashIndex].getValue());
        } else {
            //otherwise iterate through it's linked list to find the key
            MapEntry<K, V> curr = table[hashIndex];
            while (curr.getNext() != null) {
                MapEntry<K, V> nexto = curr.getNext();
                if (nexto.getKey().equals(key)) {
                    return nexto.getValue();
                }

                curr = curr.getNext();
            }
        }
        //no key found
        throw new NoSuchElementException("Key not in map");
    }

    @Override
    public boolean containsKey(K key) {
        //same functionality as get()
        if (key == null) {
            String error = String.format("Error: "
                    + "Cannot insert null key into map");
            throw new IllegalArgumentException(error);
        }

        int hashIndex = Math.abs(key.hashCode() % table.length);
        if (table[hashIndex] == null) {
            return false;
        } else if ((table[hashIndex].getKey()).equals(key)) {
            return true;
        } else {
            MapEntry<K, V> curr = table[hashIndex];
            while (curr.getNext() != null) {
                MapEntry<K, V> nexto = curr.getNext();
                if (nexto.getKey().equals(key)) {
                    return true;
                }

                curr = curr.getNext();
            }
        }
        return false;
    }

    @Override
    public void clear() {
        size = 0;
        table = (MapEntry<K, V>[]) new MapEntry[INITIAL_CAPACITY];
    }

    @Override
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keySet = new HashSet<K>();
        //iteratoin through table
        for (int i = 0; i < table.length; i++) {
            MapEntry<K, V> curr = table[i];
            if (curr != null) {
                keySet.add(curr.getKey());
                //iteration through any linkedlists at any index in table
                while (curr.getNext() != null) {
                    MapEntry<K, V> nexto = curr.getNext();
                    keySet.add(nexto.getKey());
                    curr = curr.getNext();
                }
            }
        }
        return keySet;
    }

    @Override
    public List<V> values() {
        //same func as keySet()
        List<V> vals = new ArrayList<V>();
        for (int i = 0; i < table.length; i++) {
            MapEntry<K, V> curr = table[i];
            if (curr != null) {
                vals.add(curr.getValue());
                while (curr.getNext() != null) {
                    MapEntry<K, V> nexto = curr.getNext();
                    vals.add(nexto.getValue());
                    curr = curr.getNext();
                }
            }
        }
        return vals;
    }

    @Override
    public void resizeBackingTable(int length) {

        if ((length <= 0) || (length < size)) {
            throw new IllegalArgumentException("Error! Length can't be that"
                    + " small a size");
        }
        //a temporary table
        MapEntry<K, V>[] tempTable = new MapEntry[length];
        //iterate through curr table (through the array, the outer parts)
        for (int i = 0; i < table.length; i++) {
            //iterating item (starts at first index of table)
            MapEntry<K, V> curr = table[i];
            //if it's not null, add and go through its linkedlist. else, move on
            if (curr != null) {
                //calc a new hashcode based on new length
                int newHash = Math.abs((curr.getKey()).hashCode()
                    % tempTable.length);
                //if the new hashInd is not empty,
                //add it to the front of index in new table and
                // set this new entries next pointer to whatever was there
                if (tempTable[newHash] != null) {
                    MapEntry<K, V> newHead = new MapEntry(curr.getKey(),
                        curr.getValue(), tempTable[newHash]);
                    tempTable[newHash] = newHead;
                } else {
                    //otherwise this spot is empty, add at index
                    MapEntry<K, V> newHead = new MapEntry(curr.getKey(),
                        curr.getValue());
                    tempTable[newHash] = newHead;
                }
                //now, through the spot in the old tables linked list and move
                //items
                while (curr.getNext() != null) {
                    MapEntry<K, V> nexto = curr.getNext();
                    int newChainHash = Math.abs((nexto.getKey()).hashCode()
                        % tempTable.length);
                    //spot in new table not empty, shift stuff forward and add
                    if (tempTable[newChainHash] != null) {
                        MapEntry<K, V> newHead = new MapEntry(nexto.getKey(),
                            nexto.getValue(),
                            tempTable[newChainHash]);
                        tempTable[newChainHash] = newHead;
                    } else {
                        //otherwise it is empty, and add it to the spot
                        MapEntry<K, V> newHead = new MapEntry(nexto.getKey(),
                            nexto.getValue(), null);
                        tempTable[newChainHash] = newHead;
                    }
                    //move onto next item in the linked list
                    //note we are still at one index
                    curr = curr.getNext();
                }
            }
        }
        //set table equal to new resized table
        table = tempTable;
    }

    @Override
    public MapEntry<K, V>[] getTable() {
        // DO NOT EDIT THIS METHOD!
        return table;
    }


}
