/*
* Name:
* Student ID#:
*/
import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;
/*
* Do NOT use any external packages/classes.
* If you (un)intentionally use them we did not provide,
* you will get 0.
* Also do NOT use auto-import function on IDEs.
* If the import statements change, you will also get 0.
*/

public final class MST implements IMST {
    private final int MAX = Integer.MAX_VALUE;

    private int numOfVertices;
    private int[][] matrix;
    private int[][] undirectedEdges;
    private int[] minDistances;

    public MST() {
        /*
        * Constructor
        * This function is an initializer for this class.
        */
    }

    @Override
    public int shortestPath(IGraph G, int u, int v) {
        /**
         * Input:
         *  + G: directed graph
         *  + u: start vertex
         *  + v: end vertex
         *
         * Job:
         *  Return the weight of the path from u to v.
         *  If such path doesn't exist, return -1.
         */

        matrix = G.matrix();
        numOfVertices = matrix.length;

        // Use Dijkstra's Algorithm to process the shortest path from
        // source [u] to all the other vertices
        ArrayList<Integer> path = dijkstrasAlgorithm(u);

        if (minDistances[v] != MAX)
            return minDistances[v];

        return -1;
    }

    /**
     * Dijkstra's Algorithm is used to find the shortest path from the [source] to all the other vertices.
     * @param source Source Vertex
     * @return Shortest Path with the least weight from the [source] vertex
     */
    private ArrayList<Integer> dijkstrasAlgorithm(int source) {

        ArrayList<Integer> S = new ArrayList<>();
        ArrayList<Integer> Q = new ArrayList<>();
        minDistances = new int[numOfVertices];

        // Initialization
        for (int i = 0; i < numOfVertices; i++) {
            minDistances[i] = MAX;
            Q.add(i);
        }

        // Setting the [source] vertex's distance as 0.
        minDistances[source] = 0;

        while (!Q.isEmpty()) {
            int u = getIndexOfMinDistVertex(Q);
            Q.remove((Object)u);
            S.add(u);

            int[] weights = matrix[u];
            for (int dest = 0; dest < weights.length; dest++) {
                if (weights[dest] != 0) {
                    // Custom method is used to add the weight and distance to [dest], since
                    // Integer.MAX_VALUE can also be present as the distance
                    int temp = addNumbers(minDistances[u], matrix[u][dest]);
                    // Relaxation occurs if the weight + distance to [dest] is smaller than minDistance to [dest]
                    if (leftIsGreaterThanRight(minDistances[dest], temp))
                        minDistances[dest] = temp;

                }
            }
        }

        return S;
    }

    /**
     * Returns true if [left] is greater than [right].
     * It can handle Integer.MAX_VALUE as well!
     * @param left Left Number
     * @param right Right Number
     * @return True if left > right; false if otherwise
     */
    private boolean leftIsGreaterThanRight(int left, int right){
        if (left == right) return false;
        if (left == MAX) return true;
        if (right == MAX) return false;

        return left > right;
    }

    /**
     * Adds and returns the sum of two integers.
     * Returns Integer.MAX_VALUE if either of the inputs are Integer.MAX_VALUE.
     * @param x First integer
     * @param y Second integer
     * @return Sum of the two integers
     */
    private int addNumbers(int x, int y) {
        if (x == MAX || y == MAX)
            return MAX;

        return x + y;
    }

    /**
     * Returns the index of the vertex with the smallest distance from the [source]
     * @param queue ArrayList which contains the vertices
     * @return The index of the vertex to which the distance is the smallest from the [source]
     */
    private int getIndexOfMinDistVertex(ArrayList<Integer> queue) {
        int index = queue.get(0);
        int min = minDistances[index];

        // Search for the nearest vertex from the source
        for (int i = 1; i < queue.size(); i++) {
            if (minDistances[queue.get(i)] < min) {
                index = queue.get(i);
                min = minDistances[index];
            }
        }

        return index;
    }

    @Override
    public int findMST(IGraph G) {
        /**
         * Input:
         *  + G: directed graph
         * 
         * Job:
         *  Return the total weight of the MST in G'.
         *  If MST doesn't exist, return -1.
         */
        return -1;
    }
}
