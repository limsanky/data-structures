/*
* Name:
* Student ID #:
*/

/*
* Do NOT import any additional packages/classes.
* If you (un)intentionally use some additional packages/classes we did not
* provide, you may receive a 0 for the homework.
*/

/* Min Heap stores entry with integer key and arbitrary type value */
public final class Heap<V> implements IHeap<V> {
    /*
    * Use some variables for your implementation.
    */

    public Heap() {
        /*
        * Constructor
        * This function is an initializer for this class.
        */
    }

    @Override
    public void bottomUp(int[] keys, V[] values) throws IllegalStateException {
        /*
        * Function input:
        *  keys: keys of entries
        *  values: values of entries
        *
        * Job:
        *  Construct a heap with entries (keys[i], values[i]).
        *  You *must* construct a heap using *bottom-up construction*.
        *  If the heap is not empty or lengths of keys and values are different,
        *  throw an exception.
        */
        return;
    }

    @Override
    public void insert(int key, V value) {
        /*
        * Function input:
        *  key: key of an entry
        *  value: value of an entry
        *
        * Job:
        *  Insert an entry with the given key and value.
        *  You can assume that each key of given entry is unique.
        */
        return;
    }

    @Override
    public V removeMin() throws IllegalStateException {
        /*
        * Function input: Nothing
        *
        * Job:
        *  Remove the entry with the smallest key in the heap and 
        *  return the value of the removed entry.
        *  If the heap is empty, throw an exception.
        */
        return null;
    }

    @Override
    public V min() throws IllegalStateException {
        /*
        * Function input: Nothing
        *
        * Job:
        *  Return the value of the entry with the smallest key.
        *  If the heap is empty, throw an exception.
        */
        return null;
    }

    @Override
    public int size() {
        /*
        * Function input: Nothing
        *
        * Job:
        *  Return the number of entries in the heap.
        */
        return 0;
    }

    @Override
    public boolean isEmpty() {
        /*
        * Function input: Nothing
        *
        * Job:
        *  Return whether of not the heap is empty.
        */
        return false;
    }

    @Override
    public INode<V> getRoot() {
        /*
        * Function input: Nothing
        *
        * Job:
        *  Return the root node of the heap.
        *  If the heap is empty, throw an exception.
        */
        if (this.isEmpty()) throw new IllegalStateException();
        return null;
    }
}
