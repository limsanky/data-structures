/*
* Name:
* Student ID#:
*/

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
/*
* Do NOT use any external packages/classes.
* If you (un)intentionally use them we did not provide,
* you will get 0.
* Also do NOT use auto-import function on IDEs.
* If the import statements change, you will also get 0.
*/

public final class Graph implements IGraph {
    private int size;
    private int[][] matrix;

    public Graph(String filename) {
        /*
         * Constructor
         * This function is an initializer for this class.
         */
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));

            // Get first line
            String line = br.readLine();

            // Split line wrt to the space
            String[] splitResult = line.split(" ");

            // Set the [matrix] and [size] variables
            size = Integer.parseInt(splitResult[0]);
            matrix = new int[size][size];

            // Get next line
            line = br.readLine();

            int source = 0, dest = 0, weight = 0;
            while (line != null) {
                splitResult = line.split(" ");

                // Get [source], [dest], and [weight] for the edge
                source = Integer.parseInt(splitResult[0]);
                dest = Integer.parseInt(splitResult[1]);
                weight = Integer.parseInt(splitResult[2]);

                // Set weight of edge
                matrix[source][dest] = weight;

                line = br.readLine();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void insertVertex() {
        /**
         * Input: None
         *
         * Job:
         *  Extend the number of verticies and label it as the last number.
         */

        // Increase number of vertices by 1
        size++;

        // Create and write to new matrix with the new vertex
        int[][] newMatrix = new int[size][size];

        for (int i = 0; i < matrix.length; i++)
            System.arraycopy(matrix[i], 0, newMatrix[i], 0, matrix[i].length);

        // Replace old matrix with the new matrix
        matrix = newMatrix;
    }

    @Override
    public void deleteVertex(int n) {
        /**
         * Input:
         *  + n: the node to delete
         * 
         * Job:
         *  Delete the node n and its connections to other nodes.
         */
    }

    @Override
    public void insertEdge(int u, int v, int w) {
        /**
         * Input:
         *  + u: start vertex
         *  + v: end vertex
         *  + w: weight of the edge
         *
         * Job:
         *  Insert an edge from u to v with weight w.
         */
        matrix[u][v] = w;
    }

    @Override
    public void deleteEdge(int u, int v) {
        /**
         * Input:
         *  + u: start vertex
         *  + v: end vertex
         *
         * Job:
         *  Delete an edge from u to v.
         */
        if (u >= size || v >= size)
            return;

        matrix[u][v] = 0;
    }

    @Override
    public int[][] matrix() {
        /**
         * Input: None
         *
         * Job:
         *  Return the adjacency matrix.
         */
        return matrix;
    }

    @Override
    public int size() {
        /**
         * Input: None
         *
         * Job:
         *  Return the size.
         */
        return size;
    }
}