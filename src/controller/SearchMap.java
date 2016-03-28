package controller;

import java.util.*;
import model.*;
import static model.ModelInterface.BOARDSIZE;
import view.PlayerEnum;

/**
 *
 * @author jay-to-the-dee <jay-to-the-dee@users.noreply.github.com>
 */
class SearchMap
{
    private TreeMap<Integer, Set<FlipQueue>> searchMap;
    private PlayerEnum player;

    public SearchMap(ModelInterface model, PlayerEnum player)
    {
        searchMap = new TreeMap<>();
        this.player = player;

        for (int i = 1; i <= BOARDSIZE; i++)
        {
            for (int j = 1; j <= BOARDSIZE; j++)
            {
                DiskCoordinate currentCoordinate = new DiskCoordinate(j, i);
                if (model.doesDiskExist(currentCoordinate))
                {
                    continue;
                }
                FlipQueue flipQueue = new FlipQueue(model, currentCoordinate, player);
                int currentCount = flipQueue.getTotalFlipCount();
                Set<FlipQueue> set = searchMap.get(currentCount);
                if (set == null)
                {
                    searchMap.put(currentCount, set = new HashSet<>());
                }
                set.add(flipQueue);
            }
        }
    }

    public boolean canPlayLegalMove()
    {
        return searchMap.lastKey() != 0;
    }

    public DiskCoordinate doGreedyAISearch()
    {
        /* if (this.player == player) //Can check here for right player but
           should be no need as WrongPlayerException should be thrown in 
           recievePlayerDiskAdd on receipt of arbitary DiskCoordinate
        {*/
        ///This is so easy when we already have our search-map ready stored!
        return searchMap.lastEntry().getValue().iterator().next().getStartingDisk();
        //}
    }

    public PlayerEnum getPlayer()
    {
        return player;
    }
}
