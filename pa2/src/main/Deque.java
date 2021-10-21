/*
* Name:
* Student ID #:
*/

/*
* Do NOT import any additional packages/classes.
* If you (un)intentionally use some additional packages/classes we did not
* provide, you may receive a 0 for the homework.
*/

/* Double-ended queue */
public final class Deque<E> implements IDeque<E> {
    /*
    * Use some variables for your implementation.
    */

    public Deque() {
        /*
        * Constructor
        * This function is an initializer for this class.
        */
    }

    @Override
    public void insertFirst(E item) {
        /*
        * Function input:
        *  item: an item to be inserted.
        *
        * Job:
        *  Insert the given item before the first item of this deque.
        *  If this deque is empty, use this item as the first item.
        */
    }

    @Override
    public void insertLast(E item) {
        /*
        * Function input:
        *  item: an item to be inserted.
        *
        * Job:
        *  Insert the given item after the last item of this deque.
        *  If this deque is empty, use this item as the last item.
        */
    }

    @Override
    public void deleteFirst()
            throws IllegalStateException {
        /*
        * Function input: Nothing
        *
        * Job:
        *  Delete the first item of the deque.
        *
        *  If there is no item, raise an IllegalStateException.
        *  You do not have to specify its message.
        */
    }

    @Override
    public void deleteLast()
            throws IllegalStateException {
        /*
        * Function input: Nothing
        *
        * Job:
        *  Delete the last item of the deque.
        *
        *  If there is no item, raise an IllegalStateException.
        *  You do not have to specify its message.
        */
    }

    @Override
    public E first()
            throws IllegalStateException {
        /*
        * Function input: Nothing
        *
        * Job:
        *  Return the first item.
        *
        *  If there is no item, raise an IllegalStateException.
        *  You do not have to specify its message.
        */
        return null;
    }

    @Override
    public E last()
            throws IllegalStateException {
        /*
        * Function input: Nothing
        *
        * Job:
        *  Return the last item.
        *
        *  If there is no item, raise an IllegalStateException.
        *  You do not have to specify its message.
        */
        return null;
    }

    @Override
    public int size() {
        /*
        * Function input: Nothing
        *
        * Job:
        *  Return the number of items in the deque.
        */
        return -1;
    }

    @Override
    public boolean isEmpty() {
        /* You do not have to edit this function. */
        return size() == 0;
    }
}
