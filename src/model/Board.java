package model;

import controller.ControllerInterface;
import static model.BoardSpaceState.*;

/**
 *
 * @author jay-to-the-dee <jay-to-the-dee@users.noreply.github.com>
 */
public class Board implements ModelInterface
{
    private ControllerInterface controller;
    private BoardSpace[][] board;

    public Board()
    {
        board = new BoardSpace[BOARDSIZE][BOARDSIZE];
        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board[0].length; j++)
            {
                board[i][j] = new BoardSpace();
            }
        }

        addStartingDisks();
    }

    private void addStartingDisks()
    {
        final int halfBoardSize = (BOARDSIZE / 2) + 1;

        addDisk(halfBoardSize, halfBoardSize, WHITE);
        addDisk(halfBoardSize - 1, halfBoardSize - 1, WHITE);

        addDisk(halfBoardSize - 1, halfBoardSize, BLACK);
        addDisk(halfBoardSize, halfBoardSize - 1, BLACK);
    }

    @Override
    public void setController(ControllerInterface controller)
    {
        this.controller = controller;
    }

    @Override
    public void addDisk(int X, int Y, BoardSpaceState colour)
    {
        if (isOutOfBounds(X, Y))
        {
            throw new OutOfBoundsException(X, Y);
        }
        else if (doesDiskExist(X, Y))
        {
            throw new DiskAlreadyExistsException(X, Y);
        }
        else
        {
            board[X - 1][Y - 1].setCurrentState(colour);
        }
    }

    /**
     * Gets the disk object for the specified coordinate
     * WARNING: Calling functions should check with either doesDiskExist
     * before calling this method or check for null value from this method
     * before operating on returned Disk object!
     *
     * @param X
     * @param Y
     * @return null if no disk at position specified, Disk object otherwise
     */
    @Override
    public BoardSpace getDisk(int X, int Y)
    {
        if (isOutOfBounds(X, Y))
        {
            throw new OutOfBoundsException(X, Y);
        }
        else
        {
            return board[X - 1][Y - 1];
        }
    }

    @Override
    public boolean doesDiskExist(int X, int Y)
    {
        return board[X - 1][Y - 1].getCurrentState() != EMPTY;
    }

    public boolean isBoardFull()
    {
        for (int i = 1; i <= board.length; i++)
        {
            for (int j = 1; j <= board[0].length; j++)
            {
                if (!doesDiskExist(i,j))
                {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isOutOfBounds(int X, int Y)
    {
        return (X <= 0 || X > BOARDSIZE || Y <= 0 || Y > BOARDSIZE);
    }

    @Override
    public String toString()
    {
        StringBuilder buffer = new StringBuilder();

        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board[0].length; j++)
            {
                buffer.append(board[i][j].getCurrentState());
            }
            buffer.append("\n");
        }
        return buffer.toString();
    }

    private class DiskAlreadyExistsException extends ModelException
    {
        public DiskAlreadyExistsException(int X, int Y)
        {
            super("Disk already exists at position (" + X + "," + Y + ")!");
        }
    }

    private class OutOfBoundsException extends ModelException
    {
        public OutOfBoundsException(int X, int Y)
        {
            super("Co-ordinates (" + X + "," + Y + ") are outside the grid boundary!");
        }
    }

}
