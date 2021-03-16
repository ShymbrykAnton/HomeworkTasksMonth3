package steelballs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;

public class BallContainer extends JPanel {
    private final ArrayList<Ball> balls;
    private final JFrame frame;

    public BallContainer(JFrame frame){
        this.frame = frame;
        balls = new ArrayList<>();
        MouseAdapter mouseListener = new BallListener(balls, frame);
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
        for (Ball ball : balls) {
            ball.setHeight(frame.getHeight());
            ball.setWidth(frame.getWidth());
            g2d.setPaint(ball.getBallColor());
            g2d.fillOval((int) ball.getBallCoordinates().getX(),(int) ball.getBallCoordinates().getY(),(int) ball.getRadius(),(int) ball.getRadius());
        }

    }


}
