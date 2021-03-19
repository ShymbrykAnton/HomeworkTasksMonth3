package painterballs3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class BallContainer3 extends JPanel {
    private final JFrame frame;
    volatile List<Ball3> ball3s;

    public BallContainer3(JFrame frame) {
        this.frame = frame;
        ball3s = new ArrayList<>();
        MouseAdapter mouseListener = new BallListener3(ball3s, frame);
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
        Ball3 remove = null;
        boolean flag = true;
        for (Ball3 ball3 : ball3s) {
            for (int i = ball3s.size() - 1; i >= 0; i--) {
                if (ball3s.get(i).equals(ball3)) {
                    continue;
                } else {
                    double dx = ball3.getBallCoordinates().getX() - ball3s.get(i).getBallCoordinates().getX();
                    double dy = ball3.getBallCoordinates().getY() - ball3s.get(i).getBallCoordinates().getY();
                    double distance = Math.sqrt(Math.pow(dx,2) + Math.pow(dy,2));
                    double generalRadius = ball3.getDiameter() / 2.0 + ball3s.get(i).getDiameter() / 2.0;
                    if (distance <= generalRadius) {
                        int speedX1 = ball3.getSpeedX();
                        int speedY1 = ball3.getSpeedY();
                        int radius1 = ball3.getDiameter();

                        int speedX2 = ball3s.get(i).getSpeedX();
                        int speedY2 = ball3s.get(i).getSpeedY();
                        int radius2 = ball3s.get(i).getDiameter();

                        resetNewSpeed(speedX1, speedX2, speedY1, speedY2,radius1, radius2,
                                ball3, ball3s.get(i));

                        flag = false;
                        break;
                    }
                }
            }
            if (!flag) {
                break;
            }
        }
        ball3s.forEach(ball3 -> {
            ball3.setFrameHeight(frame.getHeight());
            ball3.setFrameWidth(frame.getWidth());
            g2d.setPaint(ball3.getBallColor());
            g2d.fillOval((int) ball3.getBallCoordinates().getX(),
                    (int) ball3.getBallCoordinates().getY(),
                    (int) ball3.getDiameter(), (int) ball3.getDiameter());
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

    private void resetNewSpeed(int speedX1, int speedX2, int speedY1, int speedY2, int radius1, int radius2, Ball3 ball31, Ball3 ball32) {
        int newSpeedX1 = getNewSpeed1(speedX1, speedX2, radius1, radius2);
        int newSpeedX2 = getNewSpeed2(speedX1, speedX2, radius1, radius2);
        int newSpeedY1 = getNewSpeed1(speedY1, speedY2, radius1, radius2);
        int newSpeedY2 = getNewSpeed2(speedY1, speedY2, radius1, radius2);
        if (newSpeedX1 == newSpeedX2 && newSpeedY1 == newSpeedY2 &&
                newSpeedY1 == newSpeedX1) {
            resetNewSpeed(speedX1 + 1, speedX2 - 1, speedY1 + 1, speedY2 - 1,
                    radius1, radius2, ball31, ball32);
        } else {
            ball31.setSpeedX(newSpeedX1);
            ball31.setSpeedY(newSpeedY1);
            ball32.setSpeedX(newSpeedX2);
            ball32.setSpeedY(newSpeedY2);
            ball31.setBallCoordinates(new Point2D.Double(
                    ball31.getBallCoordinates().getX() + newSpeedX1,
                    ball31.getBallCoordinates().getY() + newSpeedY1));

            ball32.setBallCoordinates(new Point2D.Double(
                    ball32.getBallCoordinates().getX() + newSpeedX2,
                    ball32.getBallCoordinates().getY() + newSpeedY2));
        }
    }
}
