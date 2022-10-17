package com.brs.assignment.strategy;

import com.brs.assignment.components.Board;
import com.brs.assignment.components.BoardSizePiece;
import com.brs.assignment.components.Piece;
import com.brs.assignment.components.PieceCoordinate;
import com.brs.assignment.game.GameProperties;
import com.brs.assignment.game.GameResult;
import com.brs.assignment.util.ArrayUtil;

import java.util.*;

/**
 * Depth first strategy will find all the possible coordinates for each piece. Then it will sort the pieces based on the number of movements possible for each piece.
 * The piece with minimum number of coordinates will be applied on board first. If there are n number of coordinates possible for the board, then it will generate
 * n new boards. Then it will choose the next piece and apply each of them on this boards. This will continue until the leaf node is reached. After applying the last
 * piece coordinates whenever the board becomes zero, traversing will stop and return the successful combination.
 *
 */
public class DepthFirstCheckStrategy extends GameStrategy
{
    /**
     * Execute the game strategy in depth first order.
     *
     * @param gameProperties
     *
     * @return game result object having coordinates of each pieces to place on board.
     */
    public GameResult executeStrategy(GameProperties gameProperties)
    {
        //result object
        GameResult gameResult = new GameResult();

        //Gets the Piece to apply in order, The piece with less number of possibilities will always be on the top of the list.
        List<Piece> actualPieces = buildPieceCoordinates(gameProperties);

        Board startingBoard = gameProperties.getBoard();
        startingBoard.setAvailablePieces(new ArrayDeque<>(actualPieces));

        //Create the Stack to Use for DFS
        Deque<Board> boardStack = new ArrayDeque<>();
        //push the board first to stack
        boardStack.push(startingBoard);

        while (!boardStack.isEmpty())
        {
            Board board = boardStack.pop();

            //this means all pieces have been applied on board and this is the first solution leaf.
            if (board instanceof BoardSizePiece && board.getAvailablePieces() == null)
            {

                gameResult.getPieceCoordinates().add(((BoardSizePiece) board).getCoordinate());
                // traverse and get the parents and it coordinates
                BoardSizePiece boardSizePiece = ((BoardSizePiece) board).getParent();

                while (boardSizePiece != null)
                {
                    gameResult.getPieceCoordinates().add(boardSizePiece.getCoordinate());
                    boardSizePiece = boardSizePiece.getParent();
                }

                // storing in the reverse order so that the coordinate and piece matches
                Collections.reverse(actualPieces);
                gameResult.getPieceList().addAll(actualPieces);
                break;
            }


            List<BoardSizePiece> boardsAfterApplyingPiece = getBoardsAfterApplyingPiece(gameProperties.getDepth(), board);

            if (boardsAfterApplyingPiece != null && !boardsAfterApplyingPiece.isEmpty())
            {
                for (BoardSizePiece bsp : boardsAfterApplyingPiece)
                {
                    boardStack.push(bsp);
                }
            }
        }
        return gameResult;

    }

    /**
     * The below method will apply the piece in the base board passed to it in all the possible coordinates and construct the new list of boards.
     * After applying last piece(when it reach leaf node) it will check if all bits are zero in board. If so it will return and won't push to stack.
     *
     * @param depth
     * @param board
     * @return List of Board Size Piece after applying pieces.
     */
    private List<BoardSizePiece> getBoardsAfterApplyingPiece(int depth, Board board)
    {
        if (board.getAvailablePieces() == null || board.getAvailablePieces().isEmpty())
        {
            return null;
        }

        Piece pieceToApply = board.getAvailablePieces().pop();
        List<PieceCoordinate> pieceCoordinates = pieceToApply.getPossibleCoordinates();
        List<BoardSizePiece> pieceAppliedBoards = new ArrayList<>(pieceCoordinates.size());
        for (PieceCoordinate pieceCoordinate : pieceCoordinates)
        {

            int[][] boardMatrix = ArrayUtil.copyArray(board.getBoardMatrix());
            int[][] pieceMatrix = pieceToApply.getPieceMatrix();

            for (int i = 0; i < pieceMatrix.length; i++)
            {
                for (int j = 0; j < pieceMatrix[0].length; j++)
                {
                    boardMatrix[i + pieceCoordinate.getY()][j + pieceCoordinate.getX()] += pieceMatrix[i][j];
                    if (boardMatrix[i + pieceCoordinate.getY()][j + pieceCoordinate.getX()] == depth)
                    {
                        boardMatrix[i + pieceCoordinate.getY()][j + pieceCoordinate.getX()] = 0;
                    }
                }
            }

            // check if we applied the last piece, then we can check for clean board here itself
            // without pushing this into the stack

            if (board.getAvailablePieces().isEmpty())
            {
                if (ArrayUtil.allAreZeros(boardMatrix))
                {
                    BoardSizePiece bsp = new BoardSizePiece(board.getSizeOfMatrix());
                    bsp.setBoardMatrix(boardMatrix);
                    bsp.setCoordinate(pieceCoordinate);

                    if (board instanceof BoardSizePiece)
                    {
                        bsp.setParent((BoardSizePiece) board);
                    }

                    pieceAppliedBoards.add(bsp);
                    return pieceAppliedBoards;
                }
            }
            else
            {
                BoardSizePiece boardSizePiece = constructBoardSizePiece(board, pieceCoordinate, boardMatrix);
                pieceAppliedBoards.add(boardSizePiece);
            }
        }
        return pieceAppliedBoards;
    }
}
