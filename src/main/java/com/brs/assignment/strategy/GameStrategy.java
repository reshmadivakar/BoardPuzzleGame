package com.brs.assignment.strategy;


import com.brs.assignment.Main;
import com.brs.assignment.components.Board;
import com.brs.assignment.components.BoardSizePiece;
import com.brs.assignment.components.Piece;
import com.brs.assignment.components.PieceCoordinate;
import com.brs.assignment.game.GameProperties;
import com.brs.assignment.game.GameResult;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Abstract Game Strategy class with abstract method for executing game. Every implementation class should implement this API to solve game
 * according to the strategy.
 */
public abstract class GameStrategy
{
    /**
     * Constant for Logging
     */
    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    /**
     * execute the game.
     *
     * @param gameProperties
     * @return GameResult
     */
    public abstract GameResult executeStrategy(GameProperties gameProperties);

    /**
     * The below method will perform the following.
     * 1. For each piece, find out what are all the possible coordinates on board where it can be placed.
     * 2. Keep the list of coordinates inside the piece.
     * 3. Sort the list of pieces based on the number of movements possible. ie for 3*3 board if there are two pieces available with size 3*3 and 2*2. After sorting
     * the list will have 3*3 first followed by 2*2 piece. This is because for 3*3 piece can be placed in only one way on a 3*3 board. 2*2 piece can be
     * placed in 4 ways.
     *
     * @param gameProperties
     * @return List of Pieces to apply in order.
     */
    protected List<Piece> buildPieceCoordinates(GameProperties gameProperties)
    {
        //Generate all the possible board configurations for each pieces.

        List<Piece> actualPieces = gameProperties.getActualPieces();
        int numOfRowsInBoard = gameProperties.getBoard().getSizeOfMatrix()[0];
        int numOfColumnsInBoard = gameProperties.getBoard().getSizeOfMatrix()[1];

        for (Piece piece : actualPieces)
        {
            int numOfRowsInPiece = piece.getSizeOfMatrix()[0];
            int numOfColumnsInPiece = piece.getSizeOfMatrix()[1];
            int numOfHorizontalConfigurations = numOfColumnsInBoard - numOfColumnsInPiece + 1;
            int numOfVerticalConfigurations = numOfRowsInBoard - numOfRowsInPiece + 1;
            // get the available positions of the piece on the board
            for (int i = 0; i < numOfHorizontalConfigurations; i++)
            {
                for (int j = 0; j < numOfVerticalConfigurations; j++)
                {
                    piece.getPossibleCoordinates().add(new PieceCoordinate(i, j));
                }
            }
            LOGGER.debug("buildPieceCoordinates(): Coordinates for Piece "+piece+" is "+ Arrays.deepToString(piece.getPossibleCoordinates().toArray()));
        }

        // sort based on the available coordinates size ASC - means lowest available movements will be at the top
        Collections.sort(actualPieces);

        //number of levels where logic should be applied should be number of pieces
        LOGGER.debug("buildPieceCoordinates()::number of levels = " + actualPieces.size());
        actualPieces
                .stream()
                .map(e -> e.getPossibleCoordinates().size())
                .mapToDouble(Integer::longValue)
                .reduce((x, y) -> x * y)
                .ifPresent(s -> LOGGER.debug("ExecuteStrategy()::number of nodes = " + s));

        return actualPieces;
    }

    /**
     * Construct the board after applying piece.
     * @param board
     * @param pc
     * @param boardMatrix
     * @return
     */
    public BoardSizePiece constructBoardSizePiece(Board board, PieceCoordinate pc, int[][] boardMatrix)
    {
        BoardSizePiece bsp = new BoardSizePiece(board.getSizeOfMatrix());
        bsp.setBoardMatrix(boardMatrix);
        bsp.setCoordinate(pc);
        bsp.setAvailablePieces(new ArrayDeque<>(board.getAvailablePieces()));

        //We should consider parent child relationship after the first piece has been applied.
        if (board instanceof BoardSizePiece)
        {
            bsp.setParent((BoardSizePiece) board);
        }

        return bsp;
    }

}
