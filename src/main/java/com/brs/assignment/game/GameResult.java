package com.brs.assignment.game;

import com.brs.assignment.components.Piece;
import com.brs.assignment.components.PieceCoordinate;

import java.util.ArrayList;
import java.util.List;

public class GameResult
{
    private List<PieceCoordinate> pieceCoordinates = new ArrayList<>();
    private List<Piece> pieces = new ArrayList<>();

    public List<PieceCoordinate> getPieceCoordinates()
    {
        return pieceCoordinates;
    }

    public List<Piece> getPieces()
    {
        return pieces;
    }
}
