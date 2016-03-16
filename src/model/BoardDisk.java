package model;

/**
 * This represent a single Reversi disk
 *
 * @author jay-to-the-dee <jay-to-the-dee@users.noreply.github.com>
 */
public class BoardDisk
{
    private DiskState currentState;

    /**
     * Constructs the disk in either BLACK or WHITE state
     * @param currentState choose DiskState here
     */
    public BoardDisk(DiskState currentState)
    {
        this.currentState = currentState;
    }

    /**
     * Standard getter for the disk's current state
     * @return currentState
     */
    public DiskState getCurrentState()
    {
        return currentState;
    }

    /**
     * Flips this reversi disk from either black to white or vice versa
     */
    public void flipDisk()
    {
        currentState = currentState.oppositeState();
    }
}
