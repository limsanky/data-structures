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

    private int size = 0;
    private Node<E> head;
    private Node<E> tail;

    /**
     * Constructor
     */
    public Deque() {
        /*
        * Constructor
        * This function is an initializer for this class.
        */
        head = new Node<>();
        tail = new Node<>();

        // Connect [head] and [tail] together
        head.setNext(tail);
        tail.setPrev(head);
    }

    /**
     * Adds a new element @item at the front of the Deque
     * @param item The data of the node added at the beginning of the Deque
     */
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

        // Create new node to be added at the beginning of the deque,
        // and set its value to be [item].
        Node<E> first = new Node<E>();
        first.setVal(item);

        // Connect [first] to [head] and [tail], if the deque is empty
        if(isEmpty()){
            first.setNext(tail);
            first.setPrev(head);

            tail.setPrev(first);
            head.setNext(first);

            size++;
            return;
        }

        // Connect [first] to the [head] and previous first element.
        first.setNext(head.next());
        first.setPrev(head);

        head.next().setPrev(first);
        head.setNext(first);

        // Increase size by 1
        size++;
    }

    /**
     * Adds a new element @item at the end of the Deque
     * @param item The data of the node added at the end of the Deque
     */
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

        // Connect [last] to [head] and [tail], if the deque is empty
        if(isEmpty()) {
            // It's virtually the same as adding a first node at the beginning, since the list is empty.
            insertFirst(item);
            return;
        }

        // Create new node to be added at the beginning of the deque,
        // and set its value to be [item].
        Node<E> last = new Node<E>();
        last.setVal(item);

        // Connect [last] to the [tail] and previous last element.
        last.setNext(tail);
        last.setPrev(tail.prev());

        tail.prev().setNext(last);
        tail.setPrev(last);

        // Increase size by 1
        size++;
    }

    /**
     * Deletes the first element from the Deque
     * @throws IllegalStateException if the Deque is empty
     */
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

        if(isEmpty()) throw new IllegalStateException();

        // Severe connection
        head.setNext(head.next().next());
        head.next().setPrev(head);

        // Reduce size by 1
        size--;
    }

    /**
     * Deletes the last element from the Deque
     * @throws IllegalStateException if the Deque is empty
     */
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

        if(isEmpty()) throw new IllegalStateException();

        // Severe connection
        tail.prev().prev().setNext(tail);
        tail.setPrev(tail.prev().prev());

        // Reduce size by 1
        size--;
    }

    /**
     * This method returns the data held in the first node
     * @return Data held in the first node
     * @throws IllegalStateException if the Deque is empty
     */
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
        if(isEmpty()) throw new IllegalStateException();

        return (E) head.next().val();
    }

    /**
     * This method returns the data held in the last node
     * @return Data held in the last node
     * @throws IllegalStateException if the Deque is empty
     */
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
        if(isEmpty()) throw new IllegalStateException();

        return (E) tail.prev().val();
    }

    @Override
    public int size() {
        /*
        * Function input: Nothing
        *
        * Job:
        *  Return the number of items in the deque.
        */
        return size;
    }

    @Override
    public boolean isEmpty() {
        /* You do not have to edit this function. */
        return size() == 0;
    }
}
