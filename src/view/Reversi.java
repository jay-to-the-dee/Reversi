package view;

import controller.*;
import model.*;

/**
 *
 * @author jay-to-the-dee <jay-to-the-dee@users.noreply.github.com>
 */
public class Reversi
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        ModelInterface model = new Board(); //This is our Model
        ViewInterface view = new GUI(); //This is our View
                
        //This controller will link everything together
        ControllerInterface controller = new Controller(model, view);
        
        view.showAndRun();
    }
}
