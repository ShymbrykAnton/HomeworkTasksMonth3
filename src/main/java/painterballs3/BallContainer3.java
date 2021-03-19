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
        updateBallCharacteristics();

        ball3s.forEach(ball3 -> {
            ball3.setFrameHeight(frame.getHeight());
            ball3.setFrameWidth(frame.getWidth());
            g2d.setPaint(ball3.getBallColor());
            g2d.fillOval((int) ball3.getBallCoordinates().getX(),
                    (int) ball3.getBallCoordinates().getY(),
                    ball3.getDiameter(), ball3.getDiameter());
        });
    }

    private void updateBallCharacteristics() {
        for (Ball3 ball3 : ball3s) {
            for (int i = ball3s.size() - 1; i >= 0; i--) {
                if (!ball3s.get(i).equals(ball3) && calculateDistance(ball3, ball3s.get(i)) < getGlobalRadius(ball3, ball3s.get(i))) {
                    resetNewSpeed(ball3, ball3s.get(i));
                    break;
                }
            }
        }
    }

    private double calculateDistance (Ball3 ball1, Ball3 ball2) {
        double dx = ball1.getBallCoordinates().getX() - ball2.getBallCoordinates().getX();
        double dy = ball1.getBallCoordinates().getY() - ball2.getBallCoordinates().getY();
        return Math.sqrt((dx*dx)+(dy*dy));
    }

    private double getGlobalRadius(Ball3 ball1, Ball3 ball2) {
        return ball1.getDiameter() / 2.0 + ball2.getDiameter() / 2.0;
    }

    private int getNewSpeed1(int speed1, int speed2, int radius1, int radius2) {
        return ((radius1 - radius2) * speed1 + (2 * radius2 * speed2)) /
                (radius1 + radius2);
    }

    private int getNewSpeed2(int speed1, int speed2, int radius1, int radius2) {
        return ((2 * radius1 * speed1) + speed2 * (radius2 - radius1)) /
                (radius1 + radius2);
    }

    private void resetNewSpeed( Ball3 ball31, Ball3 ball32) {
        int newSpeedX1 = getNewSpeed1(ball31.getSpeedX(), ball32.getSpeedX(), ball31.getDiameter(), ball32.getDiameter());
        int newSpeedX2 = getNewSpeed2(ball31.getSpeedX(), ball32.getSpeedX(), ball31.getDiameter(), ball32.getDiameter());
        int newSpeedY1 = getNewSpeed1(ball31.getSpeedY(), ball32.getSpeedY(), ball31.getDiameter(), ball32.getDiameter());
        int newSpeedY2 = getNewSpeed2(ball31.getSpeedY(), ball32.getSpeedY(), ball31.getDiameter(), ball32.getDiameter());
        if (newSpeedX1 == newSpeedX2 && newSpeedY1 == newSpeedY2 && newSpeedY1 == newSpeedX1) {
            resetNewSpeed( ball31, ball32 );
        } else {
            ball31.setSpeedX(newSpeedX1);
            ball31.setSpeedY(newSpeedY1);
            ball32.setSpeedX(newSpeedX2);
            ball32.setSpeedY(newSpeedY2);
            ball31.setBallCoordinates(new Point2D.Double(ball31.getBallCoordinates().getX() + newSpeedX1, ball31.getBallCoordinates().getY() + newSpeedY1));
            ball32.setBallCoordinates(new Point2D.Double(ball32.getBallCoordinates().getX() + newSpeedX2, ball32.getBallCoordinates().getY() + newSpeedY2));
        }
    }
}
