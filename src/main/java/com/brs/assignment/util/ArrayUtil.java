package com.brs.assignment.util;

public class ArrayUtil
{
    public static int[][] copyArray(int[][] src)
    {
        int[][] copyIntArr = new int[src.length][];
        for (int i = 0; i < src.length; i++)
        {
            int[] aMatrix = src[i];
            int aLength = aMatrix.length;
            copyIntArr[i] = new int[aLength];
            System.arraycopy(aMatrix, 0, copyIntArr[i], 0, aLength);
        }
        return copyIntArr;
    }


    public static boolean allAreZeros(int[][] boardMatrix)
    {
        for (int i = 0; i < boardMatrix.length; i++)
        {
            for (int j = 0; j < boardMatrix[0].length; j++)
            {
                if (boardMatrix[i][j] != 0)
                {
                    return false;
                }
            }
        }
        return true;
    }

    public static int diffBetweenMatrix(int[][] a, int[][] b)
    {
        int diff = 0;
        if(a.length == b.length && a[0].length == b[0].length)
        {
            for (int i = 0; i < a.length; i++)
            {
                for (int j = 0; j < a[0].length; j++)
                {
                    if (a[i][j] != b[i][j])
                    {
                        diff += 1;
                    }
                }
            }
        }
        return diff;
    }
}
