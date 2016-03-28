package model;

import static model.ModelInterface.BOARDSIZE;

/**
 * Represents an X-Y co-ordinate for a disk on the board
 *
 * @author jay-to-the-dee <jay-to-the-dee@users.noreply.github.com>
 */
public class DiskCoordinate
{
    private final int X;
    private final int Y;

    public DiskCoordinate(int X, int Y)
    {
        this.X = X;
        this.Y = Y;
    }

    public int getX()
    {
        return X;
    }

    public int getY()
    {
        return Y;
    }

    @Override
    public String toString()
    {
        return "(" + X + "," + Y + ")";
        //return "DiskCoordinate\t" + "X=" + X + "\tY=" + Y;
    }

    @Override
    public int hashCode()
    {
        return (BOARDSIZE * X) + Y;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final DiskCoordinate other = (DiskCoordinate) obj;
        return this.X == other.X && this.Y == other.Y;
    }
}
