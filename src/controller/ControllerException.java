package controller;

/**
 *
 * @author jay-to-the-dee <jay-to-the-dee@users.noreply.github.com>
 */
abstract class ControllerException extends Exception
{
    public ControllerException(String message)
    {
        super(message);
    }
}
