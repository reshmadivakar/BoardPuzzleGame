package com.brs.assignment.components;

/**
 * Class to represent a board created after applying a piece. This will also store the details of the coordinates
 * where the piece has been applied and previous board piece.
 */
public class BoardSizePiece extends Board
{
    /**
     * to store the piece coordinate
     */
    private PieceCoordinate coordinate;
    /**
     * Parent board where the piece has been applied
     */
    private BoardSizePiece parent;

    /**
     * Constructor
     *
     * @param sizeOfMatrix
     */
    public BoardSizePiece(int[] sizeOfMatrix)
    {
        super(sizeOfMatrix);
    }

    /**
     * Getter for coordinate.
     *
     * @return coordinate
     */
    public PieceCoordinate getCoordinate()
    {
        return coordinate;
    }

    /**
     * Setter for coordinate
     *
     * @param coordinate
     */
    public void setCoordinate(PieceCoordinate coordinate)
    {
        this.coordinate = coordinate;
    }

    /**
     * Getter for parent board piece
     * @return parent
     */
    public BoardSizePiece getParent()
    {
        return parent;
    }

    /**
     * Setter for parent board piece.
     * @param parent
     */
    public void setParent(BoardSizePiece parent)
    {
        this.parent = parent;
    }


}
