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

    public void recievePlayerDiskAdd(int X, int Y, PlayerEnum player)
    {
        try
        {
            model.addDisk(X, Y, player.getPlayersDisk());
        }
        catch (ModelException e) //TODO: Controller exceptions should be handled here too
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Invalid move", JOptionPane.WARNING_MESSAGE);
        }
        view.updateCurrentPlayer(currentPlayer);
    }
}
