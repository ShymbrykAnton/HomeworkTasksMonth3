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
        Ball2 remove = null;
        boolean flag = true;
        for (Ball2 ball2 : ball2s) {
            for (int i = ball2s.size()-1; i >= 0; i--) {
                if (ball2s.get(i).equals(ball2)) {
                    continue;
                } else {
                    double dx = ball2.getBallCoordinates().getX() - ball2s.get(i).getBallCoordinates().getX();
                    double dy = ball2.getBallCoordinates().getY() - ball2s.get(i).getBallCoordinates().getY();
                    double distance = Math.sqrt((dx*dx)+(dy*dy));
                    if(distance< ball2.getDiameter()/2.0+
                            ball2s.get(i).getDiameter()/2.0){
                       remove = ball2;
                       Color color = new Color(ball2.getBallColor().getRGB()+
                               ball2s.get(i).getBallColor().getRGB());
                       int speedX = ball2.getSpeedX()+ ball2s.get(i).getSpeedX();
                       int speedY = ball2.getSpeedY()+ ball2s.get(i).getSpeedY();
                       int radius = ball2.getDiameter()+ ball2s.get(i).getDiameter();
                       ball2s.get(i).setDiameter(radius);
                       ball2s.get(i).setBallColor(color);
                       ball2s.get(i).setSpeedX(speedX);
                       ball2s.get(i).setSpeedY(speedY);
                        flag = false;
                        break;
                    }
                }
            }
            if(!flag){
                break;
            }
        }
        ball2s.remove(remove);
        ball2s.forEach(ball2 -> {
            ball2.setFrameHeight(frame.getHeight());
            ball2.setFrameWidth(frame.getWidth());
            g2d.setPaint(ball2.getBallColor());
            g2d.fillOval((int) ball2.getBallCoordinates().getX(),
                    (int) ball2.getBallCoordinates().getY(),
                    (int) ball2.getDiameter(), (int) ball2.getDiameter());
        });
    }
}
