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
    private HashNode<K>[] hashTable;
    private int count;
    private int hashTableSize;
    private final IHashFunction<K> hashTool;
    private final IResizeFunction resizeTool;
    private int lastSearchedElementIndex = -1;

    /**
     * Custom Hash Node is created which is a generic type.
     * @param <T> Generic Type
     */
    private class HashNode<T> {
        public T key = null;
        public boolean isDefunct = false;

        public HashNode() {}

        public HashNode(T k) {
            key = k;
            isDefunct = false;
        }

        public void clearNode() {
            key = null;
            isDefunct = true;
        }
    }

    public Hash(int tablesize) {
        /*
         * Constructor
         * This function is an initializer for this class. You should implement your own HashFunction and ResizeFunction.
         * Input:
         *  + tablesize: the initial table size of the hash table.
         */

        hashTable = new HashNode[tablesize];
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
        hashTable = new HashNode[tablesize];
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

            // Linear Probing
            while (hashTable[index] != null) {
                if (hashTable[index].key != null)
                    index++;
                else break;
            }


            hashTable[index] = new HashNode<>(key);
            count++;

            if (resizeTool.checkResize(hashTableSize, count)) {
                // Table needs to be resized

                int newSize = resizeTool.extendTable(hashTableSize);
                HashNode<K>[] newTable = new HashNode[newSize];

                int done = 0;
                for (int i = 0; i < hashTableSize; i++) {
                    if (done < count) {
                        HashNode<K> element = hashTable[i];

                        if (element != null) {

                            int newIndex = hashTool.hash(element.key, newSize);

                            // Linear Probing
                            while (newTable[newIndex] != null) {
                                if (newTable[newIndex].key != null)
                                    newIndex++;
                                else break;
                            }

                            newTable[newIndex] = new HashNode<>(element.key);
                            done++;
                        }
                    }
                    else
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
                if (hashTable[index] != null) {
                    if (key.equals(hashTable[index].key)) {
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
        return -1;
    }

    @Override
    public int tablesize() {
        /*
        * Job:
        *  Return the size of current hashtable.
        */
        return -1;
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
        return null;
    }

}
