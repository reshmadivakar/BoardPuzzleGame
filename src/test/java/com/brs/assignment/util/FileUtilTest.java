package com.brs.assignment.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class added for testing various file util APIs.
 */
class FileUtilTest
{

    private Path path1, path2, path3;
    private File file1, file2, file3;

    private String temFolderRootPath;

    @TempDir
    private Path tempDir;

    @BeforeEach
    public void setUp()
    {
        try
        {
            path1 = tempDir.resolve("testfile1.txt");
            path2 = tempDir.resolve("testfile2.csv");
            path3 = tempDir.resolve("testfile3.json");
        }
        catch (InvalidPathException ipe)
        {
            System.err.println("error creating temporary test file in " + this.getClass().getSimpleName());
        }

        file1 = path1.toFile();
        file2 = path2.toFile();
        file3 = path3.toFile();

        temFolderRootPath = file1.getParent();

    }


    @Test
    @DisplayName("Test if check file ends with extension")
    void checkFileEndsWith()
    {
        assertTrue(FileUtil.checkFileEndsWith(file1.getAbsolutePath(), ".txt"));
        assertTrue(FileUtil.checkFileEndsWith(file2.getAbsolutePath(), ".csv"));
        assertTrue(FileUtil.checkFileEndsWith(file3.getAbsolutePath(), ".json"));
    }
}