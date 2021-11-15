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

        if (!isEmpty() || (keys.length != values.length))
            throw new IllegalStateException();

        if (keys.length != 0) {
            int count = keys.length;

            // Create heap in array format
            for (int i = count / 2 - 1; i >= 0; i--)
                createHeap(keys, values, i, count);

            // Linking the Nodes to create the Heap
            linkHeapNodes(keys, values);

            size = keys.length;
        }
    }

    /**
     * Recursively creates an array-based heap for
     * the provided [keys] and corresponding [values] arrays.
     * @param keys Array of keys
     * @param values Array of corresponding values
     * @param index Index of element
     * @param count Size of keys and values arrays (is equal)
     */
    private void createHeap(int[] keys, V[] values, int index, int count) {
        int smallest = index;
        int left = 2 * index + 1;
        int right = left + 1;

        if ((left < count) && (keys[left] < keys[smallest]))
            smallest = left;

        if ((right < count) && (keys[right] < keys[smallest]))
            smallest = right;

        if (smallest != index) {
            // Swapping of elements in keys and values arrays
            // from [smallest] to [index]
            swapElements(smallest, index, keys, values);

            // Recursively create the heap
            createHeap(keys, values, smallest, count);
        }
    }

    /**
     * Swaps elements present at indices @from and @to of keys and values arrays
     * @param from index of element to be swapped
     * @param to index of element to be swapped with
     * @param keys the keys array
     * @param values the values array
     */
    private void swapElements(int from, int to, int[] keys, V[] values) {
        int k = keys[from];
        V v = values[from];

        keys[from] = keys[to];
        values[from] = values[to];
        keys[to] = k;
        values[to] = v;
    }

    /**
     * Creates Heap Nodes for the root node
     * according to the provided keys and values arrays.
     * @param keys Heapified keys array
     * @param values Corresponding heapified values array
     */
    private void linkHeapNodes(int[] keys, V[] values) {

        Node<V>[] heapNodes = new Node[keys.length];

        for (int i = 0; i < keys.length; i++) {
            Node<V> current = new Node<>(keys[i], values[i]);
            heapNodes[i] = current;

            if(i != 0) {
                current.setParent(heapNodes[i/2]);

                if (i % 2 != 0)
                    heapNodes[i / 2].setLeft(current);
                else
                    heapNodes[i / 2].setRight(current);
            }
        }

        if (heapNodes.length > 0)
            root = heapNodes[0];
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

        // If size is 0
        if (size == 0) {
            root = new Node<>(key, value);
            size++;
            return;
        }

        Node<V> newNode = new Node<>(key, value);

        // [numNodesBottom] contains the number of nodes at the last level.
        // It starts from [size], since we have already dealt with the condition that size is 0.
        int numNodesBottom = size;

        // [numNodesLevel] contains the number of nodes at a particular level.
        // [numOfCompleteLevels] indicates the number of levels that are 'full'.
        int numNodesLevel, numOfCompleteLevels;

        for(numNodesLevel = 1, numOfCompleteLevels = 0;; numNodesLevel *= 2, numOfCompleteLevels++) {
            if (numNodesBottom - numNodesLevel < 0)
                break;
            numNodesBottom -= numNodesLevel;
        }

        Node<V> current = root;
        for(int bit = 1 << (numOfCompleteLevels - 1); bit != 1; bit >>= 1) {
            // If last bit is 1, it means the right node,
            // else it means the left node.
            if ((numNodesBottom & bit) == bit)
                current = (Node<V>) current.getRight();
            else
                current = (Node<V>) current.getLeft();
        }

        // If the last node has a left child, set new node as the right child.
        // Else, just set the left child as the new node.
        if (current.getLeft() != null)
            current.setRight(newNode);
        else
            current.setLeft(newNode);

        // Set new node's parent as the current node.
        newNode.setParent(current);

        // Increase size by 1.
        size++;

        Node<V> addedNode = newNode;

        // Traverse up the heap to fix the heap if its requirements are not met.
        while(hasParent(addedNode)){
            if (addedNode.getKey() < addedNode.getParent().getKey()) {
                swapNodes(addedNode, addedNode.getParent());
                addedNode = (Node<V>) addedNode.getParent();
            } else break;
        }
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
