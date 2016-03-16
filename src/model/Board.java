package model;

import static model.DiskState.*;

/**
 *
 * @author jay-to-the-dee <jay-to-the-dee@users.noreply.github.com>
 */
public class Board
{
    public final static int BOARDSIZE = 8;

    BoardDisk[][] board;

    public Board()
    {
        board = new BoardDisk[BOARDSIZE][BOARDSIZE];

        setUpStartingDisks();
    }

    private void setUpStartingDisks()
    {
        board[BOARDSIZE / 2][BOARDSIZE / 2] = new BoardDisk(WHITE);
        board[(BOARDSIZE / 2) - 1][(BOARDSIZE / 2) - 1] = new BoardDisk(WHITE);

        board[(BOARDSIZE / 2)][(BOARDSIZE / 2) - 1] = new BoardDisk(BLACK);
        board[(BOARDSIZE / 2) - 1][(BOARDSIZE / 2)] = new BoardDisk(BLACK);
    }

    public void addDisk(int X, int Y, DiskState colour)
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
            board[X - 1][Y - 1] = new BoardDisk(colour);
        }
    }

    /**
     * Gets the disk object for the specified co-ordinate
     * WARNING: Calling functions should check with either doesDiskExist
     * before calling this method or check for null value from this method
     * before operating on returned Disk object!
     *
     * @param X
     * @param Y
     * @return null if no disk at position specified, Disk object otherwise
     */
    public BoardDisk getDisk(int X, int Y)
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

    public static boolean isOutOfBounds(int X, int Y)
    {
        return (X <= 0 || X > BOARDSIZE || Y <= 0 || Y > BOARDSIZE);
    }

    public boolean doesDiskExist(int X, int Y)
    {
        return board[X - 1][Y - 1] != null;
    }

    @Override
    public String toString()
    {
        StringBuilder buffer = new StringBuilder();

        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board[0].length; j++)
            {
                BoardDisk currentDisk = board[i][j];
                if (currentDisk != null)
                {
                    buffer.append(currentDisk.getCurrentState());
                }
                else
                {
                    buffer.append(" ");
                }
            }
            buffer.append("\n");
        }
        return buffer.toString();
    }

    private abstract class BoardException extends RuntimeException
    {
        public BoardException(String message)
        {
            super(message);
        }
    }

    private class DiskAlreadyExistsException extends BoardException
    {
        public DiskAlreadyExistsException(int X, int Y)
        {
            super("Disk already exists at position (" + X + "," + Y + ")!");
        }
    }

    private class OutOfBoundsException extends BoardException
    {
        public OutOfBoundsException(int X, int Y)
        {
            super("Co-ordinates (" + X + "," + Y + ") are outside the grid boundary!");
        }
    }
}
