package view;

import controller.ControllerInterface;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import model.*;
import static view.PlayerEnum.*;

/**
 * This class set's up and contains our BoardSpacePanel's
 *
 * @author jay-to-the-dee <jay-to-the-dee@users.noreply.github.com>
 */
class BoardPanel extends JPanel
{
    private final ControllerInterface controller;
    private final PlayerEnum player;

    //private BoardSpacePanel[][] panels;
    public BoardPanel(ControllerInterface controller, PlayerEnum player)
    {
        super();
        this.controller = controller;
        this.player = player;

        //this.panels = new BoardSpacePanel[ModelInterface.BOARDSIZE][ModelInterface.BOARDSIZE];
        this.setLayout(new GridLayout(ModelInterface.BOARDSIZE, ModelInterface.BOARDSIZE));
        this.setBackground(Color.BLACK);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));

        for (int x = 1; x <= ModelInterface.BOARDSIZE; x++)
        {
            for (int y = 1; y <= ModelInterface.BOARDSIZE; y++)
            {
                BoardSpacePanel panel; //TODO: Do we need our panels[i][j] reference?
                DiskCoordinate modelCoordinate;
                int modelX, modelY;
                if (player == WHITE_PLAYER) //Implement 180 switch
                {
                    modelCoordinate = new DiskCoordinate(x, y);
                }
                else //This is the switched board
                {
                    modelCoordinate = new DiskCoordinate(ModelInterface.BOARDSIZE - x + 1, ModelInterface.BOARDSIZE - y + 1);
                }
                panel = new BoardSpacePanel(controller.getModel().getDisk(modelCoordinate));
                panel.addMouseListener(new MouseAdapterImpl(modelCoordinate, player));
                this.add(panel);
            }
        }
    }
    private class MouseAdapterImpl extends MouseAdapter
    {
        private final DiskCoordinate modelCoordinate;
        private final PlayerEnum player;

        private MouseAdapterImpl(DiskCoordinate modelCoordinate, PlayerEnum player)
        {
            this.modelCoordinate = modelCoordinate;
            this.player = player;
        }

        @Override
        public void mouseClicked(MouseEvent e)
        {
            controller.recievePlayerDiskAdd(modelCoordinate, player);
        }
    }

}
