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
     * Constant to represent SPACE delimiter
     */
    private static final String SPACE_DELIMITER = " ";
    /**
     * Depth of the board
     */
    private int depth;

    /**
     * Board for the game
     */
    private Board board;

    private List<Piece> actualPieces;

    /**
     * Read and initialise the depth of the board from input text file line 1.
     *
     * @param depth
     * @throws InvalidGamePropertyException
     */
    public void initializeDepth(String depth) throws InvalidGamePropertyException, NumberFormatException
    {

        this.depth = Integer.parseInt(depth);

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
            System.out.println("boardlines " + Arrays.deepToString(boardLines));

            // check if all lines are of same length so that the board is proper
            boolean properBoard = Stream.of(boardLines).map(String::length).distinct().count() == 1 &&
                    // no need to handle as case of depth 4 will only need max '3'
                    Stream.of(boardLines).allMatch(s -> s.chars().allMatch(ch -> ch == '0' || ch == '1' || ch == '2' || ch == '3'));

            if (properBoard)
            {
                int numOfRows = boardLines.length;
                int numOfColumns = (int) boardLines[0].chars().count();

                LOGGER.debug("initializeBoard(): numberOfRows = " + numOfRows + " and numOfColumns = " + numOfColumns);

                //At least one column should be there.
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
                //set the board matrix structure
                gameBoard.setBoardMatrix(boardMatrix);
                this.board = gameBoard;

            }
            else
            {
                throw new InvalidGamePropertyException("invalid board structure, check input file line 2");
            }
        }
        //If board string is null or empty handle error scenario.
        else
        {
            throw new InvalidGamePropertyException("invalid string " + boardString + " is given in input text file for board, check input file line 2");
        }
    }

    /**
     * Method to initialise the pieces. The below method will read and parse the third line from input text file and split them into string array,
     * create Piece objects and store them in a list.
     *
     * @param piecesString third line from input file.
     */
    public void initializePieces(String piecesString) throws InvalidGamePropertyException
    {
        if (piecesString != null && !piecesString.isEmpty())
        {

            //Separate each pieces by splitting with space
            String[] eachPieceStringArray = piecesString.split(SPACE_DELIMITER);
            LOGGER.debug("initializePieces(): input text file string = " + Arrays.deepToString(eachPieceStringArray) + ", length = " + eachPieceStringArray.length);

            //At least one piece should be available
            if (eachPieceStringArray.length > 0)
            {
                // Create a list to hold the pieces.
                this.actualPieces = new ArrayList<>(eachPieceStringArray.length);
                for (int index = 0; index < eachPieceStringArray.length; index++)
                {
                    String piece = eachPieceStringArray[index];
                    if (piece != null && !piece.isEmpty())
                    {
                        String[] pieceLines = piece.split(COMMA_DELIMITER);

                        LOGGER.debug("\ninitializePieces():piece is = " + Arrays.deepToString(pieceLines));
                        // check if all lines are of same length so that the pieces are of proper size
                        boolean properPiece = Stream.of(pieceLines).map(x -> x.length()).distinct().count() == 1 &&
                                Stream.of(pieceLines).allMatch(s -> s.chars().allMatch(ch -> ch == 'X' || ch == '.'));

                        if (properPiece)
                        {
                            int numOfRows = pieceLines.length;
                            int numOfColumns = (int) pieceLines[0].chars().count();

                            Piece gamePiece = new Piece(new int[]{numOfRows, numOfColumns});
                            LOGGER.debug("piece matrix size is " + numOfRows + "x" + numOfColumns);

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
                            gamePiece.setIndex(index);
                            gamePiece.setPieceMatrix(pieceMatrix);
                            this.actualPieces.add(gamePiece);
                        }

                        else
                        {
                            throw new InvalidGamePropertyException("invalid Pieces given with format other than .X and . or different length individual pieces: check input text file line 3");
                        }
                    }
                    else
                    {
                        throw new InvalidGamePropertyException("Null/Empty individual Piece given : " + piece + " check input text file line 3");
                    }
                }
            }
            else
            {
                throw new InvalidGamePropertyException("Couldn't get one piece after splitting with space, check input text line 3");
            }

            if (eachPieceStringArray.length != this.actualPieces.size())
            {
                throw new InvalidGamePropertyException("Parsing error for pieces, Pieces string array length " + eachPieceStringArray.length + " and actual pieces size " + actualPieces.size());
            }
        }
        else
        {
            throw new InvalidGamePropertyException("Null or empty piece line in input file line 3");
        }
    }

    /**
     * Getter for depth of board.
     *
     * @return depth
     */
    public int getDepth()
    {
        return depth;
    }

    /**
     * Getter for board
     *
     * @return board.
     */
    public Board getBoard()
    {
        return board;
    }

    /**
     * List of pieces to place on the board.
     */
    public List<Piece> getActualPieces()
    {
        return actualPieces;
    }
}
