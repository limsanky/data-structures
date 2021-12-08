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
        matrix = G.matrix();
        numOfVertices = matrix.length;
        undirectedEdges = new int[numOfVertices][numOfVertices];
        minDistances = new int[numOfVertices];

        // Create the undirected graph edges from the input graph.
        for (int s = 0; s < numOfVertices; s++) {
            for (int i = s + 1; i < numOfVertices; i++) {
                int temp = addNumbers(matrix[s][i], matrix[i][s]);
//                int temp = matrix[s][i] + matrix[i][s];

                undirectedEdges[s][i] = temp;
                undirectedEdges[i][s] = temp;
            }
        }

        int[] minimumSpanningTree = getMinSpanTree();

        return calculateWeight(minimumSpanningTree);
    }

    /**
     * Calculates the weight of the minimum spanning tree which has been produced!
     * Returns -1 if the input tree array is of length 0.
     * @param minSpanTree Array of vertices to which constitute the minimum spanning tree
     * @return Weight of the minimum spanning tree; -1 if the length of the input array is 0
     */
    private int calculateWeight(int[] minSpanTree) {
        // Invalid tree, if there are no vertices
        // => return -1
        if (minSpanTree.length == 0)
            return -1;

        int weight = 0;

        // Calculate weights for the edges.
        // [source] and [dest] indices help us know the edge in the MST,
        // which can be accessed through [undirectedEdges].
        for (int source = 0; source < numOfVertices - 1; source++) {
            int dest = minSpanTree[source + 1];

            weight = weight + undirectedEdges[source + 1][dest];
        }

        // Returns the calculated weight.
        return weight;
    }

    /**
     * Get the Minimum Spanning Tree using Prim's Algorithm
     * @return Array with vertices of the Minimum Spanning Tree
     */
    private int[] getMinSpanTree() {
        int[] childTree = new int[numOfVertices];
        boolean[] inMinSpanTree = new boolean[numOfVertices];

        // Initialization
        for(int i = 1; i < numOfVertices; i++) {
            minDistances[i] = MAX;
            inMinSpanTree[i] = false;
        }

        inMinSpanTree[0] = false;
        minDistances[0] = 0;
        childTree[0] = 0;

        for (int count = 0; count < numOfVertices - 1; count++) {
            int minDistanceVertex = minDistanceIndex(inMinSpanTree);

            if (minDistanceVertex == -1)
                return new int[] {};

            inMinSpanTree[minDistanceVertex] = true;

            for (int adjVertex = 0; adjVertex < numOfVertices; adjVertex++) {
                int weight = undirectedEdges[minDistanceVertex][adjVertex];

                if (!inMinSpanTree[adjVertex] && weight != 0 && weight < minDistances[adjVertex]) {
                    childTree[adjVertex] = minDistanceVertex;
                    minDistances[adjVertex] = weight;
                }
            }
        }

        return childTree;
    }

    /**
     * Returns the index of the vertex NOT present in the MST, to which the distance is the smallest.
     * @param inMinSpanTree Array of booleans which inform if the vertex is present in the MST or not
     * @return Index of the vertex to which the distance is the smallest
     */
    private int minDistanceIndex(boolean[] inMinSpanTree) {
        int index = -1;
        int min = MAX;

        for (int i = 0; i < numOfVertices; i++)
            if (!inMinSpanTree[i] && minDistances[i] < min) {
                min = minDistances[i];
                index = i;
            }

        return index;
    }
}
