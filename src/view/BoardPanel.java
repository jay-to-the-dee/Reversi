package view;

import java.awt.GridLayout;
import javax.swing.JPanel;
import model.ModelInterface;
import static view.PlayerEnum.*;

/**
 *
 * @author jay-to-the-dee <jay-to-the-dee@users.noreply.github.com>
 */
class BoardPanel extends JPanel
{
    private final ModelInterface model;
    private PlayerEnum player;

    private BoardSpacePanel[][] panels;

    public BoardPanel(ModelInterface model, PlayerEnum player)
    {
        super();
        this.model = model;
        this.player = player;

        this.panels = new BoardSpacePanel[ModelInterface.BOARDSIZE][ModelInterface.BOARDSIZE];
        this.setLayout(new GridLayout(ModelInterface.BOARDSIZE, ModelInterface.BOARDSIZE));

        for (int i = 0; i < ModelInterface.BOARDSIZE; i++)
        {
            for (int j = 0; j < ModelInterface.BOARDSIZE; j++)
            {
                if (player == WHITE_PLAYER) //Implement 180 switch
                {
                    panels[i][j] = new BoardSpacePanel(model.getDisk(i + 1, j + 1));
                }
                else
                {
                    panels[i][j] = new BoardSpacePanel(model.getDisk(ModelInterface.BOARDSIZE - i, ModelInterface.BOARDSIZE - j ));
                }
                this.add(panels[i][j]);
            }
        }
    }
}
