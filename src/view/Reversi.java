package view;

import controller.Controller;
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
    public static void main(String[] args) throws Exception
    {
        ModelInterface model = new Board(); //This is our Model
        GUI view = new GUI(); //This is our View
                
        //This controller will link everything together
        Controller controller = new Controller(model, view);
        
        view.showAndRun();
    }
}
