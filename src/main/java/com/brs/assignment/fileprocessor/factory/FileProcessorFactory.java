package com.brs.assignment.fileprocessor.factory;


import com.brs.assignment.fileprocessor.AbstractGameFileProcessor;
import com.brs.assignment.fileprocessor.TextGameFileProcessor;
import com.brs.assignment.util.FileUtil;

/**
 * Factory class which will create and return various types of file processor based on the input file types.
 * For now only text file types are supported.
 */
public class FileProcessorFactory
{

    private static final String TEXT_FILE_EXTENSION = ".txt";

    /**
     * Gets the File Processor based on the type of input text file eg: txt, csv, .doc
     *
     * @param absolutePath of the file
     *                     
     * @return File Processor corresponds to the input file type.
     */
    public static AbstractGameFileProcessor getFileProcessor(String absolutePath)
    {
        if (FileUtil.checkFileEndsWith(absolutePath, TEXT_FILE_EXTENSION))
        {
            return new TextGameFileProcessor();
        }
        return null;
    }

}
