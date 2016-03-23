package controller;

import java.util.*;
import model.*;
import static model.BoardSpaceState.*;
import static model.ModelInterface.BOARDSIZE;
import view.PlayerEnum;

/**
 * This class finds all the flips that a new move will make
 *
 * @author jay-to-the-dee <jay-to-the-dee@users.noreply.github.com>
 */
public class FlipQueue
{
    private final ModelInterface model;
    private final DiskCoordinate startingDisk;
    private final PlayerEnum player;

    private Map<SearchDirectionsEnum, Queue<DiskCoordinate>> queues;

    public FlipQueue(ModelInterface model, DiskCoordinate startingDisk, PlayerEnum player)
    {
        this.model = model;
        this.startingDisk = startingDisk;
        this.player = player;
        this.queues = new HashMap<>();

        for (SearchDirectionsEnum currentSearchDirection : SearchDirectionsEnum.values())
        {
            this.queues.put(currentSearchDirection, new ArrayDeque<>());
        }

        doQueueGeneration();
    }

    private void doQueueGeneration()
    {
        for (SearchDirectionsEnum currentSearchDirection : SearchDirectionsEnum.values())
        {
            for (int i = 1; i <= BOARDSIZE; i++)
            {
                DiskCoordinate coordinate = currentSearchDirection.getCoordinate(startingDisk, i);
                Queue<DiskCoordinate> relevantQueue = queues.get(currentSearchDirection);
                BoardSpace disk; //Assign later after out-of-bounds check

                if (model.isOutOfBounds(coordinate))
                {
                    relevantQueue.clear(); //Finished by board ending
                    break; //Reached end of board - first search complete condition
                }
                disk = model.getDisk(coordinate);
                if (disk.getCurrentState() == player.getPlayersDisk().oppositeState())
                {
                    //Add this co-ordinate to queue as it's ready to flip & continue search
                    relevantQueue.add(coordinate);
                }
                else if (disk.getCurrentState() == EMPTY)
                {
                    relevantQueue.clear(); //Finished on an empty
                    break;
                }
                else //Found origin disk - means search complete - with results
                {
                    break;
                }
            }
        }
    }

    public int getTotalFlipCount()
    {
        int count = 0;
        for (Queue<DiskCoordinate> queue : queues.values())
        {
            count += queue.size();
        }
        return count;
    }

    public Collection<DiskCoordinate> getAllQueueCoordinates()
    {
        Collection<DiskCoordinate> allCoordinates = new ArrayDeque<>();
        for (Queue<DiskCoordinate> queue : queues.values())
        {
            allCoordinates.addAll(queue);
        }
        return allCoordinates;
    }
}
