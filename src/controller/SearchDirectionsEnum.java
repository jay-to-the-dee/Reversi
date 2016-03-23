package controller;

import model.DiskCoordinate;

/**
 *
 * @author jay-to-the-dee <jay-to-the-dee@users.noreply.github.com>
 */
enum SearchDirectionsEnum
{
    NORTH,
    NORTH_EAST,
    EAST,
    SOUTH_EAST,
    SOUTH,
    SOUTH_WEST,
    WEST,
    NORTH_WEST;

    public DiskCoordinate getCoordinate(DiskCoordinate startingDisk, int distanceFromStart)
    {
        int newX = startingDisk.getX(), newY = startingDisk.getY();

        switch (this)
        {
            case WEST:
                newY -= distanceFromStart;
                break;
            case SOUTH_WEST:
                newY -= distanceFromStart;
                newX += distanceFromStart;
                break;
            case SOUTH:
                newX += distanceFromStart;
                break;
            case SOUTH_EAST:
                newX += distanceFromStart;
                newY += distanceFromStart;
                break;
            case EAST:
                newY += distanceFromStart;
                break;
            case NORTH_EAST:
                newX -= distanceFromStart;
                newY += distanceFromStart;
                break;
            case NORTH:
                newX -= distanceFromStart;
                break;
            case NORTH_WEST:
                newX -= distanceFromStart;
                newY -= distanceFromStart;
                break;
        }
        return new DiskCoordinate(newX, newY);
    }
}
