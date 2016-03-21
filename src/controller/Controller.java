package controller;

import model.ModelInterface;
import view.PlayerEnum;
import view.ViewInterface;

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

        currentPlayer = PlayerEnum.WHITE_PLAYER;
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

    public void mainGameLoop()
    {
        do
        {
            currentPlayer = currentPlayer.oppositePlayer();
        }
        while (true);
    }
}
