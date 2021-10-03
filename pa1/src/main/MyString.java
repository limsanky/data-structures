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

    public MyString() {
        /*
         * Constructor 
         * Create an empty String class
         */
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
    }

    @Override
    public IListNode head() {
        /*
         * Function input:
         *  None
         * 
         * Job:
         *  Return the head of the linked list. If empty, return null.
         */
        return null;
    }

    @Override
    public IListNode tail() {
        /*
         * Function input:
         *  None
         * 
         * Job:
         *  Return the tail of the linked list. If empty, return null.
         */
        return null;
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
