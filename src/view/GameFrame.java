package view;

import controller.ControllerInterface;
import java.awt.*;
import javax.swing.*;
import static view.PlayerEnum.*;

/**
 * This is basically one of JFrame, with all the stuff we want inside as well
 *
 * @author jay-to-the-dee <jay-to-the-dee@users.noreply.github.com>
 */
class GameFrame
{
    private final ControllerInterface controller;
    private final PlayerEnum player;

    private final JFrame frame;
    private JLabel statusLabel;
    private JPanel gamePanel;
    private JButton AIButton;

    public GameFrame(ControllerInterface controller, PlayerEnum player)
    {
        this.controller = controller;
        this.player = player;

        frame = new JFrame("Reversi - " + player + " player");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        addContents(frame.getContentPane());

        frame.pack();
        setFrameLocation();
        frame.setVisible(true);
    }

    private void addContents(Container contentPane)
    {
        statusLabel = new JLabel(" ");
        contentPane.add(statusLabel, BorderLayout.NORTH);

        gamePanel = new BoardPanel(controller, player);
        contentPane.add(gamePanel, BorderLayout.CENTER);

        AIButton = new JButton("Greedy AI (Play " + player + ")");
        contentPane.add(AIButton, BorderLayout.SOUTH);

    }

    private void setFrameLocation()
    {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final int screenHeight = screenSize.height;
        final int screenWidth = screenSize.width;

        final int X = screenWidth / 2;
        final int Y = screenHeight / 2 - frame.getHeight() / 2;

        if (player == WHITE_PLAYER) //White player goes to left of screen
        {
            frame.setLocation(X - frame.getWidth(), Y);
        }
        else
        {
            frame.setLocation(X, Y);
        }
    }

    public void setThisFramesTurn(boolean myTurn)
    {
        if (myTurn)
        {
            frame.toFront();
            frame.requestFocus();
            statusLabel.setText(player + " player - click place to put piece");
        }
        else
        {
            statusLabel.setText(player + " player - not your turn");
        }
        frame.repaint();
    }
}
