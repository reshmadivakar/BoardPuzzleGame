package com.brs.assignment.exception;

/**
 * Custom defined exception to be thrown when invalid game properties are entered in to the file.
 */
public class InvalidGamePropertyException extends Exception
{
    public InvalidGamePropertyException(String errorMessage)
    {
        super(errorMessage);
    }
}
