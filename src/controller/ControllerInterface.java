package controller;

import model.ModelInterface;
import view.GUI;

/**
 *
 * @author jay-to-the-dee <jay-to-the-dee@users.noreply.github.com>
 */
public interface ControllerInterface
{
    public ModelInterface getModel();
    public GUI getView();
}
