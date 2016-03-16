package view;

import controller.ControllerInterface;
import static view.PlayerEnum.*;

/**
 *
 * @author jay-to-the-dee <jay-to-the-dee@users.noreply.github.com>
 */
public class GUI
{
    private ControllerInterface controller;
            
    private GameFrame whitePlayer;
    private GameFrame blackPlayer;

    public void setController(ControllerInterface controller)
    {
        this.controller = controller;
    }

    public void showAndRun() throws Exception
    {
        if (controller != null)
        {
            this.whitePlayer = new GameFrame(controller.getModel(), WHITE_PLAYER);
            this.blackPlayer = new GameFrame(controller.getModel(), BLACK_PLAYER);
        }
        else
        {
            //This should never happen (in theory)
            throw new Exception("Controller not set before GUI startup!");
        }
    }
}
