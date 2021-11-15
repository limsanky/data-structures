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
    private Node<V> root;
    private int size;

    public Heap() {
        /*
         * Constructor
         * This function is an initializer for this class.
         */
        root = null;
        size = 0;
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

    /**
     * Swap the two input node.
     * It just swaps the key and value of the two nodes.
     * @param from Node to be swapped
     * @param to Node to be swapped with
     */
    private void swapNodes(INode<V> from, INode<V> to) {
        int fromKey = from.getKey();
        V fromValue = from.getValue();

        from.setValue(to.getValue());
        from.setKey(to.getKey());

        to.setValue(fromValue);
        to.setKey(fromKey);
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

        if (this.isEmpty()) throw new IllegalStateException();

        // Creating temp var of root's value for swapping
        V tempValue = root.getValue();

        // If root is the only node present
        if (size == 1) {
            root = null;
            size--;
            return tempValue;
        }

        // Creating temp var of root's key for swapping
        int tempKey = root.getKey();

        // Getting the last node for swapping with root
        Node<V> lastNode = (Node<V>) getLastNode(root);

        // Swapping root and node
        root.setKey(lastNode.getKey());
        root.setValue(lastNode.getValue());

        lastNode.setValue(tempValue);
        lastNode.setKey(tempKey);

        // Severing the connection between the last node (the former root) and its parent node.
        if (lastNode.getParent().getLeft().getKey() == lastNode.getKey())
            lastNode.getParent().setLeft(null);
        else
            lastNode.getParent().setRight(null);

        lastNode.setParent(null);

        // Checking and correcting the heap requirements
        Node<V> current = root;

        // Run to check whether all nodes satisfy the heap requirements.
        // If not, then swap with the smallest child!
        while (hasChildren(current)) {
            if(current.getRight() != null) {
                if (current.getRight().getKey() < current.getLeft().getKey()) {
                    if (current.getRight().getKey() < current.getKey()) {
                        swapNodes(current, current.getRight());
                        current = (Node<V>) current.getRight();
                    }
                } else {
                    if (current.getLeft().getKey() < current.getKey()) {
                        swapNodes(current, current.getLeft());
                        current = (Node<V>) current.getLeft();
                    }
                }
            } else {
                if (current.getLeft().getKey() < current.getKey()) {
                    swapNodes(current, current.getLeft());
                    current = (Node<V>) current.getLeft();
                } else break;
            }
        }

        // Reduce size by 1
        size--;

        // Return the min value (of the former root)
        return tempValue;
    }

    /**
     * Returns a boolean that indicates whether the given node has at least one child or not.
     * @param node Input node
     * @return Returns false if the input node has no children, else true if there is at least 1 child.
     */
    private boolean hasChildren(INode<V> node) {
        return node.getRight() != null || node.getLeft() != null;
    }

    /**
     * Returns a bool that indicates whether the given node has a parent or not.
     * I.e. it returns true if node is not the root.
     * @param node Input node
     * @return True if node has a parent, false if not.
     */
    private boolean hasParent(INode<V> node) {
        return node.getParent() != null;
    }

    /**
     * Finds the last (the lowest farthest) node, and returns it.
     * It uses the size's binary string and navigates to find the last node.
     * @param node root node
     * @return The last (the lowest farthest) node
     */
    private INode<V> getLastNode(INode<V> node) {
        if (size == 1) return root;

        String binStr = Integer.toBinaryString(size);

        for (int i = 1; i < binStr.length(); i++)
            if (binStr.charAt(i) != '1')
                node = node.getLeft();
            else
                node = node.getRight();

        return node;
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
        if (this.isEmpty()) throw new IllegalStateException();

        return root.getValue();
    }

    @Override
    public int size() {
        /*
         * Function input: Nothing
         *
         * Job:
         *  Return the number of entries in the heap.
         */
        return size;
    }

    @Override
    public boolean isEmpty() {
        /*
         * Function input: Nothing
         *
         * Job:
         *  Return whether of not the heap is empty.
         */
        return size == 0;
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

        return root;
    }
}
