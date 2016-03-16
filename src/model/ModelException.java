package model;

/**
 * This is a RuntimeException as I don't expect ModelException's to occur during 
 * normal running, but they will be useful for development and debugging!
 * @author jay-to-the-dee <jay-to-the-dee@users.noreply.github.com>
 */
abstract class ModelException extends RuntimeException
{
    public ModelException(String message)
    {
        super(message);
    }
}
