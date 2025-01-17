package view;

import model.BoardSpaceState;
import static model.BoardSpaceState.*;

/**
 * Use this to distinguish Black player and White player in our view
 *
 * @author jay-to-the-dee <jay-to-the-dee@users.noreply.github.com>
 */
public enum PlayerEnum
{
    BLACK_PLAYER, WHITE_PLAYER;

    @Override
    public String toString()
    {
        switch (this)
        {
            case BLACK_PLAYER:
                return "Black";
            case WHITE_PLAYER:
                return "White";
        }
        return null;
    }

    public PlayerEnum oppositePlayer()
    {
        switch (this)
        {
            case BLACK_PLAYER:
                return WHITE_PLAYER;
            case WHITE_PLAYER:
                return BLACK_PLAYER;
        }
        return null;
    }

    public BoardSpaceState getPlayersDisk()
    {
        switch (this)
        {
            case BLACK_PLAYER:
                return BLACK;
            case WHITE_PLAYER:
                return WHITE;
        }
        return null;
    }
}
