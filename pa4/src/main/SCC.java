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
    int[][] weightMatrix, transposeMatrix;
    int numOfVertices;
    Boolean[] visited;

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

    /**
     * Depth First Search on our graph.
     * Can be used for transpose matrix as well, but MAKE SURE to have used the [setTranspose()] method first!!
     * @param source Vertex to visit
     * @param visited Array of Booleans to see if vertex has been visited
     * @param forTranspose Boolean to know if DFS should be performed on the transpose matrix or not.
     */
    void depthFirstSearch(int source, Boolean[] visited, boolean forTranspose) {
        visited[source] = true;

        int[] weights;

        if (!forTranspose)
            weights = weightMatrix[source];
        else
            weights = transposeMatrix[source];

        for (int dest = 0; dest < weights.length; dest++) {
            // Check if weight of edge is not 0, since it means that it's a valid neighbor of [source]
            if (weights[dest] != 0)
                if (!visited[dest])
                    depthFirstSearch(dest, visited, forTranspose);
        }
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
