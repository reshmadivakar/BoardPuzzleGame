package com.brs.assignment.components;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to represent the game pieces. This will hold an int[] to represent the size of the pieces and two dimensional array to represent the
 * piece matrix.
 */
public class Piece implements Comparable<Piece>
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
     * To hold the list of coordinates where this piece can be placed
     */
    private List<PieceCoordinate> possibleCoordinates = new ArrayList<>();
    /**
     * To hold the index of the Piece in input file.
     */
    private int index;

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
     * Gets the size of matrix.
     *
     * @return sizeOfMatrix
     */
    public int[] getSizeOfMatrix()
    {
        return sizeOfMatrix;
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
                sb.append(this.pieceMatrix[i][j] + " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * To hold the list of coordinates where this piece can be placed
     */
    public List<PieceCoordinate> getPossibleCoordinates()
    {
        return possibleCoordinates;
    }


    @Override
    public int compareTo(Piece o)
    {
        return this.getPossibleCoordinates().size() < o.getPossibleCoordinates().size() ? -1 : 1;
    }

    /**
     * To hold the index of the Piece in input file.
     */
    public int getIndex()
    {
        return index;
    }

    /**
     * Setter for index which will be used to re order the results based on inputs.
     *
     * @param index
     */
    public void setIndex(int index)
    {
        this.index = index;
    }
}
