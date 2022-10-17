package com.brs.assignment.components;

/**
 * Class to represent a coordinate with x and y values.
 */
public class PieceCoordinate
{
    /**
     * x coordinate value
     */
    private int x;

    /**
     * y coordinate value
     */
    private int y;

    /**
     * constructor
     *
     * @param x
     * @param y
     */
    public PieceCoordinate(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * Getter method for x coordinate
     *
     * @return x
     */
    public int getX()
    {
        return x;
    }

    /**
     * Getter for y coordinate.
     *
     * @return y
     */
    public int getY()
    {
        return y;
    }

    /**
     * To string method to display x and y.
     *
     * @return
     */
    @Override
    public String toString()
    {
        return "[" + "x=" + x + ", y=" + y + ']';
    }
}