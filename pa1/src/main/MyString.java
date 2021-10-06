/*
 * Name: 
 * Student ID #: 
 */

/*
 * Do NOT import any additional packages/classes.
 * If you (un)intentionally use some additional packages/classes we did not
 * provide, you may receive a 0 for the homework.
 */

public final class MyString implements IString{
    /*
     * Required Variables
     */
    private final ListNode head, tail;
    private int size = 0;

    /*
     * Constructor
     */
    public MyString() {
        head = tail = new ListNode();
        head.setNext(tail);
        tail.setPrev(head);
    }

    /*
     * Required Methods
     */

    /*
     * Returns whether this list is empty or not.
     * @return Boolean
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /*
     * Adds first element to list.
     * @param x - Character which is supposed to be the first element of the list.
     */
    private void addFirst(char x){
        ListNode newNode = new ListNode(x);

        head.setNext(newNode);
        tail.setPrev(newNode);

        newNode.setNext(tail);
        newNode.setPrev(head);

        size++;
    }

    @Override
    public void append(char x) {
        // Adds first element since list is empty.
        if(isEmpty()) addFirst(x);

        // Add element as the last element of the list.
        else {
            ListNode newNode = new ListNode(x);

            newNode.setPrev(tail.prev());
            tail.prev().setNext(newNode);

            newNode.setNext(tail);
            tail.setPrev(newNode);

            size++;
        }
    }

    @Override
    public void prepend(char x) {
        // Adds first element since list is empty.
        if(isEmpty()) addFirst(x);

        // Add element as the first element of the list.
        else {
            ListNode newNode = new ListNode(x);

            head.next().setPrev(newNode);
            newNode.setNext(head.next());

            newNode.setPrev(head);
            head.setNext(newNode);

            size++;
        }
    }

    @Override
    public IListNode head() {
        if(isEmpty()) return null;

        return head.next();
    }

    @Override
    public IListNode tail() {
        if(isEmpty()) return null;

        return tail.prev();
    }

    @Override
    public int findFirst(char x) {
        if(isEmpty()) return -1;

        IListNode current = head.next();
        int index = 0;

        while(current != tail) {
            if (current.value() != x) {
                index++;
                current = current.next();
            } else return index;
        }

        // Did not find element x
        return -1;
    }

    @Override
    public int findLast(char x) {
        if(isEmpty()) return -1;

        IListNode current = tail.prev();
        int index = size - 1;

        while(current != head){
            if (current.value() != x) {
                index--;
                current = current.prev();
            } else return index;
        }

        // Did not find element x
        return -1;
    }

    @Override
    public boolean lessOrEqual(IString s) {
        // If [this] is empty.
        if(isEmpty()) return true;

        // [this] is not empty, but [s] is empty.
        if(s.size() == 0) return false;

        // [this] & [s] are not empty.
        int smallestLength;

        if (s.size() >= size) smallestLength = size;
        else smallestLength = s.size();

        IListNode currentThis = head.next();
        IListNode currentS = s.head();

        for(int i = 0; i < smallestLength; i++) {
            // Check if the value of [s] is >= that of [this]
            if (currentS.value() >= currentThis.value()) {
                currentS = currentS.next();
                currentThis = currentThis.next();
            } else return false; // Return false if value of [s] is < than that of [this].
        }

        // Return true, as all elements of [s] are >= those of [this].
        return true;
    }

    @Override
    public char[] print() {
        if(isEmpty()) return new char[0];

        char[] returnArray = new char[size];
        IListNode current = head.next();

        for(int i = 0; i < size; i++){
            returnArray[i] = current.value();
            current = current.next();
        }

        return returnArray;
    }

    @Override
    public int size() {
        return size;
    }
}
