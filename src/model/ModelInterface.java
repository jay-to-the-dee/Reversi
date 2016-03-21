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
    public void addDisk(int X, int Y, BoardSpaceState colour);
    public BoardSpace getDisk(int X, int Y);
    public boolean doesDiskExist(int X, int Y);
    public boolean isBoardFull();
}
