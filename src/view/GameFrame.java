package view;

import java.awt.*;
import javax.swing.*;
import model.ModelInterface;

/**
 *
 * @author jay-to-the-dee <jay-to-the-dee@users.noreply.github.com>
 */
class GameFrame
{
    private final ModelInterface model;
    private PlayerEnum player;

    private final JFrame frame;
    private JLabel statusLabel;
    private JPanel gamePanel;
    private JButton AIButton;

    public GameFrame(ModelInterface model, PlayerEnum player)
    {
        this.model = model;
        this.player = player;

        frame = new JFrame("Reversi - " + player + " player");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        addContents(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void addContents(Container contentPane)
    {
        statusLabel = new JLabel(" ");
        contentPane.add(statusLabel, BorderLayout.NORTH);

        gamePanel = new BoardPanel(model, player);
        contentPane.add(gamePanel, BorderLayout.CENTER);

        AIButton = new JButton("Greedy AI (Play " + player + ")");
        contentPane.add(AIButton, BorderLayout.SOUTH);

    }
}
