package view;

import controller.ControllerInterface;
import static view.PlayerEnum.*;

/**
 *
 * @author jay-to-the-dee <jay-to-the-dee@users.noreply.github.com>
 */
public class GUI implements ViewInterface
{
    private ControllerInterface controller;

    private GameFrame whitePlayer;
    private GameFrame blackPlayer;

    @Override
    public void setController(ControllerInterface controller)
    {
        this.controller = controller;
    }

    @Override
    public void showAndRun()
    {
        if (controller != null)
        {
            this.blackPlayer = new GameFrame(controller, BLACK_PLAYER);
            this.whitePlayer = new GameFrame(controller, WHITE_PLAYER);
            controller.mainGameLoop();
        }
        else
        {
            //This should never happen (in theory)
            throw new RuntimeException("Controller not set before GUI startup!");
        }
    }

    @Override
    public void updateCurrentPlayer(PlayerEnum currentPlayer)
    {
        boolean whiteCurrentlyInControl = false;
        if (currentPlayer == PlayerEnum.WHITE_PLAYER)
        {
            whiteCurrentlyInControl = true;
        }
        whitePlayer.setThisFramesTurn(whiteCurrentlyInControl);
        blackPlayer.setThisFramesTurn(!whiteCurrentlyInControl);
    }
}
