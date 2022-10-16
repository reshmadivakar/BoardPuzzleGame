package com.brs.assignment.components;

public class BoardSizePiece extends Board
{
    private PieceCoordinate coordinate;

    private BoardSizePiece parent;

    public BoardSizePiece(int[] sizeOfMatrix)
    {
        super(sizeOfMatrix);
    }

    public PieceCoordinate getCoordinate()
    {
        return coordinate;
    }

    public void setCoordinate(PieceCoordinate coordinate)
    {
        this.coordinate = coordinate;
    }

    public BoardSizePiece getParent()
    {
        return parent;
    }

    public void setParent(BoardSizePiece parent)
    {
        this.parent = parent;
    }


}
