package view;

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
        Board board = new Board();
        
        board.addDisk(1, 1, DiskState.WHITE);
        board.getDisk(1, 1).flipDisk();
        
        System.out.print(board);
    }
    
}
