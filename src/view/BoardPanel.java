package view;

import controller.ControllerInterface;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import model.ModelInterface;
import static view.PlayerEnum.*;

/**
 * This class set's up and contains our BoardSpacePanel's
 *
 * @author jay-to-the-dee <jay-to-the-dee@users.noreply.github.com>
 */
class BoardPanel extends JPanel
{
    private final ControllerInterface controller;
    private PlayerEnum player;

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
                int modelX, modelY;
                if (player == WHITE_PLAYER) //Implement 180 switch
                {
                    modelX = x;
                    modelY = y;
                }
                else //This is the switched board
                {
                    modelX = ModelInterface.BOARDSIZE - x + 1;
                    modelY = ModelInterface.BOARDSIZE - y + 1;
                }
                panel = new BoardSpacePanel(controller.getModel().getDisk(modelX, modelY));
                panel.addMouseListener(new MouseAdapterImpl(modelX, modelY, player));
                this.add(panel);
            }
        }
    }
    private class MouseAdapterImpl extends MouseAdapter
    {
        private final int x;
        private final int y;
        private final PlayerEnum player;

        public MouseAdapterImpl(int x, int y, PlayerEnum player)
        {
            this.x = x;
            this.y = y;
            this.player = player;
        }

        public void mouseClicked(MouseEvent e)
        {
            controller.recievePlayerDiskAdd(x, y, player);
        }
    }

}
