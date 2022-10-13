package com.brs.assignment.game;

import com.brs.assignment.components.Board;
import com.brs.assignment.components.Piece;
import com.brs.assignment.exception.InvalidGamePropertyException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Class to hold the input properties required for the game which are depth, board and list of pieces.
 */
public class GameProperties
{
    /**
     * Constant for Logging
     */
    private static final Logger LOGGER = LogManager.getLogger(GameProperties.class);
    /**
     * Constants to indicate minimum and maximum depth values.
     */
    private static final int MIN_DEPTH = 2;
    private static final int MAX_DEPTH = 4;
    /**
     * Constant to represent comma delimiter.
     */
    private static final String COMMA_DELIMITER = ",";
    /**
     * Min rows required to start the game.
     */
    private static final int MIN_DIMENSION_REQUIRED = 1;
    /**
     * Depth of the board
     */
    private int depth;

    /**
     * Board for the game
     */
    private Board board;

    /**
     * List of pieces to place on the board.
     */
    private List<Piece> actualPieces;

    /**
     * Read and initialise the depth of the board from input text file line 1.
     *
     * @param depth
     * @throws InvalidGamePropertyException
     */
    public void initializeDepth(String depth) throws InvalidGamePropertyException
    {
        try
        {
            this.depth = Integer.parseInt(depth);
        } catch (NumberFormatException exception)
        {
            LOGGER.error("Exception in initializeDepth() " + exception);
        }
        LOGGER.debug("initializeDepth(): board depth = " + depth);

        if (this.depth < MIN_DEPTH || this.depth > MAX_DEPTH)
        {
            throw new InvalidGamePropertyException("invalid depth = " + depth + " provided in input file. Should be between 2 and 4 ");
        }
    }

    /**
     * Method to initialize the board. The method will read second line of input text line and construct a two dimensional matrix to hold the initial board.
     *
     * @param boardString
     */

    public void initializeBoard(String boardString) throws InvalidGamePropertyException
    {
        if (boardString != null && !boardString.isEmpty())
        {
            String[] boardLines = boardString.split(COMMA_DELIMITER);

            // check if all lines are of same length so that the board is proper
            boolean properBoard = Stream.of(boardLines).map(String::length).distinct().count() == 1;

            if (properBoard)
            {
                int numOfRows = boardLines.length;
                int numOfColumns = (int) boardLines[0].chars().count();

                LOGGER.debug("initializeBoard(): numberOfRows = " + numOfRows+" and numOfColumns = "+numOfColumns);

                //At least one row should be there on the board.
                if (numOfRows >= MIN_DIMENSION_REQUIRED && numOfColumns >= MIN_DIMENSION_REQUIRED)
                {
                    Board gameBoard = new Board(new int[]{numOfRows, numOfColumns});

                    int[][] boardMatrix = gameBoard.getBoardMatrix();

                    for (int i = 0; i < boardLines.length; i++)
                    {

                        char[] boardBits = boardLines[i].toCharArray();
                        for (int j = 0; j < boardBits.length; j++)
                        {
                            boardMatrix[i][j] = Character.getNumericValue(boardBits[j]);
                        }
                    }

                    gameBoard.setBoardMatrix(boardMatrix);
                    this.board = gameBoard;
                }
                //At least one row should be there.
                else
                {
                    throw new InvalidGamePropertyException("invalid board matrix size numOfRows: "+numOfRows+" and numOfColumns: "+numOfColumns);
                }
            }
            else
            {
                throw new InvalidGamePropertyException("invalid string "+boardString+" is given in input text file for board");
            }
        }
        //If board string is null or empty handle error scenario.
        else
        {
            throw new InvalidGamePropertyException("invalid string "+boardString+" is given in input text file for board");
        }
    }

    /**
     * Method to
     * @param piecesString
     */
    public void initializePieces(String piecesString)
    {
        if (piecesString != null && !piecesString.isEmpty())
        {

            //Separate each pieces by splitting with space
            String[] eachPieceString = piecesString.split(" ");
            System.out.println("eachPieceString = " + Arrays.deepToString(eachPieceString) + ", length = " + eachPieceString.length);

            // Create a list to hold the pieces.
            this.actualPieces = new ArrayList<>(eachPieceString.length);

            for (String piece : eachPieceString)
            {
                if (piece != null && !piece.isEmpty())
                {
                    String[] pieceLines = piece.split(",");

                    System.out.println("\npieceLines = " + Arrays.deepToString(pieceLines));
                    // check if all lines are of same length so that the pieces are of proper size
                    boolean properPiece = Stream.of(pieceLines).map(String::length).distinct().count() == 1 &&
                            Stream.of(pieceLines).allMatch(s -> s.chars().allMatch(ch -> ch == 'X' || ch == '.'));

                    if (properPiece)
                    {

                        int numOfRows = pieceLines.length;
                        int numOfColumns = (int) pieceLines[0].chars().count();

                        //valid board size
                        Piece gamePiece = new Piece(new int[]{numOfRows, numOfColumns});
                        System.out.println("piece matrix size is " + numOfRows + "x" + numOfColumns);

                        int[][] pieceMatrix = gamePiece.getPieceMatrix();

                        for (int i = 0; i < pieceLines.length; i++)
                        {
                            char[] pieceBits = pieceLines[i].toCharArray();
                            int digit = 0;
                            for (int j = 0; j < pieceBits.length; j++)
                            {
                                if (pieceBits[j] == 'X')
                                {
                                    digit = 1;
                                }
                                else if (pieceBits[j] == '.')
                                {
                                    digit = 0;
                                }

                                pieceMatrix[i][j] = digit;
                            }
                        }

                        gamePiece.setPieceMatrix(pieceMatrix);

                        this.actualPieces.add(gamePiece);

                    }

                    else
                    {
                        System.out.println("Not proper piece");
                        // throws board error exception
                    }
                }
                else
                {
                    // throws board error exception
                }
            }

            if (eachPieceString.length != this.actualPieces.size())
            {
                System.out.println("Invalid Pieces Found");
                // throw invalid pieces exception
            }
            else
            {
                // do nothing
            }
        }
    }


    public int getDepth()
    {
        return depth;
    }

    public Board getBoard()
    {
        return board;
    }
}
