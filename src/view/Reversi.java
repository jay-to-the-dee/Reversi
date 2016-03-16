package view;

import model.*;
import static model.BoardSpaceState.*;

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
        
        board.addDisk(5, 3, WHITE);
        board.getDisk(4, 3).flipDisk();
        
        System.out.print(board);
    }
    
}
