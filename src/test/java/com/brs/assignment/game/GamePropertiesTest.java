package com.brs.assignment.game;

import com.brs.assignment.components.Board;
import com.brs.assignment.components.Piece;
import com.brs.assignment.exception.InvalidGamePropertyException;
import org.apache.log4j.BasicConfigurator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class added for testing parsing and extracting of input file with various samples.
 */
class GamePropertiesTest
{

    @Test
    @DisplayName("Test Valid depth")
    void testInitializeValidDepth() throws InvalidGamePropertyException
    {
        BasicConfigurator.configure();
        GameProperties gameProperties = new GameProperties();
        gameProperties.initializeDepth("2");
        assertEquals(2, gameProperties.getDepth());
        gameProperties.initializeDepth("4");
        assertEquals(4, gameProperties.getDepth());
    }

    @Test
    @DisplayName("Test Invalid depth")
    void testInitializeInvalidDepth()
    {
        GameProperties gameProperties = new GameProperties();
        Throwable exception1 = assertThrows(InvalidGamePropertyException.class, () -> gameProperties.initializeDepth("5"));
        assertEquals("invalid depth = 5 provided in input file. Should be between 2 and 4 ", exception1.getMessage());
        Throwable exception2 = assertThrows(InvalidGamePropertyException.class, () -> gameProperties.initializeDepth("1"));
        assertEquals("invalid depth = 1 provided in input file. Should be between 2 and 4 ", exception2.getMessage());

    }

    @Test
    @DisplayName("Test depth for non integers")
    void testInitializeDepthNonInteger()
    {
        GameProperties gameProperties = new GameProperties();
        assertThrows(NumberFormatException.class, () -> gameProperties.initializeDepth(""));
        assertThrows(NumberFormatException.class, () -> gameProperties.initializeDepth("a"));
    }

    @Test
    @DisplayName("Test valid board with size 3 * 3")
    void testInitializeBoardValidDimension() throws InvalidGamePropertyException
    {
        String boardString = "111,111,111";
        GameProperties gameProperties = new GameProperties();
        gameProperties.initializeBoard(boardString);
        Board actualBoard = gameProperties.getBoard();
        assertTrue(actualBoard != null);
        int[][] boardMatrix = actualBoard.getBoardMatrix();
        assertTrue(boardMatrix != null);
        assertEquals(actualBoard.getSizeOfMatrix()[0], 3);
        assertEquals(actualBoard.getSizeOfMatrix()[1], 3);
        for (int i = 0; i < actualBoard.getSizeOfMatrix()[0]; i++)
        {
            for (int j = 0; j < actualBoard.getSizeOfMatrix()[1]; j++)
            {
                assertEquals(1, boardMatrix[i][j]);
            }
        }
    }

    @Test
    @DisplayName("Test board for empty or null text input string")
    void testInitializeBoardEmptyNullInputString()
    {
        //Test for empty
        String boardString1 = "";
        GameProperties gameProperties = new GameProperties();
        Throwable exception1 = assertThrows(InvalidGamePropertyException.class, () -> gameProperties.initializeBoard(boardString1));
        assertEquals("invalid string " + boardString1 + " is given in input text file for board, check input file line 2", exception1.getMessage());
        Board actualBoard = gameProperties.getBoard();
        assertTrue(actualBoard == null);
        //Test for null
        String boardString2 = null;
        Throwable exception2 = assertThrows(InvalidGamePropertyException.class, () -> gameProperties.initializeBoard(boardString2));
        assertEquals("invalid string " + boardString2 + " is given in input text file for board, check input file line 2", exception2.getMessage());
        actualBoard = gameProperties.getBoard();
        assertTrue(actualBoard == null);

    }

    @Test
    @DisplayName("Test board for num of bits different in rows eg: 01,0,1")
    void testInitializeBoardDifferentNumBits()
    {
        //Test1
        String boardString1 = "01,0,1";
        GameProperties gameProperties = new GameProperties();
        Throwable exception1 = assertThrows(InvalidGamePropertyException.class, () -> gameProperties.initializeBoard(boardString1));
        assertEquals("invalid board structure, check input file line 2", exception1.getMessage());
        Board actualBoard = gameProperties.getBoard();
        assertTrue(actualBoard == null);

        //Test2
        String boardString2 = "110,,1000";
        Throwable exception2 = assertThrows(InvalidGamePropertyException.class, () -> gameProperties.initializeBoard(boardString2));
        assertEquals("invalid board structure, check input file line 2", exception2.getMessage());
        actualBoard = gameProperties.getBoard();
        assertTrue(actualBoard == null);

    }

    @Test
    @DisplayName("Test board input non numeric characters")
    void testInitializeBoardForNoRowsOrColumns()
    {
        String boardString1 = ",";
        GameProperties gameProperties = new GameProperties();
        Throwable exception1 = assertThrows(InvalidGamePropertyException.class, () -> gameProperties.initializeBoard(boardString1));
        assertEquals("invalid board structure, check input file line 2", exception1.getMessage());

        String boardString2 = ",,,";
        Throwable exception2 = assertThrows(InvalidGamePropertyException.class, () -> gameProperties.initializeBoard(boardString2));
        assertEquals("invalid board structure, check input file line 2", exception2.getMessage());
    }

