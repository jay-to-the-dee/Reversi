package controller;

import model.ModelInterface;
import view.GUI;

/**
 *
 * @author jay-to-the-dee <jay-to-the-dee@users.noreply.github.com>
 */
public class Controller implements ControllerInterface
{
    ModelInterface model;
    GUI view;

    public Controller(ModelInterface model, GUI view)
    {
        this.model = model;
        this.view = view;
        
        model.setController(this);
        view.setController(this);
    }

    @Override
    public ModelInterface getModel()
    {
        return model;
    }

    @Override
    public GUI getView()
    {
        return view;
    }
    
    
}
