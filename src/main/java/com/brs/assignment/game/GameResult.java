package com.brs.assignment.game;

import com.brs.assignment.components.Piece;
import com.brs.assignment.components.PieceCoordinate;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to wrap the list of pieces in last applied order and the successful coordinates.
 */
public class GameResult
{
    /**
     * List of successful coordinates
     */
    private List<PieceCoordinate> pieceCoordinates = new ArrayList<>();
    /**
     * Pieces list
     */
    private List<Piece> pieceList = new ArrayList<>();

    /**
     * Gets piece coordinates
     *
     * @return
     */
    public List<PieceCoordinate> getPieceCoordinates()
    {
        return pieceCoordinates;
    }

    /**
     * Gets Piece list
     *
     * @return
     */
    public List<Piece> getPieceList()
    {
        return pieceList;
    }

    /**
     * The result should indicate the coordinates of the pieces in order as given in input text file.
     * The below method will rearrange the pieces in same order as the input pieces.
     *
     * @return array of coordinates.
     */
    public int[][] getPieceCoordinatesInOrder()
    {
        int[][] pieceCoordinatesInIOrder = new int[pieceList.size()][];
        for (int i = 0; i < pieceList.size(); i++)
        {
            Piece piece = pieceList.get(i);
            int index = piece.getIndex();
            pieceCoordinatesInIOrder[index] = new int[]{pieceCoordinates.get(i).getX(), pieceCoordinates.get(i).getY()};

        }
        return pieceCoordinatesInIOrder;
    }
}
