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
    private final BoardSpace[][] board;

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

        addDisk(new DiskCoordinate(halfBoardSize, halfBoardSize), WHITE);
        addDisk(new DiskCoordinate(halfBoardSize - 1, halfBoardSize - 1), WHITE);

        addDisk(new DiskCoordinate(halfBoardSize - 1, halfBoardSize), BLACK);
        addDisk(new DiskCoordinate(halfBoardSize, halfBoardSize - 1), BLACK);
    }

    @Override
    public void setController(ControllerInterface controller)
    {
        this.controller = controller;
    }

    @Override
    public void addDisk(DiskCoordinate coordinate, BoardSpaceState colour)
    {
        int X = coordinate.getX(), Y = coordinate.getY();
        if (isOutOfBounds(coordinate))
        {
            throw new OutOfBoundsException(coordinate);
        }
        else if (doesDiskExist(coordinate))
        {
            throw new DiskAlreadyExistsException(coordinate);
        }
        else
        {
            board[Y - 1][X - 1].setCurrentState(colour);
        }
    }

    /**
     * Gets the disk object for the specified coordinate
     *
     * @param coordinate the X,Y position of the disk
     * @return null if no disk at position specified, Disk object otherwise
     */
    @Override
    public BoardSpace getDisk(DiskCoordinate coordinate)
    {
        int X = coordinate.getX(), Y = coordinate.getY();
        if (isOutOfBounds(coordinate))
        {
            throw new OutOfBoundsException(coordinate);
        }
        else
        {
            return board[Y - 1][X - 1];
        }
    }

    @Override
    public boolean doesDiskExist(DiskCoordinate coordinate)
    {
        int X = coordinate.getX(), Y = coordinate.getY();
        return board[Y - 1][X - 1].getCurrentState() != EMPTY;
    }

    @Override
    public WhiteBlackCount getDisksCount()
    {
        int whiteCount = 0;
        int blackCount = 0;

        for (int i = 1; i <= board.length; i++)
        {
            for (int j = 1; j <= board[0].length; j++)
            {
                switch (getDisk(new DiskCoordinate(j, i)).getCurrentState())
                {
                    case WHITE:
                        whiteCount++;
                        break;
                    case BLACK:
                        blackCount++;
                        break;
                }
            }
        }
        return new WhiteBlackCount(whiteCount, blackCount);
    }

    @Override
    public boolean isOutOfBounds(DiskCoordinate coordinate)
    {
        int X = coordinate.getX(), Y = coordinate.getY();
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
        public DiskAlreadyExistsException(DiskCoordinate coordinate)
        {
            super("Disk already exists at position " + coordinate.toString() + "!");
        }
    }

    private class OutOfBoundsException extends ModelException
    {
        public OutOfBoundsException(DiskCoordinate coordinate)
        {
            super("Co-ordinates " + coordinate.toString() + " are outside the grid boundary!");
        }
    }

}
