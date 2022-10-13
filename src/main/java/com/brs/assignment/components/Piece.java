package com.brs.assignment.components;

public class Piece
{
    private int[] sizeOfMatrix;

    private int[][] pieceMatrix;


    public Piece(int[] sizeOfMatrix)
    {
        this.sizeOfMatrix = sizeOfMatrix;
        this.pieceMatrix = new int[sizeOfMatrix[0]][sizeOfMatrix[1]];
    }


    public int[][] getPieceMatrix()
    {
        return pieceMatrix;
    }

    public void setPieceMatrix(int[][] pieceMatrix)
    {
        this.pieceMatrix = pieceMatrix;
    }

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
