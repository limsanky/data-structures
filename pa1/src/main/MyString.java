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
     * Add some variables you will use.
     */

    private final ListNode head, tail;
    private int size = 0;

    public MyString() {
        /*
         * Constructor 
         * Create an empty String class
         */
        head = tail = new ListNode();
        head.setNext(tail);
        tail.setPrev(head);
    }

    /**
     * Returns whether this list is empty or not.
     * @return Boolean
     */
    public boolean isEmpty(){
        return size == 0;
    }

    @Override
    public void append(char x) {
        /*
         * Function input:
         *  + x: A character to be appended
         *
         * Job:
         *  Insert the character to the end of the linked list.
         */

        ListNode newNode = new ListNode(x);

        if(isEmpty()){
            head.setNext(newNode);
            tail.setPrev(newNode);

            newNode.setNext(tail);
            newNode.setPrev(head);
        } else {
            newNode.setPrev(tail.prev());
            tail.prev().setNext(newNode);

            newNode.setNext(tail);
            tail.setPrev(newNode);
        }

        size++;
    }

    @Override
    public void prepend(char x) {
        /*
         * Function input:
         *  + x: A character to be prepended
         *
         * Job:
         *  Insert the character to the start of the linked list.
         */

        ListNode newNode = new ListNode(x);

        if(isEmpty()){
            head.setNext(newNode);
            tail.setPrev(newNode);

            newNode.setNext(tail);
            newNode.setPrev(head);
        } else {
            head.next().setPrev(newNode);
            newNode.setNext(head.next());

            newNode.setPrev(head);
            head.setNext(newNode);
        }

        size++;
    }

    @Override
    public IListNode head() {
        /*
         * Function input:
         *  None
         *
         * Job:
         *  Return the first node of the linked list. If empty, return null.
         */
        if(isEmpty()) return null;

        return head;
    }

    @Override
    public IListNode tail() {
        /*
         * Function input:
         *  None
         * 
         * Job:
         *  Return the last node of the linked list. If empty, return null.
         */
        if(isEmpty()) return null;

        return tail;
    }

    @Override
    public int findFirst(char x) {
        /*
         * Function input:
         *  + x: A character to find
         * 
         * Job:
         *  Return the smallest index which you can find x.
         *  If x is not in the string, return -1.
         */
        return -1;
    }

    @Override
    public int findLast(char x) {
        /*
         * Function input:
         *  + x: A character to find
         * 
         * Job:
         *  Return the largest index which you can find x.
         *  If x is not in the string, return -1.
         */
        return -1;
    }

    @Override
    public boolean lessOrEqual(IString s) {
        /*
         * Function input:
         *  + s: String to compare to.
         * 
         * Job:
         *  Return if this string is less or equal to s in lexicographical order.
         */
        return false;
    }

    @Override
    public char[] print() {
        /*
         * Function input:
         *  None
         * 
         * Job:
         *  Return the whole string.
         */
        return new char[]{};
    }

    @Override
    public int size() {
        /*
         * Function input:
         *  None
         * 
         * Job:
         *  Return the size(length) of the string.
         */
        return 0;
    }
}
