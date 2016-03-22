package model;

import controller.ControllerInterface;

/**
 *
 * @author jay-to-the-dee <jay-to-the-dee@users.noreply.github.com>
 */
public interface ModelInterface
{
    public final static int BOARDSIZE = 8;

    public void setController(ControllerInterface controller);
    public void addDisk(DiskCoordinate coordinate, BoardSpaceState colour);
    public BoardSpace getDisk(DiskCoordinate coordinate);
    public boolean doesDiskExist(DiskCoordinate coordinate);
    public boolean isBoardFull();
    
   public boolean isOutOfBounds(DiskCoordinate coordinate);
}
