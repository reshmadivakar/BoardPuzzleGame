package com.brs.assignment.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Util Class added for handling file operations.
 */
public class FileUtil
{

    /**
     * Returns the absolute paths of all files in this folder.
     *
     * @param path
     * @return List of all file names inside the folder.
     */
    public static List<String> getAllFilePathsInFolder(String path)
    {
        //result list
        List<String> files = new ArrayList<String>();
        File folder = new File(path);
        if (folder.exists())
        {
            File[] allFilesInFolder = folder.listFiles();

            if (allFilesInFolder != null)
            {
                for (File fileInFolder : allFilesInFolder)
                {
                    if (fileInFolder.isFile())
                    {
                        files.add(fileInFolder.getAbsolutePath());
                    }
                }
            }
        }
        return files;
    }

    /**
     * API to check if the file name (passed as argument 1) ends with the string (passed as argument 2)
     *
     * @param absolutePath path of the file
     * @param endString    end name to check
     * @return true if file name ends with passed argument else return false.
     */
    public static boolean checkFileEndsWith(String absolutePath, String endString)
    {
        if (absolutePath != null && !absolutePath.isEmpty() && endString != null && !endString.isEmpty())
        {
            if (absolutePath.endsWith(endString))
            {
                return true;

            }
        }
        return false;
    }
}
