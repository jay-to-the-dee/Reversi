package model;

import static model.BoardSpaceState.*;

/**
 * This represent a single Reversi disk
 *
 * @author jay-to-the-dee <jay-to-the-dee@users.noreply.github.com>
 */
public class BoardSpace
{
    private BoardSpaceState currentState;

    /**
     * Constructs a new empty board space
     */
    public BoardSpace()
    {
        this.currentState = EMPTY;
    }

    /**
     * Set the disk to either BLACK or WHITE state
     *
     * @param currentState choose DiskState here
     */
    public void setCurrentState(BoardSpaceState currentState)
    {
        if (currentState == EMPTY)
        {
            throw new SetSpaceToEmptyException();
        }
        this.currentState = currentState;
    }

    /**
     * Flips this reversi disk from either BLACK to WHITE or vice-versa
     */
    public void flipDisk()
    {
        if (currentState == EMPTY)
        {
            throw new FlipEmptyException();
        }
        currentState = currentState.oppositeState();
    }

    /**
     * Standard getter for the disk's current state
     *
     * @return currentState
     */
    public BoardSpaceState getCurrentState()
    {
        return currentState;
    }

    private class SetSpaceToEmptyException extends ModelException
    {
        public SetSpaceToEmptyException()
        {
            super("Can't set a BoardSpace to empty!");
        }
    }

    private class FlipEmptyException extends ModelException
    {
        public FlipEmptyException()
        {
            super("Can't flip an empty BoardSpace!");
        }
    }
}
