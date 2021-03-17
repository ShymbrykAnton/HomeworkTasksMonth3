package painterballs3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.geom.Point2D;
import java.util.ArrayList;
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
            for (int i = balls.size() - 1; i >= 0; i--) {
                if (balls.get(i).equals(ball)) {
                    continue;
                } else {
                    double dx = ball.getBallCoordinates().getX() - balls.get(i).getBallCoordinates().getX();
                    double dy = ball.getBallCoordinates().getY() - balls.get(i).getBallCoordinates().getY();
                    double distance = Math.sqrt(Math.pow(dx,2) + Math.pow(dy,2));
                    double generalRadius = ball.getRadius() / 2.0 + balls.get(i).getRadius() / 2.0;
                    if (distance <= generalRadius) {
                        int speedX1 = ball.getSpeedX();
                        int speedY1 = ball.getSpeedY();
                        int radius1 = ball.getRadius();

                        int speedX2 = balls.get(i).getSpeedX();
                        int speedY2 = balls.get(i).getSpeedY();
                        int radius2 = balls.get(i).getRadius();

                        resetNewSpeed(speedX1, speedX2, speedY1, speedY2,radius1, radius2, ball, balls.get(i));

                        flag = false;
                        break;
                    }
                }
            }
            if (!flag) {
                break;
            }
        }
        balls.forEach(ball -> {
            ball.setHeight(frame.getHeight());
            ball.setWidth(frame.getWidth());
            g2d.setPaint(ball.getBallColor());
            g2d.fillOval((int) ball.getBallCoordinates().getX(),
                    (int) ball.getBallCoordinates().getY(),
                    (int) ball.getRadius(), (int) ball.getRadius());
        });

    }

    private int getNewSpeed1(int speed1, int speed2, int radius1, int radius2) {
        return ((radius1 - radius2) * speed1 + (2 * radius2 * speed2)) /
                (radius1 + radius2);
    }

    private int getNewSpeed2(int speed1, int speed2, int radius1, int radius2) {
        return ((2 * radius1 * speed1) + speed2 * (radius2 - radius1)) /
                (radius1 + radius2);
    }

    private void resetNewSpeed(int speedX1, int speedX2, int speedY1, int speedY2, int radius1, int radius2, Ball ball1, Ball ball2) {
        int newSpeedX1 = getNewSpeed1(speedX1, speedX2, radius1, radius2);
        int newSpeedX2 = getNewSpeed2(speedX1, speedX2, radius1, radius2);
        int newSpeedY1 = getNewSpeed1(speedY1, speedY2, radius1, radius2);
        int newSpeedY2 = getNewSpeed2(speedY1, speedY2, radius1, radius2);
        if (newSpeedX1 == newSpeedX2 && newSpeedY1 == newSpeedY2 &&
                newSpeedY1 == newSpeedX1) {
            resetNewSpeed(speedX1 + 1, speedX2 - 1, speedY1 + 1, speedY2 - 1,
                    radius1, radius2, ball1, ball2);
        } else {
            ball1.setSpeedX(newSpeedX1);
            ball1.setSpeedY(newSpeedY1);
            ball2.setSpeedX(newSpeedX2);
            ball2.setSpeedY(newSpeedY2);
            ball1.setBallCoordinates(new Point2D.Double(
                    ball1.getBallCoordinates().getX() + newSpeedX1,
                    ball1.getBallCoordinates().getY() + newSpeedY1));

            ball2.setBallCoordinates(new Point2D.Double(
                    ball2.getBallCoordinates().getX() + newSpeedX2,
                    ball2.getBallCoordinates().getY() + newSpeedY2));
        }
    }
}
