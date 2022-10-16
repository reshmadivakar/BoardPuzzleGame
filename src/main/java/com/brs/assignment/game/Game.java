package com.brs.assignment.game;

import com.brs.assignment.strategy.GameStrategy;

/**
 * Wrapper to hold the game properties and strategy chosen for a particular game.
 */
public class Game
{
    /**
     * Game properties
     */
    private GameProperties gameProperties;
    /**
     * Strategy chosen for the game.
     */
    private GameStrategy gameStrategy;

    /**
     * Constructor
     *
     * @param gameProperties
     *
     * @param gameStrategy
     */
    public Game(GameProperties gameProperties, GameStrategy gameStrategy)
    {
        this.gameProperties = gameProperties;
        this.gameStrategy = gameStrategy;
    }

    /**
     * The below method will execute the strategy and solve the game to find out the game result.
     *
     * @return GameResult
     */
    public GameResult solve()
    {
        return gameStrategy.executeStrategy(gameProperties);
    }
}
