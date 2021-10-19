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
    /*
    * Use some variables for your implementation.
    */
    public Reconstructor() {
        /*
        * Constructor
        * This function is an initializer for this class.
        */
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
        return new TreeNode<Integer>();
    }
}