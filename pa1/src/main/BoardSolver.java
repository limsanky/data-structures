/*
 * Name: 
 * Student ID #: 
 */

/*
 * Do NOT import any additional packages/classes.
 * If you (un)intentionally use some additional packages/classes we did not
 * provide, you may receive a 0 for the homework.
 */

public final class BoardSolver implements IBoardSolver {
    /**
     * References:
     *      https://stackoverflow.com/questions/34469538/efficient-nonogram-solver
     *      https://github.com/Samjayyy/logicpuzzles/blob/master/nonogram/MainV1.java
     */
    
    /*
    Required Variables
     */
    private static int numRows, numCols;
    private static int[][] yConditions, xConditions;
    private static long[] grid, listOfAllPermutations;
    private static long[][] rowPermutations;
    private static int[][] position, blockSizeIndex;

    @Override
    public IBoard solve(IBoard board) {
        numRows = board.getHeight();
        numCols = board.getWidth();
        xConditions = new int[numRows][];
        yConditions = new int[numCols][];
        grid = new long[numRows];
        rowPermutations = new long[numRows][];

        for(int column = 0; column < numCols; column++) {
            int[] condition = board.getRowConstraints(column);

            if(condition.length == 0)
                yConditions[column] = new int[] {0};
            else
                yConditions[column] = condition;
        }

        for(int row = 0; row < numRows; row++) {
            int[] condition = board.getColumnConstraints(row);

            if(condition.length == 0)
                xConditions[row] = new int[] {0};
            else
                xConditions[row] = condition;
        }

        for(int r = 0; r < numRows; r++){
            listOfAllPermutations = new long[] {};
            int numOfSpaces = numCols - yConditions[r].length + 1;

            for(int i = 0; i < yConditions[r].length; i++)
                numOfSpaces -= yConditions[r][i];

            getPermutations(r, 0, numOfSpaces, 0, 0);

            if(listOfAllPermutations.length == 0)
                return null;

            rowPermutations[r] = new long[listOfAllPermutations.length];

            System.arraycopy(listOfAllPermutations, 0, rowPermutations[r], 0, listOfAllPermutations.length);
        }

        position = new int[numRows][numCols];
        blockSizeIndex = new int[numRows][numCols];

        if (depthFirstSearch(0)){
            Board solvedBoard = new Board(numCols, numRows, yConditions, xConditions);

            for(int r = 0; r < numRows; r++)
                for(int c = 0; c < numCols; c++)
                    if((BoardSolver.grid[r] & (1L << c)) != 0)
                        solvedBoard.fill(r, c);

            return solvedBoard;
        } else
            return null;
    }

    /**
     * Depth First Search Function which searches in a dfs way the perfect combination.
     * @param row integer which denotes the current row
     * @return True or False, depending on whether the conditions are valid
     */
    private static boolean depthFirstSearch(int row){
        if(row == numRows){
            for(int c = 0; c < numCols; c++) {
                if ((blockSizeIndex[numRows - 1][c] == xConditions[c].length) ||
                        ((position[numRows - 1][c] == xConditions[c][blockSizeIndex[numRows - 1][c]]) &&
                                (blockSizeIndex[numRows - 1][c] == xConditions[c].length - 1)))
                    continue;


                return false;
            }

            return true;
        }

        for(int i = 0; i < rowPermutations[row].length; i++){
            grid[row] = rowPermutations[row][i];

            if(columnUpdateFor(row) && depthFirstSearch(row + 1))
                return true;
        }

        return false;
    }

    /**
     * Update column for specified row
     * @param row integer which specifies a row
     * @return true or false, depending upon whether column update is valid
     */
    private static boolean columnUpdateFor(int row){
        if(row == 0) {
            for (int column = 0, colIndex = 1; column < numCols; column++, colIndex <<= 1) {
                if ((grid[0] & colIndex) == 0)
                    position[0][column] = 0;
                else
                    position[0][column] = 1;
            }

            return true;
        }

        for (int column = 0, colIndex = 1; column < numCols; column++, colIndex <<= 1) {
            position[row][column] = position[row - 1][column];
            blockSizeIndex[row][column] = blockSizeIndex[row - 1][column];

            if((grid[row] & colIndex) == 0){
                if(position[row - 1][column] > 0){
                    if(xConditions[column][blockSizeIndex[row - 1][column]] != position[row - 1][column])
                        return false;

                    blockSizeIndex[row][column]++;
                    position[row][column] = 0;
                }
            } else {
                if((position[row - 1][column] == 0) && (blockSizeIndex[row - 1][column] == xConditions[column].length))
                    return false;
                if(xConditions[column][blockSizeIndex[row - 1][column]] == position[row - 1][column])
                    return false;

                position[row][column]++;
            }
        }

         return true;
    }

    /**
     * Calculate Permutations for each block
     * @param row integer specifying particular row
     * @param currentBlock block
     * @param numOfSpaces number of spaces in row
     * @param permutation permutation for specified block
     * @param shift shifting for bit
     */
    private static void getPermutations(int row, int currentBlock, int numOfSpaces, long permutation, int shift){
        if(currentBlock == yConditions[row].length) {
            if((permutation & grid[row]) == grid[row]) {
                long[] newList = new long[listOfAllPermutations.length + 1];
                System.arraycopy(listOfAllPermutations, 0, newList, 0, listOfAllPermutations.length);

                newList[listOfAllPermutations.length] = permutation;
                listOfAllPermutations = newList;
            }

            return;
        }

        while(numOfSpaces >= 0){
            int condition = yConditions[row][currentBlock];
            long bits = (toBinary(condition) << shift);
            long newPermutation = permutation | bits;

            getPermutations(row, currentBlock + 1, numOfSpaces,
                    newPermutation, yConditions[row][currentBlock] + shift + 1);

            numOfSpaces--;
            shift++;
        }
    }

    /**
     * Convert decimal number into binary format
     * @param decimal integer number which is to be converted
     * @return long number which is the binary representation of the decimal
     */
    private static long toBinary(int decimal){
        return (1L << decimal) - 1;
    }
}
