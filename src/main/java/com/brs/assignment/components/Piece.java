package com.brs.assignment.components;
/**
 * Class to represent the game pieces. This will hold an int[] to represent the size of the pieces and two dimensional array to represent the
 * piece matrix.
 */
public class Piece
{
    /**
     * To hold the dimension of the piece ie num of rows and num of columns.
     */
    private int[] sizeOfMatrix;
    /**
     * To hold the board values.
     */
    private int[][] pieceMatrix;

    /**
     * Constructor
     *
     * @param sizeOfMatrix
     */
    public Piece(int[] sizeOfMatrix)
    {
        this.sizeOfMatrix = sizeOfMatrix;
        this.pieceMatrix = new int[sizeOfMatrix[0]][sizeOfMatrix[1]];
    }

    /**
     * API to get the piece matrix
     *
     * @return pieceMatrix
     */
    public int[][] getPieceMatrix()
    {
        return pieceMatrix;
    }

    /**
     * setter for pieceMatrix
     *
     * @param pieceMatrix
     */
    public void setPieceMatrix(int[][] pieceMatrix)
    {
        this.pieceMatrix = pieceMatrix;
    }

    /**
     * To print the piece values.
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
                sb.append(this.pieceMatrix[i][j] + " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}
