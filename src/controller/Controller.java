package controller;

import java.util.*;
import javax.swing.JOptionPane;
import model.*;
import static model.ModelInterface.BOARDSIZE;
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
            currentPlayer = currentPlayer.oppositePlayer();
            view.updateCurrentPlayer(currentPlayer);

            try
            {
                this.wait(); //We're waiting for user to place disk here here
            }
            catch (InterruptedException ex)
            {
            }
        }
        while (!model.isBoardFull());

        //TODO: Game has ended here - report winner etc.
        JOptionPane.showMessageDialog(null, "Game over - TODO WINNER HERE", "Game over!", JOptionPane.INFORMATION_MESSAGE);
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
            FlipQueue flipQueue = new FlipQueue(model, coordinate, player);
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
    public DiskCoordinate doGreedyAISearch(PlayerEnum player)
    {
        TreeMap<Integer, Set<DiskCoordinate>> searchMap = new TreeMap<>();

        for (int i = 1; i <= BOARDSIZE; i++)
        {
            for (int j = 1; j <= BOARDSIZE; j++)
            {
                DiskCoordinate currentCoordinate = new DiskCoordinate(j, i);
                if (model.doesDiskExist(currentCoordinate))
                {
                    continue;
                }
                int currentCount = new FlipQueue(model, currentCoordinate, player).getTotalFlipCount();
                Set<DiskCoordinate> set = searchMap.get(currentCount);
                if (set == null)
                {
                    searchMap.put(currentCount, set = new HashSet<>());
                }
                set.add(currentCoordinate);
            }
        }

        return searchMap.lastEntry().getValue().iterator().next();
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
