package com.brs.assignment.fileprocessor;

import com.brs.assignment.exception.InvalidGamePropertyException;
import com.brs.assignment.game.GameProperties;

/**
 * Abstract Input file processor class with abstract API for processing different kinds of input files.
 * Each processor needs to handle the processing of file based on the input file format(eg:.txt,.csv etc)
 */
public abstract class AbstractGameFileProcessor
{
    /**
     * API to read and parse the input file to extract the properties needed for game.
     *
     * @param absoluteFilePath
     * @return Wrapper to hold all the properties needs to be used for the game. Properties are depth, board to use and pieces to place on the board.
     */
    public abstract GameProperties processFile(String absoluteFilePath) throws InvalidGamePropertyException;
}
