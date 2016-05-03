package controller;

import javax.swing.JOptionPane;
import static model.ModelInterface.BOARDSIZE;
import model.*;
import view.*;

/**
 *
 * @author jay-to-the-dee <jay-to-the-dee@users.noreply.github.com>
 */
public class Controller implements ControllerInterface
{
    ModelInterface model;
    ViewInterface view;

    private PlayerEnum currentPlayer;
    private SearchMap searchMap;
    private WhiteBlackCount currentWBCount;

    public Controller(ModelInterface model, ViewInterface view)
    {
        this.model = model;
        this.view = view;

        model.setController(this);
        view.setController(this);

        currentPlayer = PlayerEnum.BLACK_PLAYER;
    }

    @Override
    public ModelInterface getModel()
    {
        return model;
    }

    @Override
    public ViewInterface getView()
    {
        return view;
    }

    @Override
    public synchronized void mainGameLoop()
    {
        do
        {
            searchMap = new SearchMap(model, currentPlayer.oppositePlayer());
            if (searchMap.canPlayLegalMove()) //Only switch player if they'll be able to play a legal move
            {
                currentPlayer = currentPlayer.oppositePlayer();
                view.updateCurrentPlayer(currentPlayer);
            }
            else if (!new SearchMap(model, currentPlayer).canPlayLegalMove())
            {
                break;
            }

            waitForUserToPlaceDisk(); //basically this.wait() neatened
            searchMap = null;

            currentWBCount = model.getDisksCount();
        }
        while (gameContinuationConditions(currentWBCount));

        doGameOverSequence(currentWBCount); //Game has ended when loop method reaches here
    }

    private static boolean gameContinuationConditions(WhiteBlackCount currentWBCount)
    {
        return currentWBCount.getTotalCount() != Math.pow(BOARDSIZE, 2) //Board full
                && currentWBCount.getBlackCount() != 0 //Deal with condition where no opposition disks left 
                && currentWBCount.getWhiteCount() != 0; //Ditto
    }

    private void doGameOverSequence(WhiteBlackCount currentWBCount)
    {
        JOptionPane.showMessageDialog(null, "Game over - " + currentWBCount.getWinnerString() + "! "
                + currentWBCount.getRatioString(),
                "Game over!",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private synchronized void waitForUserToPlaceDisk()
    {
        try
        {
            this.wait(); //We're waiting for user to place disk here here
        }
        catch (InterruptedException ex)
        {
        }
    }

    @Override
    public synchronized void recievePlayerDiskAdd(DiskCoordinate coordinate, PlayerEnum player)
    {
        try
        {
            if (player != currentPlayer)
            {
                throw new WrongPlayerException(player);
            }
            FlipQueue flipQueue;
            do
            {
                flipQueue = searchMap.getCachedFlipQueue(coordinate); //How do we wait for this to make sure our most recent searchMap exists?
                //FlipQueue flipQueue = new FlipQueue(model, coordinate, player);
            }
            while (flipQueue == null);

            if (flipQueue.getTotalFlipCount() == 0)
            {
                throw new NoDisksCapturedException(player);
            }
            model.addDisk(coordinate, player.getPlayersDisk());
            addFlipQueue(flipQueue);
            this.notifyAll();
            view.updateCurrentPlayer(currentPlayer); //TODO: Better way to refresh GUI?
        }
        catch (ModelException | ControllerException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Invalid move", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void addFlipQueue(FlipQueue flipQueue)
    {
        for (DiskCoordinate coordinate : flipQueue.getAllQueueCoordinates())
        {
            model.getDisk(coordinate).flipDisk();
        }
    }

    @Override
    public synchronized DiskCoordinate doGreedyAISearch()
    {
        return searchMap.doGreedyAISearch();
    }

    private static class WrongPlayerException extends ControllerException
    {
        public WrongPlayerException(PlayerEnum player)
        {
            super("Not currently " + player.toString().toLowerCase() + " players turn!");
        }
    }

    private static class NoDisksCapturedException extends ControllerException
    {
        public NoDisksCapturedException(PlayerEnum player)
        {
            super("Cannot place disk here, as it doesn't capture any " + player.oppositePlayer().toString().toLowerCase() + " disks.");
        }
    }

}
