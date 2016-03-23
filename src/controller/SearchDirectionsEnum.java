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
            case NORTH:
                newY -= distanceFromStart;
                break;
            case NORTH_EAST:
                newY -= distanceFromStart;
                newX += distanceFromStart;
                break;
            case EAST:
                newX += distanceFromStart;
                break;
            case SOUTH_EAST:
                newX += distanceFromStart;
                newY += distanceFromStart;
                break;
            case SOUTH:
                newY += distanceFromStart;
                break;
            case SOUTH_WEST:
                newX -= distanceFromStart;
                newY += distanceFromStart;
                break;
            case WEST:
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
