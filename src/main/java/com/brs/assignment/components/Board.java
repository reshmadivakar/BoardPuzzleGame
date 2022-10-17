package com.brs.assignment.components;

import java.util.Deque;

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

    private Deque<Piece> availablePieces;


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
     * Gets the size of the matrix
     *
     * @return size
     */
    public int[] getSizeOfMatrix()
    {
        return sizeOfMatrix;
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
        return sb.toString();
    }

    /**
     * Getter for available pieces
     *
     * @return availablePieces
     */
    public Deque<Piece> getAvailablePieces()
    {
        return availablePieces;
    }

    /**
     * Setter for available pieces.
     *
     * @param availablePieces
     */
    public void setAvailablePieces(Deque<Piece> availablePieces)
    {
        this.availablePieces = availablePieces;
    }

}
