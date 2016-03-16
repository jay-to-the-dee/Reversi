package view;

import java.awt.GridLayout;
import javax.swing.JPanel;
import model.ModelInterface;

/**
 *
 * @author jay-to-the-dee <jay-to-the-dee@users.noreply.github.com>
 */
class BoardPanel extends JPanel
{
    private final ModelInterface model;
    private BoardSpacePanel[][] panels;

    public BoardPanel(ModelInterface model)
    {
        super();
        this.model = model;
        this.panels = new BoardSpacePanel[ModelInterface.BOARDSIZE][ModelInterface.BOARDSIZE];

        this.setLayout(new GridLayout(ModelInterface.BOARDSIZE, ModelInterface.BOARDSIZE));

        for (int i = 0; i < ModelInterface.BOARDSIZE; i++)
        {
            for (int j = 0; j < ModelInterface.BOARDSIZE; j++)
            {
                panels[i][j] = new BoardSpacePanel(model.getDisk(i + 1, j + 1));
                this.add(panels[i][j]);
            }
        }
    }
}
