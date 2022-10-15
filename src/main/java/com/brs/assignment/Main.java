package com.brs.assignment;

import com.brs.assignment.exception.InvalidGamePropertyException;
import com.brs.assignment.fileprocessor.AbstractGameFileProcessor;
import com.brs.assignment.fileprocessor.factory.FileProcessorFactory;
import com.brs.assignment.game.GameProperties;
import com.brs.assignment.util.FileUtil;
import com.brs.assignment.util.TimeUtil;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.List;

/**
 * Main class to start the execution of game. This will provide various input files and invoke execution.
 */

public class Main
{
    /**
     * Path where input sample files are stored.
     */
    private static final String INPUT_SAMPLES_FOLDER_PATH = "src/main/resources/samples";
    /**
     * Constant for Logging
     */
    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    /**
     * Main method where the execution starts.
     *
     * @param args
     */
    public static void main(String[] args)
    {
        BasicConfigurator.configure();
        List<String> filePathsInFolder = FileUtil.getAllFilePathsInFolder(INPUT_SAMPLES_FOLDER_PATH);

        if (filePathsInFolder != null && !filePathsInFolder.isEmpty())
        {
            Collections.sort(filePathsInFolder);

            for (String fileName : filePathsInFolder)
            {
                long startTime = System.currentTimeMillis();

                LOGGER.debug("main(): processing input file  = " + fileName);

                AbstractGameFileProcessor fileProcessor = FileProcessorFactory.getFileProcessor(fileName);
                if (fileProcessor != null)
                {
                    GameProperties gameProperties = null;
                    try
                    {
                        gameProperties = fileProcessor.processFile(fileName);
                        LOGGER.debug("------------ GAME PROPERTIES READY ------------");
                        LOGGER.debug("depth = " + gameProperties.getDepth());
                        LOGGER.debug("board = " + gameProperties.getBoard());
                        LOGGER.debug("-----------------------------------------------");

                    } catch (InvalidGamePropertyException e)
                    {
                        LOGGER.error("Exception: " + e);
                    }

                }
                long endTime = System.currentTimeMillis();
                LOGGER.info("Time Taken to process = " + TimeUtil.convertMillisecondsToHHMMSSFormat(endTime - startTime));

            }
        }
        else
        {
            LOGGER.error(" There are no input files exist at path " + INPUT_SAMPLES_FOLDER_PATH);
        }
    }
}

