package view;

import controller.ControllerInterface;

/**
 *
 * @author jay-to-the-dee <jay-to-the-dee@users.noreply.github.com>
 */
public interface ViewInterface
{
    public void setController(ControllerInterface controller);
    public void showAndRun();

    public void updateCurrentPlayer(PlayerEnum currentPlayer);
}
