/*
* Name:
* Student ID#:
*/
import java.util.List;
import java.util.ArrayList;
/*
* Do NOT use any external packages/classes.
* If you (un)intentionally use them we did not provide,
* you will get 0.
* Also do NOT use auto-import function on IDEs.
* If the import statements change, you will also get 0.
*/

public final class SCC implements ISCC {
    public SCC() {
        /*
        * Constructor
        * This function is an initializer for this class.
        */
    }

    @Override
    public boolean path(IGraph G, int u, int v) {
        /**
         * Input:
         *  + G: directed graph
         *  + u: start vertex
         *  + v: end vertex
         * 
         * Job:
         *  Return whether there is a path from u to v or not.
         */
        return false;
    }

    @Override
    public int connectivity(IGraph G) {
        /**
         * Input:
         *  + G: directed graph
         * 
         * Job:
         *  Find the number of strongly connected components in G.
         */
        return -1;
    }
}
