package model;

/**
 * Represents an X-Y co-ordinate for a disk on the board
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
        return "DiskCoordinate\t" + "X=" + X + "\tY=" + Y;
    }
}
