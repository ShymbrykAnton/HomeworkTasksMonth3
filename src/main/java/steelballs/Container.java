package steelballs;

import javax.swing.*;
import java.awt.*;

public class Container extends JPanel {
    GetListBall getListBall = new GetListBall();
    public Container(){
        this.setBackground(Color.RED);
    }
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(700, 500);
    }
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        for (Ball ball : getListBall.getBalls()) {
            g2d.setPaint(ball.getBallColor());
            g2d.fillOval((int) ball.getX(),(int) ball.getY(),(int) ball.getRadius(),(int) ball.getRadius());
        }

    }
    public void moveBall(){
        repaint();
    }


}
