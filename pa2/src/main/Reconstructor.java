/*
* Name:
* Student ID #:
*/

/*
* Do NOT import any additional packages/classes.
* If you (un)intentionally use some additional packages/classes we did not
* provide, you may receive a 0 for the homework.
*/

/* Reconstructor of the Tree */
public final class Reconstructor implements IReconstructor {
    private TreeNode root;
    private int postOrderIndex;
    private Integer[] postOrderSeq, inOrderSeq;

    /**
     * Creating the constructor method
     */
    public Reconstructor() {
        root = new TreeNode();
    }

    @Override
    public ITreeNode reconstruct(Integer[] postOrder, Integer[] inOrder)
        throws IllegalStateException {
        /*
        * Function input: post-order sequence,in-order sequnce of a tree.
        *
        * Job:
        *  Reconstruct the original binary tree that gives the post-order sequence and in-order sequence.
        *  Return the root node of that tree.
        *
        *  If there is no such tree, raise an IllegalStateException.
        *  You do not have to specify its message.
        */
        if(postOrder.length != inOrder.length) throw new IllegalStateException();

        /// If the length is 1,
        // then return a TreeNode with just one node (which is the root)
        if(postOrder.length == 1){
            root.setVal(postOrder[0]);
            return root;
        }

        /// Save the sequences in the variables
        postOrderSeq = postOrder;
        inOrderSeq = inOrder;
        /// Set index as the end of the postOrder sequence
        postOrderIndex = postOrder.length - 1;

        /// Recursively solve for root node
        root = recursivelyReconstruct(0, postOrder.length - 1);

        /// If root is not null, then return the [root]
        if (root.val() != null) return root;

        /// Else, the root is null, therefore throw an [IllegalStateException]
        throw new IllegalStateException();
    }

    /**
     * Recursively Reconstruct the tree, starting from [root] node
     * @param inOrderStart index from where the corresponding inOrder sequence begins for a node
     * @param inOrderEnd index from where the corresponding inOrder sequence ends for a node
     * @return TreeNode which is the root node
     */
    private TreeNode recursivelyReconstruct(int inOrderStart, int inOrderEnd){
        /// Return empty TreeNode if [inOrderStart] > [inOrderEnd]
        if(inOrderStart > inOrderEnd) return new TreeNode();

        /// Create a new TreeNode from which recursion can begin
        TreeNode node = new TreeNode();

        /// Throw an [IllegalStateException] if [postOrderIndex] < 0
        if(postOrderIndex < 0) throw new IllegalStateException();

        /// Set value of node
        node.setVal(postOrderSeq[postOrderIndex]);
        /// Reduce the index
        postOrderIndex--;

        /// Return node if both are equal,
        // since this node has no children
        if(inOrderStart == inOrderEnd) return node;

        /// Get index of node,
        /// since it is a parent node
        int parentIndex = getParentIndex(postOrderSeq[postOrderIndex + 1]);

        /// Recursively solve for the right and left subtree
        TreeNode rightTree = recursivelyReconstruct(parentIndex + 1, inOrderEnd);
        if(rightTree.val() != null){
            rightTree.setParent(node);
            node.setRight(rightTree);
        }

        TreeNode leftTree = recursivelyReconstruct(inOrderStart, parentIndex - 1);
        if(leftTree.val() != null){
            leftTree.setParent(node);
            node.setLeft(leftTree);
        }

        /// Return [node]
        return node;
    }

    /**
     * Returns the index of the [value] in the In Order sequence
     * @param value index of which is to be returned
     * @return index of [value]
     */
    private int getParentIndex(int value) {
        for(int i = 0; i < inOrderSeq.length; i++)
            if(inOrderSeq[i] == value)
                return i;

        /// Technically impossible!
        return -1;
    }

}