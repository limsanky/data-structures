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
    }

    @Override
    public void insertVertex() {
        /**
         * Input: None
         * 
         * Job:
         *  Extend the number of verticies and label it as the last number.
         */
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
    }

    @Override
    public int[][] matrix() {
        /**
         * Input: None
         * 
         * Job:
         *  Return the adjacency matrix.
         */
        return null;
    }

    @Override
    public int size() {
        /**
         * Input: None
         * 
         * Job:
         *  Return the size.
         */
        return -1;
    }
}