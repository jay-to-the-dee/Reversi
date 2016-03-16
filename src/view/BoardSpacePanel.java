package view;

import java.awt.*;
import javax.swing.JPanel;
import model.*;

/**
 *
 * @author jay-to-the-dee <jay-to-the-dee@users.noreply.github.com>
 */
class BoardSpacePanel extends JPanel
{
    private static final int SIZE = 50;
    private static final int PADDING = 1;

    private final BoardSpace boardSpace;

    public BoardSpacePanel(BoardSpace boardSpace)
    {
        this.boardSpace = boardSpace;
        this.setPreferredSize(new Dimension(SIZE, SIZE));
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(Color.GREEN);
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());

        BoardSpaceState currentState = boardSpace.getCurrentState();
        g2d.setColor(currentState.getColor());
        g2d.fillOval(PADDING, PADDING, this.getWidth() - PADDING * 2, this.getHeight() - PADDING * 2);
        g2d.setColor(currentState.oppositeState().getColor());
        g2d.drawOval(PADDING, PADDING, this.getWidth() - PADDING * 2, this.getHeight() - PADDING * 2);

        g2d.setColor(Color.BLACK);
        g2d.drawRect(0, 0, this.getWidth(), this.getHeight());
    }
}
