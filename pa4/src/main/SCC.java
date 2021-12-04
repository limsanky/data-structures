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
        weightMatrix = G.matrix();
        numOfVertices = weightMatrix.length;

        visited = new Boolean[numOfVertices];
        for(int i = 0; i < numOfVertices; i++)
            visited[i] = false;

        // Perform DFS on the node [u], and check if [v] is a valid reachable vertex.
        depthFirstSearch(u, visited, false);

        // Returns true if [v] turns out to be a reachable vertex to vertex node [u]
        return visited[v];
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
        weightMatrix = G.matrix();
        numOfVertices = weightMatrix.length;

        // Custom Stack maintained by ArrayList
        ArrayList<Integer> stack = new ArrayList<>();
        int count = 0;

        /**
         * Tarjan's SSC Algorithm
         */
        int index = 0;

        int[] indices = new int[numOfVertices];
        int[] lowlinks = new int[numOfVertices];
        visited = new Boolean[numOfVertices];

        for(int i = 0; i < numOfVertices; i++) {
            visited[i] = false;
            indices[i] = -1;
        }

        for (int i = 0; i < numOfVertices; i++) {
            if (indices[i] == -1) {
                count = strongConnect(i, index, stack, indices, lowlinks, count);
            }
        }

        /**
         * Kosaraju's Algorithm
         */

//        // Create and set transpose of weight matrix
//        // i.e. we are "reversing" the graph
//        setTranspose();
//
//        // Create array of booleans for tracking whether a vertex has been visited or not
//        visited = new Boolean[numOfVertices];
//        Boolean[] visitedTransposed = new Boolean[numOfVertices];
//
//        for (int i = 0; i < numOfVertices; i++) {
//            visited[i] = false;
//            visitedTransposed[i] = false;
//        }
//
//        for (int i = 0; i < numOfVertices; i++)
//            if (!visited[i])
//                createDFSOrder(i, visited, stack);
//
//        // Processing vertices based on order defined by our stack
//        for(int i = numOfVertices - 1; i >= 0; i--) {
//            // Getting the last element in the "stack"
//            int lastElement = stack.remove(i);
//
//            // Increase count if vertex is not visited.
//            // Then do DFS on the vertex to see if it forms an SCC
//            if (!visitedTransposed[lastElement]) {
//                count++;
//                depthFirstSearch(lastElement, visitedTransposed, true);
//            }
//        }

        return count;
    }

    /**
     * Performs a single depth first search for the node and reports the strongly connected vertices of the subgraph.
     * Used for Tarjan's SSC Algorithm.
     * @param node Vertex to visit
     * @param index DFS discovery number
     * @param stack Custom Stack based on ArrayList
     * @param indices Array of DFS discovery numbers of all the vertices
     * @param lowlinks Array of all the vertices' smallest-known reachable vertex.
     * @param count Number of SCCs
     * @return count Number of SCCs
     */
    private int strongConnect(int node, int index, List<Integer> stack, int[] indices, int[] lowlinks, int count) {
        indices[node] = index;
        lowlinks[node] = index;
        stack.add(node);
        visited[node] = true;

        index++;

        int[] weights = weightMatrix[node];
        for (int neighbor = 0; neighbor < weights.length; neighbor++) {
            if (weights[neighbor] != 0) {
                if (indices[neighbor] == -1) {
                    count = strongConnect(neighbor, index, stack, indices, lowlinks, count);
                    lowlinks[node] = min(lowlinks[node], lowlinks[neighbor]);
                } else if (visited[neighbor]) {
                    lowlinks[node] = min(lowlinks[node], indices[neighbor]);
                }
            }
        }

        // Check if smallest-reachable vertex of [node] is the same as the index of [node].
        // If they are the same, then it means that the [node] is the "root" of the subgraph
        if (indices[node] == lowlinks[node]) {
            int neighbor = -1;

            do {
                neighbor = stack.remove(stack.size() - 1);
                visited[neighbor] = false;
            } while (node != neighbor);

            count++;
        }

        return count;
    }

    /**
     * Creating the order of vertices to be visited using DFS, and storing it in our custom stack.
     * Used for Kosaraju's Algorithm
     * @param node Vertex to visit
     * @param visited Array of Booleans to see if vertex has been visited
     * @param stack Custom Stack based on ArrayList
     */
    private void createDFSOrder(int node, Boolean[] visited, ArrayList<Integer> stack) {
        visited[node] = true;

        int[] weights = weightMatrix[node];

        // Do DFS and add node to the custom stack
        for (int dest = 0; dest < weights.length; dest++)
            if (weights[dest] != 0)
                if (!visited[dest])
                    createDFSOrder(dest, visited, stack);

        // Adding node to stack
        stack.add(node);
    }

    /**
     * Create and set the transpose matrix based on the weight matrix.
     * MAKE SURE weight matrix has been defined beforehand.
     * Used for Kosaraju's Algorithm
     */
    private void setTranspose() {
        transposeMatrix = new int[numOfVertices][numOfVertices];

        for (int i = 0; i < numOfVertices; i++)
            for (int j = 0; j < numOfVertices; j++)
                transposeMatrix[j][i] = weightMatrix[i][j];
    }

    /**
     * Returns the smallest number out of the two inputs, [x] and [y],
     * @param x First argument
     * @param y Second argument
     * @return The smallest number between the two input arguments
     */
    private int min(int x, int y) {
        if (x < y)
            return x;
        return y;
    }
}
