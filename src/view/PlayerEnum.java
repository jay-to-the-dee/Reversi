package view;

/**
 * Use this to distinguish Black player and White player in our view
 * @author jay-to-the-dee <jay-to-the-dee@users.noreply.github.com>
 */
enum PlayerEnum
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
}
