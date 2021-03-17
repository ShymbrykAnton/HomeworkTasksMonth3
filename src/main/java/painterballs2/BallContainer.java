package painterballs2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BallContainer extends JPanel {
    private final JFrame frame;
    volatile List<Ball> balls;

    public BallContainer(JFrame frame) {
        this.frame = frame;
        balls = new ArrayList<>();
        MouseAdapter mouseListener = new BallListener(balls, frame);
        this.addMouseListener(mouseListener);
    }

    public void moveBall() {
        repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(frame.getWidth(), frame.getHeight());
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Ball remove = null;
        boolean flag = true;
        for (Ball ball : balls) {
            for (int i = balls.size()-1; i >= 0; i--) {
                if (balls.get(i).equals(ball)) {
                    continue;
                } else {
                    double dx = ball.getBallCoordinates().getX() - balls.get(i).getBallCoordinates().getX();
                    double dy = ball.getBallCoordinates().getY() - balls.get(i).getBallCoordinates().getY();
                    double distance = Math.sqrt((dx*dx)+(dy*dy));
                    if(distance<ball.getRadius()/2.0+balls.get(i).getRadius()/2.0){
                       remove = ball;
                       Color color = new Color(ball.getBallColor().getRGB()+balls.get(i).getBallColor().getRGB());
                       int speedX = ball.getSpeedX()+balls.get(i).getSpeedX();
                       int speedY = ball.getSpeedY()+balls.get(i).getSpeedY();
                       int radius = ball.getRadius()+balls.get(i).getRadius();
                       balls.get(i).setRadius(radius);
                       balls.get(i).setBallColor(color);
                       balls.get(i).setSpeedX(speedX);
                       balls.get(i).setSpeedY(speedY);
                        flag = false;
                        break;
                    }
                }
            }
            if(!flag){
                break;
            }
        }
        balls.remove(remove);
        balls.forEach(ball -> {
            ball.setHeight(frame.getHeight());
            ball.setWidth(frame.getWidth());
            g2d.setPaint(ball.getBallColor());
            g2d.fillOval((int) ball.getBallCoordinates().getX(),
                    (int) ball.getBallCoordinates().getY(),
                    (int) ball.getRadius(), (int) ball.getRadius());
        });
    }
}
