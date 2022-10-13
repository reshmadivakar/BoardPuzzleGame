package com.brs.assignment.components;

/**
 * Class to represent the game board. This will hold an int[] to represent the size of the board and two dimensional array to represent the
 * board matrix.
 */
public class Board
{
    /**
     * To hold the dimension of the board ie num of rows and num of columns.
     */
    private int[] sizeOfMatrix;

    /**
     * To hold the board values.
     */
    private int[][] boardMatrix;

    /**
     * Constructor
     *
     * @param sizeOfMatrix
     */
    public Board(int[] sizeOfMatrix)
    {
        this.sizeOfMatrix = sizeOfMatrix;
        this.boardMatrix = new int[sizeOfMatrix[0]][sizeOfMatrix[1]];
    }

    /**
     * API to get the board matrix
     *
     * @return boardMatrix
     */
    public int[][] getBoardMatrix()
    {
        return boardMatrix;
    }

    /**
     * API to set the board matrix.
     *
     * @param boardMatrix
     */
    public void setBoardMatrix(int[][] boardMatrix)
    {
        this.boardMatrix = boardMatrix;
    }

    /**
     * To print the board values.
     *
     * @return
     */
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        for (int i = 0; i < sizeOfMatrix[0]; i++)
        {
            for (int j = 0; j < sizeOfMatrix[1]; j++)
            {
                sb.append(this.boardMatrix[i][j] + " ");
            }
            sb.append("\n");
        }
        sb.append("\n");
        return sb.toString();
    }

}