package steelballs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;

public class BallContainer extends JPanel {
    private final ArrayList<Ball> balls;
    private final MouseAdapter mouseListener;

    public BallContainer(){
        balls = new ArrayList<>();
        mouseListener = new BallListener(balls);
        this.addMouseListener(mouseListener);
    }

    public void moveBall(){
        repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1000, 1000);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        for (Ball ball : balls) {
            g2d.setPaint(ball.getBallColor());
            g2d.fillOval((int) ball.getBallCoordinates().getX(),(int) ball.getBallCoordinates().getY(),(int) ball.getRadius(),(int) ball.getRadius());
        }

    }


}
