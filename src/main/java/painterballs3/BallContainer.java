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
    public  void paintComponent(Graphics g) {
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
                       int speedX1 = ball.getSpeedX();
                       int speedY1 = ball.getSpeedY();
                       int radius1 = ball.getRadius();
                       int energyFirst = radius1*speedX1;
                        System.out.println(speedX1);
                        System.out.println(speedY1);

                       int speedX2 =balls.get(i).getSpeedX();
                       int speedY2 = balls.get(i).getSpeedY();
                       int radius2 = balls.get(i).getRadius();
                       int energySecond = radius2*speedX2;

                       int newSpeedX1 = getNewSpeed1(speedX1,speedX2,radius1,radius2);
                       int newSpeedX2 = getNewSpeed2(speedX1,speedX2,radius1,radius2);

                        int newSpeedY1 = getNewSpeed1(speedY1,speedY2,radius1,radius2);
                        int newSpeedY2 =  getNewSpeed2(speedY1,speedY2,radius1,radius2);


                        balls.get(i).getBallCoordinates().setLocation(balls.get(i).getBallCoordinates().getX()+speedX2,balls.get(i).getBallCoordinates().getY()+speedY2);

                        System.out.println(newSpeedX1 +"new SPEED X1");
                        System.out.println(newSpeedX2+"new SPEED X2");
                        System.out.println(newSpeedY1+"new SPEED Y1");
                        System.out.println(newSpeedY2+"new SPEED Y2");

                       ball.setSpeedX(newSpeedX1);
                       ball.setSpeedY(newSpeedY1);

                        ball.setBallCoordinates(new Point2D.Double(ball.getBallCoordinates().getX()+newSpeedX1,ball.getBallCoordinates().getY()+newSpeedY1));
                        balls.get(i).setBallCoordinates(new Point2D.Double(balls.get(i).getBallCoordinates().getX()+newSpeedX2,balls.get(i).getBallCoordinates().getY()+newSpeedY2));
                       balls.get(i).setSpeedX(newSpeedX2);
                       balls.get(i).setSpeedY(newSpeedY2);
                        try {
                            ball.join(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        flag = false;
                        break;
                    }
                }
            }
            if(!flag){
                break;
            }
        }
        balls.forEach(ball -> {
            g2d.setPaint(ball.getBallColor());
            g2d.fillOval((int) ball.getBallCoordinates().getX(),
                    (int) ball.getBallCoordinates().getY(),
                    (int) ball.getRadius(), (int) ball.getRadius());
        });

    }
    private int getNewSpeed1(int speed1,int speed2, int radius1,int radius2){
        int newSpeed = ((radius1-radius2)*speed1+(2*radius2*speed2))/(radius1+radius2);
        return newSpeed;

    }
    private int getNewSpeed2(int speed1,int speed2,int radius1,int radius2){
        int newSpeed = ((2*radius1*speed1) + speed2*(radius2-radius1))/(radius1+radius2);
        return newSpeed;
    }
    
}
