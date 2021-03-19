package painterballs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;

public class BallContainer1 extends JPanel {
    private final ArrayList<Ball1> ball1s;
    private final JFrame frame;

    public BallContainer1(JFrame frame){
        this.frame = frame;
        ball1s = new ArrayList<>();
        MouseAdapter mouseListener = new BallListener1(ball1s, frame);
        this.addMouseListener(mouseListener);
    }

    public void moveBall(){
        repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(frame.getWidth(), frame.getHeight());
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        for (Ball1 ball1 : ball1s) {
            ball1.setFrameHeight(frame.getHeight());
            ball1.setFrameWidth(frame.getWidth());
            g2d.setPaint(ball1.getBallColor());
            g2d.fillOval((int) ball1.getBallCoordinates().getX(),(int) ball1.getBallCoordinates().getY(),(int) ball1
                    .getDiameter(),(int) ball1.getDiameter());
        }
    }
}
