package com.brs.assignment.util;

/**
 * Util class added for handling array operations.
 */
public class ArrayUtil
{
    /**
     * API to perform array copy
     *
     * @param src source array
     * @return copied array
     */
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

    /**
     * API to check if all the elements of the two dimensional array are zeros
     *
     * @param boardMatrix
     * @return true if all are zero
     */
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

}
