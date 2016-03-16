package model;

import java.awt.Color;

/**
 * Represents a disk being in either a black or white state
 * Contains some helpful extra methods too!
 * @author jay-to-the-dee <jay-to-the-dee@users.noreply.github.com>
 */
public enum DiskState
{
    BLACK, WHITE;

    public DiskState oppositeState()
    {
        switch (this)
        {
            case BLACK:
                return WHITE;
            default:
                return BLACK;
        }
    }

    public Color getColor()
    {
        switch (this)
        {
            case BLACK:
                return Color.BLACK;
            default:
                return Color.WHITE;
        }
    }

    @Override
    public String toString()
    {
        switch (this)
        {
            case BLACK:
                return "B";
            default:
                return "W";
        }
    }
}
