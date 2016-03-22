package controller;

import javax.swing.JOptionPane;
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

            //We're waiting for user to place disk here here
            try
            {
                this.wait();
            }
            catch (InterruptedException ex)
            {
                //Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        while (!model.isBoardFull());

        //TODO: Game has ended here - report winner etc.
        JOptionPane.showMessageDialog(null, "Game over - TODO WINNER HERE", "Game over!", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public synchronized void recievePlayerDiskAdd(int X, int Y, PlayerEnum player)
    {
        try
        {
            if (player != currentPlayer)
            {
                throw new WrongPlayerException(player);
            }
            
            model.addDisk(X, Y, player.getPlayersDisk());
            this.notify();
            view.updateCurrentPlayer(currentPlayer);
        }
        catch (ModelException | ControllerException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Invalid move", JOptionPane.WARNING_MESSAGE);
        }

    }

    private static class WrongPlayerException extends ControllerException
    {
        public WrongPlayerException(PlayerEnum player)
        {
            super("Not currently " + player.toString().toLowerCase() + " players turn!");
        }
    }

}