    @Test
    @DisplayName("Test Board for non integers")
    void testInitializeBoardNonInteger()
    {
        GameProperties gameProperties = new GameProperties();
        assertThrows(InvalidGamePropertyException.class, () -> gameProperties.initializeBoard("A, B, C"));
        assertThrows(InvalidGamePropertyException.class, () -> gameProperties.initializeBoard("1, 0, C"));
        assertThrows(InvalidGamePropertyException.class, () -> gameProperties.initializeBoard("11, 0A, BC"));
    }

    @Test
    @DisplayName("Test piece input when input line is empty or null")
    void testInitializePiecesForEmptyNullInputString()
    {
        //Test for empty
        String pieceString1 = "";
        GameProperties gameProperties = new GameProperties();
        Throwable exception1 = assertThrows(InvalidGamePropertyException.class, () -> gameProperties.initializePieces(pieceString1));
        assertEquals("Null or empty piece line in input file line 3", exception1.getMessage());
        assertTrue(gameProperties.getActualPieces() == null);
        //Test for null
        String pieceString2 = null;
        Throwable exception2 = assertThrows(InvalidGamePropertyException.class, () -> gameProperties.initializePieces(pieceString2));
        assertEquals("Null or empty piece line in input file line 3", exception2.getMessage());
        assertTrue(gameProperties.getActualPieces() == null);
    }

    @Test
    @DisplayName("Test piece input for a valid string with 3 pieces")
    void testInitializePiecesForValidString() throws InvalidGamePropertyException
    {
        String pieceString = "XX,.. X. ...,X.X,...";
        GameProperties gameProperties = new GameProperties();
        gameProperties.initializePieces(pieceString);
        List<Piece> pieceList = gameProperties.getActualPieces();
        assertTrue(pieceList != null);
        assertTrue(pieceList.size() == 3);
        Piece piece = pieceList.get(0);
        assertEquals(1, piece.getPieceMatrix()[0][0]);
        assertEquals(1, piece.getPieceMatrix()[0][1]);
        assertEquals(0, piece.getPieceMatrix()[1][0]);
        assertEquals(0, piece.getPieceMatrix()[1][1]);

        piece = pieceList.get(1);
        assertEquals(1, piece.getPieceMatrix()[0][0]);
        assertEquals(0, piece.getPieceMatrix()[0][1]);

        piece = pieceList.get(2);
        assertEquals(0, piece.getPieceMatrix()[0][0]);
        assertEquals(0, piece.getPieceMatrix()[0][1]);
        assertEquals(0, piece.getPieceMatrix()[0][2]);
        assertEquals(1, piece.getPieceMatrix()[1][0]);
        assertEquals(0, piece.getPieceMatrix()[1][1]);
        assertEquals(1, piece.getPieceMatrix()[1][2]);
        assertEquals(0, piece.getPieceMatrix()[2][0]);
        assertEquals(0, piece.getPieceMatrix()[2][1]);
        assertEquals(0, piece.getPieceMatrix()[2][2]);

    }

    @Test
    @DisplayName("test pieces for a single space piece")
    void testInitializePiecesForZeroPiece()
    {
        String pieceString = " ";
        GameProperties gameProperties = new GameProperties();
        Throwable exception = assertThrows(InvalidGamePropertyException.class, () -> gameProperties.initializePieces(pieceString));
        assertEquals("Couldn't get one piece after splitting with space, check input text line 3", exception.getMessage());

        List<Piece> pieceList = gameProperties.getActualPieces();
        assertTrue(pieceList == null);

    }

    @Test
    @DisplayName("test pieces when empty or null individual pieces available")
    void testInitializePiecesForEmptyOrNullPieces()
    {
        String pieceString = "  XX,..  ";
        GameProperties gameProperties = new GameProperties();
        Throwable exception = assertThrows(InvalidGamePropertyException.class, () -> gameProperties.initializePieces(pieceString));
        assertEquals("Null/Empty individual Piece given :  check input text file line 3", exception.getMessage());

    }

    @Test
    @DisplayName("test pieces when pieces contain characters other than X and .")
    void testInitializePiecesForInvalidCharacters()
    {
        String pieceString = "XX AA,BB ";
        GameProperties gameProperties = new GameProperties();
        Throwable exception = assertThrows(InvalidGamePropertyException.class, () -> gameProperties.initializePieces(pieceString));
        assertEquals("invalid Pieces given with format other than .X and . or different length individual pieces: check input text file line 3", exception.getMessage());
    }

    @Test
    @DisplayName("test pieces when pieces number of characters in rows are different")
    void testInitializePiecesWhenNumOfColumnsDiffer()
    {
        String pieceString = "XX,. ..,X.X";
        GameProperties gameProperties = new GameProperties();
        Throwable exception = assertThrows(InvalidGamePropertyException.class, () -> gameProperties.initializePieces(pieceString));
        assertEquals("invalid Pieces given with format other than .X and . or different length individual pieces: check input text file line 3", exception.getMessage());
    }
}