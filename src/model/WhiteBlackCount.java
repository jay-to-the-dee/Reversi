package model;

/**
 *
 * @author jay-to-the-dee <jay-to-the-dee@users.noreply.github.com>
 */
public class WhiteBlackCount
{
    private final int whiteCount;
    private final int blackCount;

    public WhiteBlackCount(int whiteCount, int blackCount)
    {
        this.whiteCount = whiteCount;
        this.blackCount = blackCount;
    }

    public int getWhiteCount()
    {
        return whiteCount;
    }

    public int getBlackCount()
    {
        return blackCount;
    }

    public int getTotalCount()
    {
        return whiteCount + blackCount;
    }

    public String getWinnerString()
    {
        if (whiteCount == blackCount)
        {
            return "It's a draw";
        }
        else if (whiteCount < blackCount)
        {
            return "Black player wins";
        }
        else
        {
            return "White player wins";
        }
    }

    public String getRatioString()
    {
        return "White: " + whiteCount + " Black: " + blackCount;
    }
}
