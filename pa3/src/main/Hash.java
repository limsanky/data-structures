/*
* Name:
* Student ID#:
*/

import java.lang.Math;
import java.util.List;
import java.util.ArrayList;
/*
* Do NOT use any external packages/classes.
* If you (un)intentionally use them we did not provide,
* you will get 0.
* Also do NOT use auto-import function on IDEs.
* If the import statements change, you will also get 0.
*/

public final class Hash<K> implements IHash<K> {
    /*
    * Use some variables for your implementation.
    */
    private ArrayList<K> hashTable;
    private int count;
    private int hashTableSize;
    private final IHashFunction<K> hashTool;
    private final IResizeFunction resizeTool;
    private int lastSearchedElementIndex = -1;

    public Hash(int tablesize) {
        /*
         * Constructor
         * This function is an initializer for this class. You should implement your own HashFunction and ResizeFunction.
         * Input:
         *  + tablesize: the initial table size of the hash table.
         */

        hashTable = new ArrayList<>(tablesize);
        for (int i = 0; i < tablesize; i++)
            hashTable.add(i, null);
        hashTableSize = tablesize;
        count = 0;

        hashTool = new IHashFunction<K>() {
            @Override
            public int hash(K i, int length) {
                return i.hashCode() % length;
            }
        };

        resizeTool = new IResizeFunction() {
            public boolean checkResize(int tablesize, int numItems) {
                return numItems > tablesize * 0.8;
            }

            public int extendTable(int tablesize) {
                return tablesize * 2;
            }
        };
    }

    public Hash(int tablesize, IHashFunction<K> h, IResizeFunction ex) {
        /*
         * Constructor
         * This function is an initializer for this class.
         * Input:
         *  + IHashFunction<K> h:
         *      int h.hash(K key, int tablesize): returns an integral hash value of key.
         *  + IResizeFunction ex:
         *      boolean ex.checkResize(int tablesize, int numItems): returns 'true' if the table must be extended for containing 'numItems' items. Otherwise, returns 'false'.
         *      int ex.extendTable(int tablesize): returns new tablesize for extended table.
         *  + tablesize: the initial table size of the hash table.
         */
        hashTable = new ArrayList<>(tablesize);
        for (int i = 0; i < tablesize; i++)
            hashTable.add(i, null);
        hashTableSize = tablesize;
        count = 0;

        hashTool = h;
        resizeTool = ex;
    }

    @Override
    public void put(K key) {
        /**
         * Input:
         * + key: A key to be added
         *
         * Job:
         *  Add the key into the hashtable.
         *  If the table must be extended, extend the table and retry adding the key.
         *  If the key is already in the hashtable, ignore the request.
         *  To decide whether two keys are equal,
         *  you must use _key.equals_ method.
         */
        if (!exists(key)) {
            // Key doesn't exist in the table

            int index = hashTool.hash(key, hashTableSize);

            if (count != 0)
                while (!key.equals(hashTable.get(index))) {
                    if (hashTable.get(index) == null)
                        break;
                    index++;
                }

            hashTable.set(index, key);
            count++;

            if (resizeTool.checkResize(hashTableSize, count)) {
                // Table needs to be resized

                int newSize = resizeTool.extendTable(hashTableSize);
                ArrayList<K> newTable = new ArrayList<K>(newSize);

                for (int i = 0; i < newSize; i++)
                    newTable.add(i, null);

                int done = 0;
                for (int i = 0; i < hashTableSize; i++) {
                    if (done < count) {
                        K element = hashTable.get(i);

                        if (element != null) {

                            int newIndex = hashTool.hash(element, newSize);

                            // Linear Probing
                            while (!element.equals(newTable.get(newIndex))) {
                                if (newTable.get(newIndex) == null)
                                    break;
                                newIndex++;
                            }

                            newTable.set(newIndex, element);
                            done++;
                        }
                    } else
                        break;
                }

                hashTable = newTable;
                hashTableSize = newSize;
            }
        }
    }

    @Override
    public void remove(K key) throws IllegalStateException {
        /*
         * Input:
         *  + key: A key to be removed
         *
         * Job:
         *  Delete the key from the hash table.
         *  To decide whether two keys are equal,
         *  you must use _key.equals_ method.
         *  If the key does not exist in the table, raise an exception.
         */
        if (!exists(key))
            throw new IllegalStateException();

        if (lastSearchedElementIndex > -1)
            if (key.equals(hashTable.get(lastSearchedElementIndex))) {
                hashTable.set(lastSearchedElementIndex, null);
                count--;
            }
    }

    @Override
    public boolean exists(K key) {
        /*
         * Input:
         *  + key: A key to be checked
         *
         * Job:
         *  Return true if the key is in the table; false otherwise.
         *  To decide whether two keys are equal,
         *  you must use _key.equals_ method.
         */

        if (count != 0) {
            int index = hashTool.hash(key, hashTableSize);

            int n = 0;
            while (n < hashTableSize && index < hashTableSize) {
                if (hashTable.get(index) != null) {
                    if (key.equals(hashTable.get(index))) {
                        lastSearchedElementIndex = index;
                        return true;
                    }
                } else {
                    lastSearchedElementIndex = -1;
                    return false;
                }

                index++;
                n++;
            }
        }

        lastSearchedElementIndex = -1;
        return false;
    }

    @Override
    public int size() {
        /*
         * Job:
         *  Return the number of items in the hashtable.
         */
        return count;
    }

    @Override
    public int tablesize() {
        /*
         * Job:
         *  Return the size of current hashtable.
         */
        return hashTableSize;
    }

    @Override
    public List<K> show() {
        /*
         * Job:
         *  Return the items in the hashtable.
         *  The list index must be the bucket index of the item.
         *  If a bucket has no item, assign null.
         *  Note that you can use ArrayList.
         */
        ArrayList<K> list = new ArrayList<K> (hashTableSize);

        for(int i = 0; i < hashTableSize; i++)
            list.add(hashTable.get(i));

        return list;
    }

}
