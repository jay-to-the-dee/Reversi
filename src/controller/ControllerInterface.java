package controller;

import model.*;
import view.*;

/**
 *
 * @author jay-to-the-dee <jay-to-the-dee@users.noreply.github.com>
 */
public interface ControllerInterface
{
    public ModelInterface getModel();
    public ViewInterface getView();
    
    public void mainGameLoop();
    public void recievePlayerDiskAdd(DiskCoordinate coordinate, PlayerEnum player);
    public DiskCoordinate doGreedyAISearch(PlayerEnum player);
}
