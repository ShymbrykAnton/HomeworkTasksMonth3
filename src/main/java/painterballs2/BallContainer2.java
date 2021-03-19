package painterballs2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.List;

public class BallContainer2 extends JPanel {
    private final JFrame frame;
    volatile List<Ball2> ball2s;

    public BallContainer2(JFrame frame) {
        this.frame = frame;
        ball2s = new ArrayList<>();
        MouseAdapter mouseListener = new BallListener2(ball2s, frame);
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
        setNewBallCharacteristics();
        ball2s.forEach(ball2 -> {
            ball2.setFrameHeight(frame.getHeight());
            ball2.setFrameWidth(frame.getWidth());
            g2d.setPaint(ball2.getBallColor());
            g2d.fillOval((int) ball2.getBallCoordinates().getX(),
                    (int) ball2.getBallCoordinates().getY(),
                    ball2.getDiameter(), ball2.getDiameter());
        });
    }

    private void setNewBallCharacteristics() {
        boolean flag = true;
        for (Ball2 ball2 : ball2s) {
            for ( int i = ball2s.size() - 1 ; i >= 0; i-- ) {
                if (!ball2s.get(i).equals(ball2) && calculateDistance(ball2, ball2s.get(i)) < getGlobalRadius(ball2, ball2s.get(i))) {
                    setNewCharacteristics(ball2, ball2s.get(i));
                    removeBall(ball2);
                    flag = false;
                        break;
                }
            }
            if(!flag){
                break;
            }
        }
    }

    private void removeBall (Ball2 ball) {
        ball2s.remove(ball);
    }

    private double calculateDistance (Ball2 ball1, Ball2 ball2) {
        double dx = ball1.getBallCoordinates().getX() - ball2.getBallCoordinates().getX();
        double dy = ball1.getBallCoordinates().getY() - ball2.getBallCoordinates().getY();
        return Math.sqrt((dx*dx)+(dy*dy));
    }

    private double getGlobalRadius(Ball2 ball1, Ball2 ball2) {
        return ball1.getDiameter() / 2.0 + ball2.getDiameter() / 2.0;
    }

    private void setNewCharacteristics (Ball2 ball1, Ball2 ball2) {
        Color color = new Color(ball1.getBallColor().getRGB()+
                ball2.getBallColor().getRGB());
        int speedX = ball1.getSpeedX() + ball2.getSpeedX();
        int speedY = ball1.getSpeedY() + ball2.getSpeedY();
        int radius = ball1.getDiameter() + ball2.getDiameter();
        ball2.setDiameter(radius);
        ball2.setBallColor(color);
        ball2.setSpeedX(speedX);
        ball2.setSpeedY(speedY);
    }
}
