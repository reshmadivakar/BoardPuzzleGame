package com.brs.assignment.fileprocessor;


import com.brs.assignment.exception.InvalidGamePropertyException;
import com.brs.assignment.game.GameProperties;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

/**
 * The processor class which handles reading the input files with .txt extension and extract the properties needed for the board game
 * and keep them in wrapper class.
 */
public class TextGameFileProcessor extends AbstractGameFileProcessor
{
    /**
     * Constant for Logging
     */
    private static final Logger LOGGER = LogManager.getLogger(TextGameFileProcessor.class);
    /**
     * number of lines required in text file.
     */
    private static final int INPUT_LINES = 3;

    /**
     * API to read and parse the input file to extract the properties needed for game.
     *
     * @param absoluteFilePath of the text file.
     * @return Wrapper to hold all the properties needs to be used for the game. Properties are depth, board to use and pieces to place on the board.
     */
    @Override
    public GameProperties processFile(String absoluteFilePath) throws InvalidGamePropertyException
    {

        List<String> inputList = Collections.emptyList();
        try
        {
            inputList = Files.readAllLines(Paths.get(absoluteFilePath), StandardCharsets.UTF_8);
        }
        catch (IOException e)
        {
            LOGGER.error("IOException occurred  in processFile() " + e);
        }
        catch (Exception e)
        {
            LOGGER.error("Exception occurred  in processFile() " + e);
        }
        //check if input file is having 3 entries before proceeding
        if (inputList.size() != INPUT_LINES)
        {
           throw new InvalidGamePropertyException("Input files doesn't contain 3 lines, it contain only "+inputList.size()+" lines");
        }

        GameProperties gameProperties = new GameProperties();

        //initialize the depth
        gameProperties.initializeDepth(inputList.get(0));

        //initialize the board
        gameProperties.initializeBoard(inputList.get(1));

        //initialize the list of pieces
        gameProperties.initializePieces(inputList.get(2));

        return gameProperties;

    }

}
