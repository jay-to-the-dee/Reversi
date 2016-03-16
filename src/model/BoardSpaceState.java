package model;

import java.awt.Color;

/**
 * Represents a board space being in either a black or white state
 * Contains some helpful extra methods too!
 * @author jay-to-the-dee <jay-to-the-dee@users.noreply.github.com>
 */
public enum BoardSpaceState
{
    BLACK, WHITE, EMPTY;

    public BoardSpaceState oppositeState()
    {
        switch (this)
        {
            case BLACK:
                return WHITE;
            case WHITE:
                return BLACK;
            default:
                return EMPTY;
        }
    }

    public Color getColor()
    {
        switch (this)
        {
            case BLACK:
                return Color.BLACK;
            case WHITE:
                return Color.WHITE;
            default:
                return Color.GREEN;
        }
    }

    @Override
    public String toString()
    {
        switch (this)
        {
            case BLACK:
                return "B";
            case WHITE:
                return "W";
            default:
                return " ";
        }
    }
}
